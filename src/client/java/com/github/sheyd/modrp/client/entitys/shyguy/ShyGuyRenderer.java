package com.github.sheyd.modrp.client.entitys.shyguy;

import com.github.sheyd.modrp.Modrp;
import com.github.sheyd.modrp.client.ModrpClient;
import com.github.sheyd.modrp.entitys.ShyGuy;
import com.github.sheyd.modrp.network.EntityFaceSeenC2SPayload;
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

public class ShyGuyRenderer extends MobEntityRenderer<ShyGuy, ShyGuyModel<ShyGuy>> {

    public ShyGuyRenderer(EntityRendererFactory.Context context) {
        super(context, new ShyGuyModel<>(context.getPart(ModrpClient.MODEL_SHYGUY_LAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(ShyGuy entity) {
        return Identifier.of(Modrp.MOD_ID, "textures/entity/shyguy/shyguy.png");
    }

    @Override
    public void render(ShyGuy entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);

        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return;

        if (hasLineOfSightToEntity(entity)) {
            String log = "line of sight with %s".formatted(entity);
            Modrp.LOGGER.info(log);
            ClientPlayNetworking.send(new EntityFaceSeenC2SPayload(entity.getId()));
        }
    }

    // to update to hisLookingAtEntity
    private boolean hasLineOfSightToEntity(ShyGuy entity) {
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
