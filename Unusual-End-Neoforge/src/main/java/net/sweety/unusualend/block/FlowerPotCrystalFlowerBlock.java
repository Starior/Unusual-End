
package net.sweety.unusualend.block;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.sweety.unusualend.init.UnusualendModBlocks;

public class FlowerPotCrystalFlowerBlock extends FlowerPotBlock {
	public FlowerPotCrystalFlowerBlock() {
		super(() -> (FlowerPotBlock) Blocks.FLOWER_POT, UnusualendModBlocks.CRYSTAL_FLOWER, BlockBehaviour.Properties.of().instabreak().lightLevel(s -> 10).noOcclusion().pushReaction(PushReaction.DESTROY));
	}
}
