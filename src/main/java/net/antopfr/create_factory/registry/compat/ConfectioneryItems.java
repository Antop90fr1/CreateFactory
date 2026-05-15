package net.antopfr.create_factory.registry.compat;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.antopfr.create_factory.CreateFactory;
import net.antopfr.create_factory.item.EffectFoodItem;
import net.mcreator.createconfectionery.init.CreateConfectioneryModMobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ConfectioneryItems {
    private static final CreateRegistrate REGISTRATE = CreateFactory.registrate();

    static {
        REGISTRATE.setCreativeTab(CreateFactory.CF_TAB);
    }

    public static final ItemEntry<EffectFoodItem> DARK_CHOCOLATE_ROLL = REGISTRATE.item("dark_chocolate_roll", EffectFoodItem::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(7)
                    .saturationModifier(0.8F)
                    .effect(() -> new MobEffectInstance(CreateConfectioneryModMobEffects.STIMULATION, 120, 0, true, false), 1)
                    .build()))
            .register();
    public static final ItemEntry<EffectFoodItem> RUBY_CHOCOLATE_ROLL = REGISTRATE.item("ruby_chocolate_roll", EffectFoodItem::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(7)
                    .saturationModifier(0.8F)
                    .effect(() -> new MobEffectInstance(MobEffects.SATURATION, 40, 0, true, false), 1)
                    .build()))
            .register();
    public static final ItemEntry<EffectFoodItem> WHITE_CHOCOLATE_ROLL = REGISTRATE.item("white_chocolate_roll", EffectFoodItem::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(7)
                    .saturationModifier(0.8F)
                    .effect(() -> new MobEffectInstance(CreateConfectioneryModMobEffects.REST, 600, 0, true, false), 1)
                    .build()))
            .register();
    public static final ItemEntry<EffectFoodItem> CARAMEL_ROLL = REGISTRATE.item("caramel_roll", EffectFoodItem::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(7)
                    .saturationModifier(0.8F)
                    .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 0, true, false), 1)
                    .build()))
            .register();


    public static final ItemEntry<EffectFoodItem> DARK_CHOCOLATE_APPLE = REGISTRATE.item("dark_chocolate_apple", EffectFoodItem::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(9)
                    .saturationModifier(0.8F)
                    .effect(() -> new MobEffectInstance(CreateConfectioneryModMobEffects.STIMULATION, 120, 0, true, false), 1)

                    .build()))
            .register();
    public static final ItemEntry<EffectFoodItem> RUBY_CHOCOLATE_APPLE = REGISTRATE.item("ruby_chocolate_apple", EffectFoodItem::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(9)
                    .saturationModifier(0.8F)
                    .effect(() -> new MobEffectInstance(MobEffects.SATURATION, 40, 0, true, false), 1)
                    .build()))
            .register();
    public static final ItemEntry<EffectFoodItem> WHITE_CHOCOLATE_APPLE = REGISTRATE.item("white_chocolate_apple", EffectFoodItem::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(9)
                    .saturationModifier(0.8F)
                    .effect(() -> new MobEffectInstance(CreateConfectioneryModMobEffects.REST, 600, 0, true, false), 1)
                    .build()))
            .register();
    public static final ItemEntry<EffectFoodItem> CARAMEL_APPLE = REGISTRATE.item("caramel_apple", EffectFoodItem::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(9)
                    .saturationModifier(0.8F)
                    .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 0, true, false), 1)
                    .build()))
            .register();


    public static final ItemEntry<EffectFoodItem> DARK_CHOCOLATE_WAFFLE = REGISTRATE.item("dark_chocolate_waffle", EffectFoodItem::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(9)
                    .saturationModifier(0.8F)
                    .effect(() -> new MobEffectInstance(CreateConfectioneryModMobEffects.STIMULATION, 120, 0, true, false), 1)

                    .build()))
            .register();
    public static final ItemEntry<EffectFoodItem> RUBY_CHOCOLATE_WAFFLE = REGISTRATE.item("ruby_chocolate_waffle", EffectFoodItem::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(9)
                    .saturationModifier(0.8F)
                    .effect(() -> new MobEffectInstance(MobEffects.SATURATION, 40, 0, true, false), 1)
                    .build()))
            .register();
    public static final ItemEntry<EffectFoodItem> WHITE_CHOCOLATE_WAFFLE = REGISTRATE.item("white_chocolate_waffle", EffectFoodItem::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(9)
                    .saturationModifier(0.8F)
                    .effect(() -> new MobEffectInstance(CreateConfectioneryModMobEffects.REST, 600, 0, true, false), 1)
                    .build()))
            .register();
    public static final ItemEntry<EffectFoodItem> CARAMEL_WAFFLE = REGISTRATE.item("caramel_waffle", EffectFoodItem::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(9)
                    .saturationModifier(0.8F)
                    .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 0, true, false), 1)
                    .build()))
            .register();

    public static void register() {}
}
