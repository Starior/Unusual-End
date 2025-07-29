
package net.sweety.unusualend.block;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.sweety.unusualend.init.UnusualendModBlocks;

public class FlowerPotChorusFungusBlock extends FlowerPotBlock {
	public FlowerPotChorusFungusBlock() {
		super(() -> (FlowerPotBlock) Blocks.FLOWER_POT, UnusualendModBlocks.CHORUS_FUNGUS, BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY));
	}
}
