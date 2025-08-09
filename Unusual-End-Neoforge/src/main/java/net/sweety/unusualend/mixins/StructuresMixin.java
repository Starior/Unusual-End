package net.sweety.unusualend.mixins;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.structure.Structure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Structure.class)
public abstract class StructuresMixin {
    @Inject(method = "isValidBiome(Lnet/minecraft/world/level/levelgen/structure/Structure$GenerationStub;Lnet/minecraft/world/level/levelgen/structure/Structure$GenerationContext;)Z",
            at = @At("RETURN"),
            cancellable = true)
    private static void UE_stopGenericStructureVoidGen_S(Structure.GenerationStub generationStub,
                                                         Structure.GenerationContext generationContext,
                                                         CallbackInfoReturnable<Boolean> cir) {
        boolean originalReturn = cir.getReturnValue();
        int yPos = generationStub.position().getY();
        if (yPos <= generationContext.chunkGenerator().getMinY() + 8) {
            try {
                Registry<LevelStem> levelStemRegistry = generationContext.registryAccess().registryOrThrow(Registries.LEVEL_STEM);
                for (LevelStem stemHolder : levelStemRegistry) {
                    if (stemHolder.generator() == generationContext.chunkGenerator() ||
                            stemHolder.generator().equals(generationContext.chunkGenerator())) {
                        if (stemHolder.type().equals(Level.END)) {
                            cir.setReturnValue(false);
                            break;
                        }
                    }
                }
            } catch (Exception ignored) {}
        }
        cir.setReturnValue(originalReturn);
    }
}