package net.sweety.unusualend.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.sweety.unusualend.init.UnusualendModItems;

public class BolokResearchNotesRightclickedProcedure {
    public static void execute(Level level, double x, double y, double z, LivingEntity entity) {
        if (entity == null)
            return;
        if (!level.isClientSide()) {
            level.playSound(null, BlockPos.containing(x, y, z), SoundEvents.BOOK_PAGE_TURN, SoundSource.PLAYERS, 1, 1);
        } else
            level.playLocalSound(x, y, z, SoundEvents.BOOK_PAGE_TURN, SoundSource.PLAYERS, 1, 1, false);
        if (entity.getMainHandItem().is(UnusualendModItems.BOLOK_RESEARCH_NOTES.get())) {
            entity.swing(InteractionHand.MAIN_HAND, true);
        } else
            entity.swing(InteractionHand.OFF_HAND, true);
    }
}
