
package net.sweety.unusualend.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

public class ShinyCrystalBlock extends Block {
	public ShinyCrystalBlock() {
		super(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).sound(SoundType.AMETHYST).strength(0.85f, 5f).lightLevel(s -> 10).requiresCorrectToolForDrops());
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 10;
	}

}
