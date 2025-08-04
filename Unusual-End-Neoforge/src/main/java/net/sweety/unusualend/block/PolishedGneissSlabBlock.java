
package net.sweety.unusualend.block;

import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class PolishedGneissSlabBlock extends SlabBlock {
	public PolishedGneissSlabBlock() {
		super(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.COLOR_BLUE).sound(SoundType.STONE).strength(14f, 15f).requiresCorrectToolForDrops().dynamicShape());
	}
}
