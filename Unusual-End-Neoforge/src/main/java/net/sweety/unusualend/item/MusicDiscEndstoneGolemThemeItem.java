
package net.sweety.unusualend.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.sweety.unusualend.init.UnusualEndSounds;

public class MusicDiscEndstoneGolemThemeItem extends Item {
	public MusicDiscEndstoneGolemThemeItem() {
		super(new Item.Properties().stacksTo(1).rarity(Rarity.RARE).jukeboxPlayable(UnusualEndSounds.ENDSTONE_GOLEM));
	}
}