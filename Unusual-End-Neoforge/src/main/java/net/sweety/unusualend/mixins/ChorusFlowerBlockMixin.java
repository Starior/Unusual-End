package net.sweety.unusualend.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChorusFlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.sweety.unusualend.init.UnusualEndBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChorusFlowerBlock.class)
public abstract class ChorusFlowerBlockMixin extends Block {
	public ChorusFlowerBlockMixin(Properties settings) {
		super(settings);
	}

	@Inject(method = "canSurvive", at = @At("HEAD"), cancellable = true)
	private void canSurvive(BlockState state, LevelReader world, BlockPos pos, CallbackInfoReturnable<Boolean> info) {
		BlockState blockstate = world.getBlockState(pos.below());
		if (blockstate.is(UnusualEndBlocks.RAW_PURPUR_BLOCK.get()) || blockstate.is(UnusualEndBlocks.INFESTED_END_STONE.get()) || blockstate.is(UnusualEndBlocks.ROOTED_RAW_PURPUR.get()) || blockstate.is(UnusualEndBlocks.GLOOPSLATE.get())
				|| blockstate.is(UnusualEndBlocks.GLOOPSTONE.get()) || blockstate.is(UnusualEndBlocks.WARPED_END_STONE.get()) || blockstate.is(UnusualEndBlocks.PURPUR_EMBEDDED_END_STONE.get())) {
			info.setReturnValue(true);
			info.cancel();
		}
	}
}
