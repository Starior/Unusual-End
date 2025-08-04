
package net.sweety.unusualend.item.inventory;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.sweety.unusualend.init.UnusualEndItems;

import javax.annotation.Nonnull;

public class BolokResearchNotesInventoryCapability extends ItemStackHandler {
    public BolokResearchNotesInventoryCapability() {
        super(1);
    }

    @Override
    public int getSlotLimit(int slot) {
        return 64;
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        return stack.getItem() != UnusualEndItems.BOLOK_RESEARCH_NOTES.get();
    }

    @Override
    public void setSize(int size) {
    }
}