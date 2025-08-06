
package net.sweety.unusualend.block;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class WarpedStoneBrickWallBlock extends WallBlock {
	public WarpedStoneBrickWallBlock() {
		super(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.COLOR_CYAN).sound(SoundType.STONE).strength(9f, 11f).requiresCorrectToolForDrops().dynamicShape().forceSolidOn());
	}

}
