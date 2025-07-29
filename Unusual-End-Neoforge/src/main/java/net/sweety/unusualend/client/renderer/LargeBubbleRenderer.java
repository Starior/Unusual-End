
package net.sweety.unusualend.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.sweety.unusualend.UnusualEnd;
import net.sweety.unusualend.client.model.Modelbubble;
import net.sweety.unusualend.entity.LargeBubbleEntity;

public class LargeBubbleRenderer extends MobRenderer<LargeBubbleEntity, Modelbubble<LargeBubbleEntity>> {
	public LargeBubbleRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelbubble(context.bakeLayer(Modelbubble.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(LargeBubbleEntity entity) {
		return UnusualEnd.makeUEID("textures/entities/large_warped_bubble.png");
	}
}
