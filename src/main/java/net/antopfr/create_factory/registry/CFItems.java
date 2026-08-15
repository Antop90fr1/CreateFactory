package net.antopfr.create_factory.registry;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.antopfr.create_factory.CreateFactory;
import net.antopfr.create_factory.item.EffectFoodItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class CFItems {

    private static final CreateRegistrate REGISTRATE = CreateFactory.registrate();

    static {
        REGISTRATE.setCreativeTab(CreateFactory.CF_TAB);
    }

    public static final ItemEntry<Item> WAFFLE = REGISTRATE.item("waffle", Item::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(5)
                    .saturationModifier(0.3F)
                    .build()))
            .register();
    public static final ItemEntry<Item> UNCOOKED_WAFFLE = REGISTRATE.item("uncooked_waffle", Item::new)
            .register();
    public static final ItemEntry<Item> CAKE_PASTE = REGISTRATE.item("cake_paste", Item::new)
            .register();


    // Rolls
    public static final ItemEntry<Item> HONEY_ROLL = REGISTRATE.item("honey_roll", Item::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(6)
                    .saturationModifier(0.8F)
                    .build()))
            .register();
    public static final ItemEntry<Item> CHOCOLATE_ROLL = REGISTRATE.item("chocolate_roll", Item::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(7)
                    .saturationModifier(0.8F)
                    .build()))
            .register();
    public static final ItemEntry<EffectFoodItem> SWEET_BERRIES_JAM_ROLL = REGISTRATE.item("sweet_berries_jam_roll", EffectFoodItem::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(8)
                    .saturationModifier(0.9F)
                    .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 30, 1, true, false),1)
                    .build()))
            .register();
    public static final ItemEntry<EffectFoodItem> GLOW_BERRIES_JAM_ROLL = REGISTRATE.item("glow_berries_jam_roll", EffectFoodItem::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(8)
                    .saturationModifier(0.9F)
                    .effect(() -> new MobEffectInstance(MobEffects.GLOWING, 200, 0, true, false),1)
                    .build()))
            .register();



    // Berries
    public static final ItemEntry<Item> HONEY_BERRIES = REGISTRATE.item("honey_glazed_berries", Item::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(6)
                    .saturationModifier(0.8F)
                    .build()))
            .register();
    public static final ItemEntry<EffectFoodItem> NECTAR_BERRIES = REGISTRATE.item("nectar_glazed_berries", EffectFoodItem::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(7)
                    .saturationModifier(0.8F)
                    .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 1, true, false),1)
                    .build()))
            .register();


    // Apples
    public static final ItemEntry<Item> CHOCOLATE_APPLE = REGISTRATE.item("chocolate_apple", Item::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(9)
                    .saturationModifier(0.8F)
                    .build()))
            .register();
    public static final ItemEntry<EffectFoodItem> NECTAR_APPLE = REGISTRATE.item("nectar_apple", EffectFoodItem::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(9)
                    .saturationModifier(0.9F)
                    .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 1, true, false),1)
                    .build()))
            .register();



    // Waffles
    public static final ItemEntry<Item> HONEY_WAFFLE = REGISTRATE.item("honey_waffle", Item::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(8)
                    .saturationModifier(0.9F)
                    .build()))
            .register();
    public static final ItemEntry<Item> CHOCOLATE_WAFFLE = REGISTRATE.item("chocolate_waffle", Item::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(9)
                    .saturationModifier(0.8F)
                    .build()))
            .register();
    public static final ItemEntry<EffectFoodItem> SWEET_BERRIES_JAM_WAFFLE = REGISTRATE.item("sweet_berries_jam_waffle", EffectFoodItem::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(11)
                    .saturationModifier(0.9F)
                    .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 30, 1, true, false),1)
                    .build()))
            .register();
    public static final ItemEntry<EffectFoodItem> GLOW_BERRIES_JAM_WAFFLE = REGISTRATE.item("glow_berries_jam_waffle", EffectFoodItem::new )
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(11)
                    .saturationModifier(0.9F)
                    .effect(() -> new MobEffectInstance(MobEffects.GLOWING, 300, 0, true, false),1)
                    .build()))
            .register();

    public static void register() {}
}
