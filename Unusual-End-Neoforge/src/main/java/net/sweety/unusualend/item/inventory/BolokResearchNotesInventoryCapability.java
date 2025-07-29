
package net.sweety.unusualend.item.inventory;

import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.item.ItemTossEvent;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.sweety.unusualend.client.gui.BolokNotesScreen;
import net.sweety.unusualend.init.UnusualendModItems;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class BolokResearchNotesInventoryCapability extends ItemStackHandler {
    public BolokResearchNotesInventoryCapability() {
        super(1);
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onItemDropped(ItemTossEvent event) {
        if (event.getEntity().getItem().getItem() == UnusualendModItems.BOLOK_RESEARCH_NOTES.get()) {
            if (Minecraft.getInstance().screen instanceof BolokNotesScreen) {
                Minecraft.getInstance().player.closeContainer();
            }
        }
    }

    @Override
    public int getSlotLimit(int slot) {
        return 64;
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        return stack.getItem() != UnusualendModItems.BOLOK_RESEARCH_NOTES.get();
    }

    @Override
    public void setSize(int size) {
    }
}
