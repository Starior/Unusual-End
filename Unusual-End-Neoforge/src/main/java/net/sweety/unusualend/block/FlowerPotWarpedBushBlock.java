
package net.sweety.unusualend.block;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.sweety.unusualend.init.UnusualEndBlocks;

public class FlowerPotWarpedBushBlock extends FlowerPotBlock {
	public FlowerPotWarpedBushBlock() {
		super(() -> (FlowerPotBlock) Blocks.FLOWER_POT, UnusualEndBlocks.WARPED_BUSH, BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY));
	}
}
