
package net.sweety.unusualend.block;

import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class WarpedStoneTilesSlabBlock extends SlabBlock {
	public WarpedStoneTilesSlabBlock() {
		super(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.COLOR_CYAN).sound(SoundType.STONE).strength(9f, 11f).requiresCorrectToolForDrops().dynamicShape());
	}
}
