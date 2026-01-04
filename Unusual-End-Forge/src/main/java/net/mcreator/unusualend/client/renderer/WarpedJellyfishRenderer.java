
package net.mcreator.unusualend.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.mcreator.unusualend.client.model.Modelglub;
import net.mcreator.unusualend.entity.EnderBugEntity;
import net.mcreator.unusualend.entity.WarpedJellyfishEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class WarpedJellyfishRenderer extends MobRenderer<WarpedJellyfishEntity, Modelglub<WarpedJellyfishEntity>> {
	public WarpedJellyfishRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelglub(context.bakeLayer(Modelglub.LAYER_LOCATION)), 0.3f);
	}

	protected void scale(EnderBugEntity entity, PoseStack posestack, float partialTicks) {
		posestack.scale(entity.getScale(), entity.getScale(), entity.getScale());
	}

	@Override
	public ResourceLocation getTextureLocation(WarpedJellyfishEntity entity) {
		return new ResourceLocation("unusualend:textures/entities/glub.png");
	}
}
