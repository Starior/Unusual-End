
package net.sweety.unusualend.block;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.sweety.unusualend.init.UnusualEndBlocks;

public class FlowerPotChorusRootsBlock extends FlowerPotBlock {
	public FlowerPotChorusRootsBlock() {
		super(() -> (FlowerPotBlock) Blocks.FLOWER_POT, UnusualEndBlocks.CHORUS_ROOTS, BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY));
	}
}
