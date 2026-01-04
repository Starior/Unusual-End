
package net.mcreator.unusualend.client.renderer;

import net.mcreator.unusualend.client.model.Modelbolok;
import net.mcreator.unusualend.entity.BolokEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BolokRenderer extends MobRenderer<BolokEntity, Modelbolok<BolokEntity>> {
	public BolokRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelbolok(context.bakeLayer(Modelbolok.LAYER_LOCATION)), 0.4f);
	}

	@Override
	public ResourceLocation getTextureLocation(BolokEntity entity) {
		return new ResourceLocation("unusualend:textures/entities/bolok.png");
	}
}
