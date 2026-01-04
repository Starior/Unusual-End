
package net.mcreator.unusualend.client.renderer;

import net.mcreator.unusualend.client.model.Modelqueen;
import net.mcreator.unusualend.entity.EnderblobQueenEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class EnderblobQueenRenderer extends MobRenderer<EnderblobQueenEntity, Modelqueen<EnderblobQueenEntity>> {
	public EnderblobQueenRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelqueen(context.bakeLayer(Modelqueen.LAYER_LOCATION)), 0.7f);
	}

	@Override
	public ResourceLocation getTextureLocation(EnderblobQueenEntity entity) {
		return new ResourceLocation("unusualend:textures/entities/enderblob_queen.png");
	}
}
