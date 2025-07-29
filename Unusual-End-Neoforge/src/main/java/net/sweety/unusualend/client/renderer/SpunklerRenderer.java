
package net.sweety.unusualend.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.sweety.unusualend.UnusualEnd;
import net.sweety.unusualend.client.model.Modelspunkler;
import net.sweety.unusualend.entity.SpunklerEntity;

public class SpunklerRenderer extends MobRenderer<SpunklerEntity, Modelspunkler<SpunklerEntity>> {
	public SpunklerRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelspunkler(context.bakeLayer(Modelspunkler.LAYER_LOCATION)), 0.4f);
	}

	@Override
	public ResourceLocation getTextureLocation(SpunklerEntity entity) {
		return UnusualEnd.makeUEID("textures/entities/spunkler.png");
	}
}
