
package net.sweety.unusualend.block;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.sweety.unusualend.init.UnusualEndBlocks;

public class FlowerPotPurpurGrassBlock extends FlowerPotBlock {
	public FlowerPotPurpurGrassBlock() {
		super(() -> (FlowerPotBlock) Blocks.FLOWER_POT, UnusualEndBlocks.FLOWERING_PURPUR_GRASS, BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY));
	}
}
