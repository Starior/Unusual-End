package net.sweety.unusualend.mixins;

import net.minecraft.world.level.levelgen.structure.Structure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Structure.class)
public abstract class StructuresMixin {
	@Inject(method = "isValidBiome(Lnet/minecraft/world/level/levelgen/structure/Structure$GenerationStub;Lnet/minecraft/world/level/levelgen/structure/Structure$GenerationContext;)Z", at = @At("RETURN"), cancellable = true)
	private static void UE_stopGenericStructureVoidGen_S(Structure.GenerationStub generationStub, Structure.GenerationContext generationContext, CallbackInfoReturnable<Boolean> cir) {
		int yPos = generationStub.position().getY();
		if (yPos <= generationContext.chunkGenerator().getMinY() + 8) {
			cir.setReturnValue(false);
		}
		cir.setReturnValue(cir.getReturnValue());
	}
}
