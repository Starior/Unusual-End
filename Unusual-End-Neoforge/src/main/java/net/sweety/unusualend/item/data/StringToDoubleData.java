package net.sweety.unusualend.item.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.ItemStack;
import net.sweety.unusualend.init.UnusualEndMiscRegister;

import java.util.HashMap;

public record StringToDoubleData(HashMap<String, Double> stringDoubleMap) {
    public static final Codec<StringToDoubleData> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.unboundedMap(Codec.STRING, Codec.DOUBLE)
                            .xmap(
                                    HashMap::new,
                                    hashMap -> hashMap
                            )
                            .fieldOf("stringDoubleMap")
                            .forGetter(StringToDoubleData::stringDoubleMap)
            ).apply(instance, StringToDoubleData::new)
    );

    public static double getData(ItemStack itemStack, String key) {
        return itemStack.has(UnusualEndMiscRegister.STRING_TO_DOUBLE_DATA.get()) ?
                itemStack.get(UnusualEndMiscRegister.STRING_TO_DOUBLE_DATA.get()).stringDoubleMap.get(key) : 0;
    }

    public static void putData(ItemStack itemStack, String key, double value) {
        if (itemStack.has(UnusualEndMiscRegister.STRING_TO_DOUBLE_DATA.get())) {
            HashMap<String, Double> hashMap = itemStack.get(UnusualEndMiscRegister.STRING_TO_DOUBLE_DATA.get()).stringDoubleMap;
            hashMap.put(key, value);
            itemStack.set(UnusualEndMiscRegister.STRING_TO_DOUBLE_DATA.get(), new StringToDoubleData(hashMap));
        }
    }
}