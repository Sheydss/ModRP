package com.github.sheyd.modrp.entitys;

import com.github.sheyd.modrp.Modrp;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.UUID;

public class ShyGuy extends PathAwareEntity {

    private final HashSet<UUID> angryAtList = new HashSet<>();
    @Nullable
    private UUID angryAt;

    public final AnimationState idlingAnimationState = new AnimationState();
    private int idleAnimationCooldown = 0;

    private void setupAnimationStates() {
        if (this.idleAnimationCooldown <= 0) {
            this.idleAnimationCooldown = this.random.nextInt(40) + 80;
            this.idlingAnimationState.start(this.age);
        } else {
            this.idleAnimationCooldown--;
        }
    }

    protected ShyGuy(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.0D, false));
    }

    public static DefaultAttributeContainer.Builder createShyGuyAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 100.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 10.0)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 100.0)
                .add(EntityAttributes.GENERIC_STEP_HEIGHT, 1.0);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getWorld().isClient) {
            setupAnimationStates();
            return;
        }

        String log = "is angry at %s".formatted(this.angryAt);
        Modrp.LOGGER.info(log);
        if (this.angryAt != null) {
            ServerPlayerEntity target = getTargetPlayer();

            if (target == null || !target.isAlive()) {

                this.angryAtList.remove(this.angryAt);
                if(this.angryAtList.isEmpty()) {
                    this.angryAt = null;
                    this.setTarget(null);
                }
                else {
                    this.angryAt = this.angryAtList.iterator().next();
                    this.setTarget(this.getTargetPlayer());
                }
            } else {
                // make sure AI actually keeps chasing this one
                if (this.getTarget() != target) {
                    this.setTarget(target);
                }
            }
        }
    }

    public void onFaceSeenBy(ServerPlayerEntity player) {
        if(this.getTarget() == null) {
            this.setTarget(player);
        }
        this.angryAtList.add(player.getUuid());

        // this.world.playSound(null, getBlockPos(), SoundEvents.ENDERMAN_SCREAM, SoundCategory.HOSTILE, 1.0F, 1.0F);
    }

    private ServerPlayerEntity getTargetPlayer() {
        if (!(this.getWorld() instanceof ServerWorld serverWorld)) return null;
        if (this.angryAt == null) return null;

        return serverWorld.getServer()
                .getPlayerManager()
                .getPlayer(this.angryAt);
    }
}
