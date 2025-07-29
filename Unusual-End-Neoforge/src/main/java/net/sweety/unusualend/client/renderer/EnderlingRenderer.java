
package net.sweety.unusualend.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.sweety.unusualend.UnusualEnd;
import net.sweety.unusualend.client.model.Modelenderling;
import net.sweety.unusualend.client.model.Modelenderling_mask;
import net.sweety.unusualend.entity.EnderlingEntity;
import net.sweety.unusualend.procedures.*;

public class EnderlingRenderer extends MobRenderer<EnderlingEntity, Modelenderling<EnderlingEntity>> {
	public EnderlingRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelenderling(context.bakeLayer(Modelenderling.LAYER_LOCATION)), 0.4f);
		this.addLayer(new RenderLayer<>(this) {
            final ResourceLocation LAYER_TEXTURE = UnusualEnd.makeUEID("textures/entities/spirit_mask_grim.png");

            @Override
            public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, EnderlingEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
                if (ReturnGrimProcedure.execute(entity)) {
                    VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
                    EntityModel model = new Modelenderling_mask(Minecraft.getInstance().getEntityModels().bakeLayer(Modelenderling_mask.LAYER_LOCATION));
                    this.getParentModel().copyPropertiesTo(model);
                    model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
                    model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                    model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
                }
            }
        });
		this.addLayer(new RenderLayer<>(this) {
            final ResourceLocation LAYER_TEXTURE = UnusualEnd.makeUEID("textures/entities/spirit_mask_malice.png");

            @Override
            public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, EnderlingEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
                if (ReturnMaliceProcedure.execute(entity)) {
                    VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
                    EntityModel model = new Modelenderling_mask(Minecraft.getInstance().getEntityModels().bakeLayer(Modelenderling_mask.LAYER_LOCATION));
                    this.getParentModel().copyPropertiesTo(model);
                    model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
                    model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                    model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
                }
            }
        });
		this.addLayer(new RenderLayer<>(this) {
            final ResourceLocation LAYER_TEXTURE = UnusualEnd.makeUEID("textures/entities/spirit_mask_mania.png");

            @Override
            public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, EnderlingEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
                if (ReturnManiaProcedure.execute(entity)) {
                    VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
                    EntityModel model = new Modelenderling_mask(Minecraft.getInstance().getEntityModels().bakeLayer(Modelenderling_mask.LAYER_LOCATION));
                    this.getParentModel().copyPropertiesTo(model);
                    model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
                    model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                    model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
                }
            }
        });
		this.addLayer(new RenderLayer<>(this) {
            final ResourceLocation LAYER_TEXTURE = UnusualEnd.makeUEID("textures/entities/spirit_mask_smile.png");

            @Override
            public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, EnderlingEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
                if (ReturnSmileProcedure.execute(entity)) {
                    VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
                    EntityModel model = new Modelenderling_mask(Minecraft.getInstance().getEntityModels().bakeLayer(Modelenderling_mask.LAYER_LOCATION));
                    this.getParentModel().copyPropertiesTo(model);
                    model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
                    model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                    model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
                }
            }
        });
		this.addLayer(new RenderLayer<>(this) {
            final ResourceLocation LAYER_TEXTURE = UnusualEnd.makeUEID("textures/entities/spirit_mask_twist.png");

            @Override
            public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, EnderlingEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
                if (ReturnTwistProcedure.execute(entity)) {
                    VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
                    EntityModel model = new Modelenderling_mask(Minecraft.getInstance().getEntityModels().bakeLayer(Modelenderling_mask.LAYER_LOCATION));
                    this.getParentModel().copyPropertiesTo(model);
                    model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
                    model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                    model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
                }
            }
        });
		this.addLayer(new RenderLayer<>(this) {
            final ResourceLocation LAYER_TEXTURE = UnusualEnd.makeUEID("textures/entities/spirit_mask_vice.png");

            @Override
            public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, EnderlingEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
                if (ReturnViceProcedure.execute(entity)) {
                    VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
                    EntityModel model = new Modelenderling_mask(Minecraft.getInstance().getEntityModels().bakeLayer(Modelenderling_mask.LAYER_LOCATION));
                    this.getParentModel().copyPropertiesTo(model);
                    model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
                    model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                    model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1, 1, 1, 1);
                }
            }
        });
	}

	@Override
	public ResourceLocation getTextureLocation(EnderlingEntity entity) {
		return UnusualEnd.makeUEID("textures/entities/undead_enderling.png");
	}
}