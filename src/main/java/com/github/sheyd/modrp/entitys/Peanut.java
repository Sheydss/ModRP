package com.github.sheyd.modrp.entitys;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.UUID;

public class Peanut extends PathAwareEntity {

    private final HashSet<UUID> playersSeeingMe = new HashSet<>();
    private long lastSeenTick = 0;
    private boolean seen = false;

    public Peanut(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.0, true) {
            @Override
            public boolean canStart() {
                return !((Peanut) this.mob).seen && super.canStart();
            }

            @Override
            public boolean shouldContinue() {
                return !((Peanut) this.mob).seen && super.shouldContinue();
            }
        });
        this.goalSelector.add(2, new WanderAroundFarGoal(this, 1.0) {
            @Override
            public boolean canStart() {
                return !((Peanut) this.mob).seen && super.canStart();
            }

            @Override
            public boolean shouldContinue() {
                return !((Peanut) this.mob).seen && super.shouldContinue();
            }
        });
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true, false));
    }

    public static DefaultAttributeContainer.Builder createPeanutAttributes() {
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

        if (this.getWorld().isClient) return;

        for (PlayerEntity player : this.getWorld().getPlayers()) {
            if (player.isSpectator()) continue;

            this.seen = isSeen();
            if (this.seen) {
                this.getNavigation().stop();
                this.setVelocity(Vec3d.ZERO);
            }
            else {
                double killRadius = 2.5;
                if (this.squaredDistanceTo(player) <= killRadius * killRadius) {
                    player.damage(this.getDamageSources().mobAttack(this), 1000.0F);
                }
            }
        }
    }

    private float getAttackDamage() {
        return (float)this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
    }

    @Override
    public boolean isAttackable() {
        return false;
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        return false;
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        return true;
    }

    @Override
    public boolean canBeHitByProjectile() {
        return false;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    public boolean isSeen() {
        return this.getWorld().getTime() - lastSeenTick <= 5;
    }

    public void markSeenBy(ServerPlayerEntity player) {
        this.lastSeenTick = this.getWorld().getTime();
        //this.playersSeeingMe.add(player.getUuid());
    }
}
