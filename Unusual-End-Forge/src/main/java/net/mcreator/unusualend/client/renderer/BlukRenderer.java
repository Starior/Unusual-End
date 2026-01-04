
package net.mcreator.unusualend.client.renderer;

import net.mcreator.unusualend.client.model.Modelbluk;
import net.mcreator.unusualend.entity.BlukEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BlukRenderer extends MobRenderer<BlukEntity, Modelbluk<BlukEntity>> {
	public BlukRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelbluk(context.bakeLayer(Modelbluk.LAYER_LOCATION)), 0.3f);
	}

	@Override
	public ResourceLocation getTextureLocation(BlukEntity entity) {
		return new ResourceLocation("unusualend:textures/entities/bluk.png");
	}
}
