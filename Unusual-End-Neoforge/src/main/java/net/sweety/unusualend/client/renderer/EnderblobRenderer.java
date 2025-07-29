
package net.sweety.unusualend.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.sweety.unusualend.UnusualEnd;
import net.sweety.unusualend.client.model.Modelenderblob;
import net.sweety.unusualend.entity.EnderblobEntity;

public class EnderblobRenderer extends MobRenderer<EnderblobEntity, Modelenderblob<EnderblobEntity>> {
	public EnderblobRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelenderblob(context.bakeLayer(Modelenderblob.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(EnderblobEntity entity) {
		return UnusualEnd.makeUEID("textures/entities/enderblob.png");
	}
}
