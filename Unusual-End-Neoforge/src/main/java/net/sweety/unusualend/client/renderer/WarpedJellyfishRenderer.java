
package net.sweety.unusualend.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.sweety.unusualend.UnusualEnd;
import net.sweety.unusualend.client.model.Modelglub;
import net.sweety.unusualend.entity.EnderBugEntity;
import net.sweety.unusualend.entity.WarpedJellyfishEntity;

public class WarpedJellyfishRenderer extends MobRenderer<WarpedJellyfishEntity, Modelglub<WarpedJellyfishEntity>> {
	public WarpedJellyfishRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelglub(context.bakeLayer(Modelglub.LAYER_LOCATION)), 0.3f);
	}

	protected void scale(EnderBugEntity entity, PoseStack posestack, float partialTicks) {
		posestack.scale(entity.getScale(), entity.getScale(), entity.getScale());
	}

	@Override
	public ResourceLocation getTextureLocation(WarpedJellyfishEntity entity) {
		return UnusualEnd.makeUEID("textures/entities/glub.png");
	}
}
