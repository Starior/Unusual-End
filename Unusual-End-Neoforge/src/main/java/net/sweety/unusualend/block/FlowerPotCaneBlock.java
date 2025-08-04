
package net.sweety.unusualend.block;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.sweety.unusualend.init.UnusualEndBlocks;

public class FlowerPotCaneBlock extends FlowerPotBlock {
	public FlowerPotCaneBlock() {
		super(() -> (FlowerPotBlock) Blocks.FLOWER_POT, UnusualEndBlocks.BLOOMING_CHORUS_CANE, BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY));
	}
}
