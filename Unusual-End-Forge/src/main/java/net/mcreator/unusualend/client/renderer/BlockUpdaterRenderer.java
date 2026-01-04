
package net.mcreator.unusualend.client.renderer;

import net.mcreator.unusualend.client.model.Modelstructure_spawn;
import net.mcreator.unusualend.entity.BlockUpdaterEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BlockUpdaterRenderer extends MobRenderer<BlockUpdaterEntity, Modelstructure_spawn<BlockUpdaterEntity>> {
	public BlockUpdaterRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelstructure_spawn(context.bakeLayer(Modelstructure_spawn.LAYER_LOCATION)), 0.2f);
	}

	@Override
	public ResourceLocation getTextureLocation(BlockUpdaterEntity entity) {
		return new ResourceLocation("unusualend:textures/entities/structure_spawn.png");
	}
}
