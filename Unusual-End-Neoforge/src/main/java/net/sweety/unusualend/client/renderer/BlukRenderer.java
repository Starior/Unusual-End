
package net.sweety.unusualend.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.sweety.unusualend.UnusualEnd;
import net.sweety.unusualend.client.model.Modelbluk;
import net.sweety.unusualend.entity.BlukEntity;

public class BlukRenderer extends MobRenderer<BlukEntity, Modelbluk<BlukEntity>> {
	public BlukRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelbluk(context.bakeLayer(Modelbluk.LAYER_LOCATION)), 0.3f);
	}

	@Override
	public ResourceLocation getTextureLocation(BlukEntity entity) {
		return UnusualEnd.makeUEID("textures/entities/bluk.png");
	}
}
