
package net.sweety.unusualend.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.sweety.unusualend.UnusualEnd;
import net.sweety.unusualend.client.model.Modelenderbulb;
import net.sweety.unusualend.entity.EnderbulbEntity;

public class EnderbulbRenderer extends MobRenderer<EnderbulbEntity, Modelenderbulb<EnderbulbEntity>> {
	public EnderbulbRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelenderbulb(context.bakeLayer(Modelenderbulb.LAYER_LOCATION)), 0.6f);
	}

	@Override
	public ResourceLocation getTextureLocation(EnderbulbEntity entity) {
		if (entity.getPersistentData().getBoolean("BulbClose")) {
			return UnusualEnd.makeUEID("textures/entities/enderbulb_close.png");
		} else {
			return UnusualEnd.makeUEID("textures/entities/enderbulb_open.png");
		}
	}
}
