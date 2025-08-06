package net.sweety.unusualend.init;

import com.google.common.base.Suppliers;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.sweety.unusualend.UnusualEnd;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class UnusualEndLootModifier {
	public static class UnusualEndLootTableModifier extends LootModifier {
		public static Supplier<MapCodec<UnusualEndLootTableModifier>> CODEC_SUPPLIER = Suppliers.memoize(() -> RecordCodecBuilder
				.mapCodec(instance -> UnusualEndLootTableModifier.codecStart(instance)
						.and(BuiltInRegistries.ITEM.byNameCodec().fieldOf("item")
								.forGetter(addItemModifierInstance -> addItemModifierInstance.item))
						.apply(instance, UnusualEndLootTableModifier::new)));

		private final Item item;

		public UnusualEndLootTableModifier(LootItemCondition[] conditionsIn, Item item) {
			super(conditionsIn);
			this.item = item;
		}

		@Override
		protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
			for (LootItemCondition condition : this.conditions) {
				if (!condition.test(context)) {
					return generatedLoot;
				}
			}

			generatedLoot.add(new ItemStack(this.item));
			return generatedLoot;
		}

		@Override
		public MapCodec<? extends IGlobalLootModifier> codec() {
			return CODEC_SUPPLIER.get();
		}
	}

	public static DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(NeoForgeRegistries.GLOBAL_LOOT_MODIFIER_SERIALIZERS, UnusualEnd.MODID);
	public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<UnusualEndLootTableModifier>> LOOT_MODIFIER = LOOT_MODIFIERS.register("unusualend_loot_modifier", UnusualEndLootTableModifier.CODEC_SUPPLIER);
}
