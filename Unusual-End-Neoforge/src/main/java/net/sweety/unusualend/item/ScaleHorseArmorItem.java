
package net.sweety.unusualend.item;

import net.minecraft.world.item.AnimalArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class ScaleHorseArmorItem extends AnimalArmorItem {
    public ScaleHorseArmorItem() {
        super(ArmorMaterials.DIAMOND, BodyType.EQUESTRIAN, false, new Item.Properties().stacksTo(1).rarity(Rarity.COMMON));
    }
}