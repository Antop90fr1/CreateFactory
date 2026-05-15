package net.antopfr.create_factory.registry.compat;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.antopfr.create_factory.CreateFactory;
import net.antopfr.create_factory.item.EffectFoodItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class EcologicsItems {
    private static final CreateRegistrate REGISTRATE = CreateFactory.registrate();

    static {
        REGISTRATE.setCreativeTab(CreateFactory.CF_TAB);
    }

    public static final ItemEntry<EffectFoodItem> SPREAD_ROLL = REGISTRATE.item("spread_roll", EffectFoodItem::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(8)
                    .saturationModifier(0.9F)
                    .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 150, 0, true, false),1)
                    .build()))
            .register();


    public static final ItemEntry<EffectFoodItem> SPREAD_APPLE = REGISTRATE.item("spread_apple", EffectFoodItem::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(10)
                    .saturationModifier(0.8F)
                    .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 150, 0, true, false),1)
                    .build()))
            .register();


    public static final ItemEntry<EffectFoodItem> SPREAD_WAFFLE = REGISTRATE.item("spread_waffle", EffectFoodItem::new )
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(11)
                    .saturationModifier(0.9F)
                    .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 150, 0, true, false),1)
                    .build()))
            .register();

    public static void register() {}
}
