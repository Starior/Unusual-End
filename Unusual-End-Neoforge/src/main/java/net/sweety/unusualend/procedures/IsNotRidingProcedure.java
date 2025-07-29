package net.sweety.unusualend.procedures;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;

public class IsNotRidingProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return !entity.isPassenger() && !((new Object() {
			public boolean getValue() {
				CompoundTag dataIndex = new CompoundTag();
				entity.saveWithoutId(dataIndex);
				return dataIndex.getBoolean("Sitting");
			}
		}.getValue()) == true);
	}
}
