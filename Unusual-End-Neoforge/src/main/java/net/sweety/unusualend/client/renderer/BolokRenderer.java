
package net.sweety.unusualend.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.sweety.unusualend.UnusualEnd;
import net.sweety.unusualend.client.model.Modelbolok;
import net.sweety.unusualend.entity.BolokEntity;

public class BolokRenderer extends MobRenderer<BolokEntity, Modelbolok<BolokEntity>> {
	public BolokRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelbolok(context.bakeLayer(Modelbolok.LAYER_LOCATION)), 0.4f);
	}

	@Override
	public ResourceLocation getTextureLocation(BolokEntity entity) {
		return UnusualEnd.makeUEID("textures/entities/bolok.png");
	}
}
