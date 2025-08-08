
package net.sweety.unusualend.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;

public class MoultDaggerItem extends SwordItem {
    public MoultDaggerItem() {
        super(ModTiers.DAGGER, new Item.Properties().attributes(SwordItem.createAttributes(ModTiers.DAGGER, 1, -3)));
    }
}
