package net.sweety.unusualend.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.sweety.unusualend.init.UnusualEndEnchantments;
import net.sweety.unusualend.init.UnusualEndMiscRegister;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {
    public ItemEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }
    @Inject(method = "tick", at = @At("RETURN"))
    private void onTickReturn(CallbackInfo ci) {
        if (EnchantmentHelper.getTagEnchantmentLevel(this.level().registryAccess().holderOrThrow(UnusualEndEnchantments.EVERLASTING), ((ItemEntity) (Object) this).getItem()) > 0) {
            if (this.getY() < -72) {
                if (this.level() instanceof ServerLevel level)
                    level.sendParticles(UnusualEndMiscRegister.WARPED_BUBBLES.get(), this.getX(), this.getY(), this.getZ(), 10, 0.1, 0.1, 0.1, 0);
                BlockPos islandTopPos = this.level().getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, this.getOnPos());
                this.teleportTo(islandTopPos.getX(), this.getY() + 1, this.getZ());
            }
        }
    }
}