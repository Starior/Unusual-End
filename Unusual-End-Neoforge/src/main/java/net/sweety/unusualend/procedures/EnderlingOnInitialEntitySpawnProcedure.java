package net.sweety.unusualend.procedures;

import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.LevelAccessor;
import net.sweety.unusualend.UnusualEnd;
import net.sweety.unusualend.configuration.UEConfig;

public class EnderlingOnInitialEntitySpawnProcedure {
    public static void execute(LevelAccessor world, LivingEntity entity) {
        if (entity == null)
            return;
        if (!world.isClientSide()) {
            if (Math.random() < UEConfig.ENDERLING_MASK.get() / 100) {
                if (entity instanceof Player _player) {
                    _player.getInventory().armor.set(3, new ItemStack((BuiltInRegistries.ITEM.getTag(ItemTags.create(UnusualEnd.makeUEID("enderling_mask"))).flatMap(enderlingMask -> enderlingMask.getRandomElement(RandomSource.create())).orElseGet(Items.AIR::builtInRegistryHolder))));
                    _player.getInventory().setChanged();
                } else {
                    entity.setItemSlot(EquipmentSlot.HEAD,
                            new ItemStack((BuiltInRegistries.ITEM.getTag(ItemTags.create(UnusualEnd.makeUEID("enderling_mask"))).flatMap(tag -> tag.getRandomElement(RandomSource.create())).orElseGet(Items.AIR::builtInRegistryHolder))));
                }
                ItemStack stack = entity.getItemBySlot(EquipmentSlot.HEAD);
                stack.hurtAndBreak(Mth.nextInt(RandomSource.create(), 0, 155), entity, entity.getEquipmentSlotForItem(stack));
                if (!entity.level().isClientSide() && entity.getServer() != null) {
                    entity.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, entity.position(), entity.getRotationVector(), entity.level() instanceof ServerLevel ? (ServerLevel) entity.level() : null, 4,
                            entity.getName().getString(), entity.getDisplayName(), entity.level().getServer(), entity), "data merge entity @s {ArmorDropChances: [0f, 0f, 0f, 0.6f]}");
                }
            }
        }
    }
}
