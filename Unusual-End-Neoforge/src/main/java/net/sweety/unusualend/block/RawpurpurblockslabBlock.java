
package net.sweety.unusualend.block;

import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class RawpurpurblockslabBlock extends SlabBlock {
	public RawpurpurblockslabBlock() {
		super(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.COLOR_MAGENTA).sound(SoundType.STONE).strength(1.5f, 6f).requiresCorrectToolForDrops().dynamicShape());
	}
}
