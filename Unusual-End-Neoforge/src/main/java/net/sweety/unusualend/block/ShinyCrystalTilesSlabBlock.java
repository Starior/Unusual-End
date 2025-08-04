
package net.sweety.unusualend.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

public class ShinyCrystalTilesSlabBlock extends SlabBlock {
	public ShinyCrystalTilesSlabBlock() {
		super(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).sound(SoundType.AMETHYST).strength(0.85f, 4f).lightLevel(s -> 5).requiresCorrectToolForDrops().dynamicShape());
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}

}
