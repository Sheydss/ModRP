package com.github.sheyd.modrp.client.entitys.shyguy;

import com.github.sheyd.modrp.entitys.ShyGuy;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;

// Made with Blockbench 5.0.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class ShyGuyModel<T  extends ShyGuy> extends SinglePartEntityModel<T> {
    private final ModelPart ShyGuy;
    private final ModelPart head;
    private final ModelPart neck;
    private final ModelPart upperhead;
    private final ModelPart lowerhead;
    private final ModelPart left_arm;
    private final ModelPart left_hand;
    private final ModelPart left_upperarm;
    private final ModelPart left_lowerarm;
    private final ModelPart right_arm;
    private final ModelPart right_hand;
    private final ModelPart right_upperarm;
    private final ModelPart right_lowerarm;
    private final ModelPart body;
    private final ModelPart torso;
    private final ModelPart hips;
    private final ModelPart left_leg;
    private final ModelPart left_upperleg;
    private final ModelPart left_lowerleg;
    private final ModelPart left_foot;
    private final ModelPart right_leg;
    private final ModelPart right_upperleg;
    private final ModelPart right_lowerleg;
    private final ModelPart right_foot;

    public ShyGuyModel(ModelPart root) {
        this.ShyGuy = root.getChild("ShyGuy");
        this.head = this.ShyGuy.getChild("head");
        this.neck = this.head.getChild("neck");
        this.upperhead = this.head.getChild("upperhead");
        this.lowerhead = this.head.getChild("lowerhead");
        this.left_arm = this.ShyGuy.getChild("left_arm");
        this.left_hand = this.left_arm.getChild("left_hand");
        this.left_upperarm = this.left_arm.getChild("left_upperarm");
        this.left_lowerarm = this.left_arm.getChild("left_lowerarm");
        this.right_arm = this.ShyGuy.getChild("right_arm");
        this.right_hand = this.right_arm.getChild("right_hand");
        this.right_upperarm = this.right_arm.getChild("right_upperarm");
        this.right_lowerarm = this.right_arm.getChild("right_lowerarm");
        this.body = this.ShyGuy.getChild("body");
        this.torso = this.body.getChild("torso");
        this.hips = this.body.getChild("hips");
        this.left_leg = this.ShyGuy.getChild("left_leg");
        this.left_upperleg = this.left_leg.getChild("left_upperleg");
        this.left_lowerleg = this.left_leg.getChild("left_lowerleg");
        this.left_foot = this.left_leg.getChild("left_foot");
        this.right_leg = this.ShyGuy.getChild("right_leg");
        this.right_upperleg = this.right_leg.getChild("right_upperleg");
        this.right_lowerleg = this.right_leg.getChild("right_lowerleg");
        this.right_foot = this.right_leg.getChild("right_foot");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData ShyGuy = modelPartData.addChild("ShyGuy", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -3.0F, 0.0F));

        ModelPartData head = ShyGuy.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -6.0F, 0.0F));

        ModelPartData neck = head.addChild("neck", ModelPartBuilder.create().uv(32, 40).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 2.0F));

        ModelPartData upperhead = head.addChild("upperhead", ModelPartBuilder.create().uv(0, 18).cuboid(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -4.0F, 2.0F));

        ModelPartData lowerhead = head.addChild("lowerhead", ModelPartBuilder.create().uv(24, 0).cuboid(-3.0F, -3.0F, -3.0F, 6.0F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -1.0F, 2.0F));

        ModelPartData left_arm = ShyGuy.addChild("left_arm", ModelPartBuilder.create(), ModelTransform.of(5.0F, -5.0F, 2.0F, -1.5708F, 0.0F, -1.5708F));

        ModelPartData left_hand = left_arm.addChild("left_hand", ModelPartBuilder.create().uv(40, 23).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 22.0F, 0.0F));

        ModelPartData left_upperarm = left_arm.addChild("left_upperarm", ModelPartBuilder.create().uv(0, 30).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData left_lowerarm = left_arm.addChild("left_lowerarm", ModelPartBuilder.create().uv(36, 28).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 12.0F, 0.0F));

        ModelPartData right_arm = ShyGuy.addChild("right_arm", ModelPartBuilder.create(), ModelTransform.of(-5.0F, -5.0F, 2.0F, -1.5708F, 0.0F, 1.5708F));

        ModelPartData right_hand = right_arm.addChild("right_hand", ModelPartBuilder.create().uv(40, 18).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 22.0F, 0.0F));

        ModelPartData right_upperarm = right_arm.addChild("right_upperarm", ModelPartBuilder.create().uv(24, 16).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData right_lowerarm = right_arm.addChild("right_lowerarm", ModelPartBuilder.create().uv(32, 16).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 12.0F, 0.0F));

        ModelPartData body = ShyGuy.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -1.0F, 0.0F));

        ModelPartData torso = body.addChild("torso", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -5.0F, 0.0F, 8.0F, 14.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData hips = body.addChild("hips", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData hips_r1 = hips.addChild("hips_r1", ModelPartBuilder.create().uv(24, 8).cuboid(-2.5F, -5.5F, 0.8F, 5.0F, 5.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 11.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

        ModelPartData left_leg = ShyGuy.addChild("left_leg", ModelPartBuilder.create(), ModelTransform.pivot(3.0F, 9.0F, 2.0F));

        ModelPartData left_upperleg = left_leg.addChild("left_upperleg", ModelPartBuilder.create().uv(16, 37).cuboid(-1.0F, -1.0F, -0.7F, 2.0F, 9.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData left_lowerleg = left_leg.addChild("left_lowerleg", ModelPartBuilder.create().uv(40, 8).cuboid(-1.0F, -1.0F, -0.7F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 9.0F, 0.0F));

        ModelPartData left_foot = left_leg.addChild("left_foot", ModelPartBuilder.create().uv(22, 30).cuboid(-1.0F, -1.0F, -3.7F, 2.0F, 2.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 17.0F, 0.0F));

        ModelPartData right_leg = ShyGuy.addChild("right_leg", ModelPartBuilder.create(), ModelTransform.pivot(-3.0F, 9.0F, 2.0F));

        ModelPartData right_upperleg = right_leg.addChild("right_upperleg", ModelPartBuilder.create().uv(16, 37).cuboid(-1.0F, -1.0F, -0.7F, 2.0F, 9.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData right_lowerleg = right_leg.addChild("right_lowerleg", ModelPartBuilder.create().uv(40, 8).cuboid(-1.0F, -1.0F, -0.7F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 9.0F, 0.0F));

        ModelPartData right_foot = right_leg.addChild("right_foot", ModelPartBuilder.create().uv(22, 30).cuboid(-1.0F, -1.0F, -3.7F, 2.0F, 2.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 17.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(ShyGuy entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);

        this.animateMovement(ShyGuyAnimations.RUNNING, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.updateAnimation(entity.idlingAnimationState, ShyGuyAnimations.IDLE, ageInTicks, 1f);
    }

    public ModelPart getPart() {
        return ShyGuy;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        ShyGuy.render(matrices, vertexConsumer, light, overlay, color);
    }


}