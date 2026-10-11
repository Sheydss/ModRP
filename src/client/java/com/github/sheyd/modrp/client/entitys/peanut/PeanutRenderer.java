package com.github.sheyd.modrp.client.entitys.peanut;

import com.github.sheyd.modrp.client.ModrpClient;
import com.github.sheyd.modrp.entitys.Peanut;
import com.github.sheyd.modrp.Modrp;
import com.github.sheyd.modrp.network.EntitySeenC2SPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;

public class PeanutRenderer extends MobEntityRenderer<Peanut, PeanutModel> {

    public PeanutRenderer(EntityRendererFactory.Context context) {
        super(context, new PeanutModel(context.getPart(ModrpClient.MODEL_PEANUT_LAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(Peanut entity) {
        return Identifier.of(Modrp.MOD_ID, "textures/entity/peanut/peanut.png");
    }

    @Override
    public void render(Peanut entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);

        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return;

        if (hasLineOfSightToEntity(entity)) {
            ClientPlayNetworking.send(new EntitySeenC2SPayload(entity.getId()));
        }
    }

    private boolean hasLineOfSightToEntity(Peanut entity) {
        MinecraftClient mc = MinecraftClient.getInstance();
        ClientPlayerEntity player = mc.player;
        if (player == null) return false;

        ClientWorld world = mc.world;
        if (world == null) return false;

        Vec3d from = player.getCameraPosVec(1.0F);

        Box box = entity.getBoundingBox();
        Vec3d to = new Vec3d(
                (box.minX + box.maxX) * 0.5,
                box.minY + (box.maxY - box.minY) * 0.9, // near top of hitbox
                (box.minZ + box.maxZ) * 0.5
        );

        BlockHitResult hit = world.raycast(new RaycastContext(
                from,
                to,
                RaycastContext.ShapeType.COLLIDER,
                RaycastContext.FluidHandling.NONE,
                player
        ));

        if (hit.getType() == HitResult.Type.MISS) {
            return true;
        }

        double hitDistSq = hit.getPos().squaredDistanceTo(from);
        double entityDistSq = to.squaredDistanceTo(from);

        return hitDistSq >= entityDistSq - 1e-4;
    }

}
