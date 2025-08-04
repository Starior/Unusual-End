
package net.sweety.unusualend.block;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.sweety.unusualend.init.UnusualEndBlocks;

public class FlowerPotShinySpireaBlock extends FlowerPotBlock {
	public FlowerPotShinySpireaBlock() {
		super(() -> (FlowerPotBlock) Blocks.FLOWER_POT, UnusualEndBlocks.SHINY_SPIREA, BlockBehaviour.Properties.of().instabreak().lightLevel(s -> 8).noOcclusion().pushReaction(PushReaction.DESTROY));
	}
}
