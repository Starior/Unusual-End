
package net.sweety.unusualend.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.sweety.unusualend.UnusualEnd;
import net.sweety.unusualend.client.model.Modelendertrapper118;
import net.sweety.unusualend.entity.EnderTrapperEntity;

public class EnderTrapperRenderer extends MobRenderer<EnderTrapperEntity, Modelendertrapper118<EnderTrapperEntity>> {
	public EnderTrapperRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelendertrapper118(context.bakeLayer(Modelendertrapper118.LAYER_LOCATION)), 0f);
	}

	@Override
	public ResourceLocation getTextureLocation(EnderTrapperEntity entity) {
		return UnusualEnd.makeUEID("textures/entities/ender_trapper.png");
	}
}
