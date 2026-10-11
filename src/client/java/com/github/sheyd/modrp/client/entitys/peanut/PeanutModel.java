package com.github.sheyd.modrp.client.entitys.peanut;

import com.github.sheyd.modrp.entitys.Peanut;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class PeanutModel extends EntityModel<Peanut> {

    private final ModelPart bb_main;

    public PeanutModel(ModelPart root) {
        this.bb_main = root.getChild("bb_main");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(32, 16).cuboid(-4.5F, -6.0F, -1.5F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F))
                .uv(32, 25).cuboid(1.5F, -6.0F, -1.5F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-5.0F, -26.0F, -3.0F, 10.0F, 20.0F, 6.0F, new Dilation(0.0F))
                .uv(0, 26).cuboid(-4.5F, -38.0F, -4.0F, 9.0F, 12.0F, 7.0F, new Dilation(0.0F))
                .uv(32, 0).cuboid(-6.0F, -23.0F, -7.0F, 1.0F, 1.0F, 7.0F, new Dilation(0.0F))
                .uv(32, 8).cuboid(5.0F, -23.0F, -7.0F, 1.0F, 1.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(Peanut entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        bb_main.render(matrices, vertexConsumer, light, overlay, color);
    }
}