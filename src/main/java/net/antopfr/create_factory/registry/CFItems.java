package net.antopfr.create_factory.registry;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.antopfr.create_factory.CreateFactory;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class CFItems {

    private static final CreateRegistrate REGISTRATE = CreateFactory.registrate();


    public static final ItemEntry<Item> WAFFLE = REGISTRATE.item("waffle", Item::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(4)
                    .saturationMod(0.3F)
                    .build()))
            .register();
    public static final ItemEntry<Item> UNCOOKED_WAFFLE = REGISTRATE.item("uncooked_waffle", Item::new)
            .register();
    public static final ItemEntry<Item> CAKE_PASTE = REGISTRATE.item("cake_paste", Item::new)
            .register();




    // Rolls
    public static final ItemEntry<Item> HONEY_ROLL = REGISTRATE.item("honey_roll", Item::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(6)
                    .saturationMod(0.7F)
                    .build()))
            .register();
    public static final ItemEntry<Item> CHOCOLATE_ROLL = REGISTRATE.item("chocolate_roll", Item::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(7)
                    .saturationMod(0.6F)
                    .build()))
            .register();
    public static final ItemEntry<Item> DARK_CHOCOLATE_ROLL = REGISTRATE.item("dark_chocolate_roll", Item::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(7)
                    .saturationMod(0.6F)
                    .build()))
            .register();
    public static final ItemEntry<Item> RUBY_CHOCOLATE_ROLL = REGISTRATE.item("ruby_chocolate_roll", Item::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(8)
                    .saturationMod(0.7F)
                    .build()))
            .register();
    public static final ItemEntry<Item> WHITE_CHOCOLATE_ROLL = REGISTRATE.item("white_chocolate_roll", Item::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(7)
                    .saturationMod(0.6F)
                    .build()))
            .register();
    public static final ItemEntry<Item> CARAMEL_ROLL = REGISTRATE.item("caramel_roll", Item::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(6)
                    .saturationMod(0.5F)
                    .build()))
            .register();



    // Berries
    public static final ItemEntry<Item> HONEY_BERRIES = REGISTRATE.item("honey_glazed_berries", Item::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(5)
                    .saturationMod(0.7F)
                    .build()))
            .register();

    // Apples
    public static final ItemEntry<Item> CHOCOLATE_APPLE = REGISTRATE.item("chocolate_apple", Item::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(6)
                    .saturationMod(0.6F)
                    .build()))
            .register();
    public static final ItemEntry<Item> DARK_CHOCOLATE_APPLE = REGISTRATE.item("dark_chocolate_apple", Item::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(9)
                    .saturationMod(0.7F)
                    .build()))
            .register();
    public static final ItemEntry<Item> RUBY_CHOCOLATE_APPLE = REGISTRATE.item("ruby_chocolate_apple", Item::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(10)
                    .saturationMod(0.8F)
                    .build()))
            .register();
    public static final ItemEntry<Item> WHITE_CHOCOLATE_APPLE = REGISTRATE.item("white_chocolate_apple", Item::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(9)
                    .saturationMod(0.7F)
                    .build()))
            .register();
    public static final ItemEntry<Item> CARAMEL_APPLE = REGISTRATE.item("caramel_apple", Item::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(8)
                    .saturationMod(0.6F)
                    .build()))
            .register();



    // Waffles
    public static final ItemEntry<Item> HONEY_WAFFLE = REGISTRATE.item("honey_waffle", Item::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(6)
                    .saturationMod(0.7F)
                    .build()))
            .register();
    public static final ItemEntry<Item> CHOCOLATE_WAFFLE = REGISTRATE.item("chocolate_waffle", Item::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(9)
                    .saturationMod(0.7F)
                    .build()))
            .register();
    public static final ItemEntry<Item> DARK_CHOCOLATE_WAFFLE = REGISTRATE.item("dark_chocolate_waffle", Item::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(10)
                    .saturationMod(0.8F)
                    .build()))
            .register();
    public static final ItemEntry<Item> RUBY_CHOCOLATE_WAFFLE = REGISTRATE.item("ruby_chocolate_waffle", Item::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(11)
                    .saturationMod(0.9F)
                    .build()))
            .register();
    public static final ItemEntry<Item> WHITE_CHOCOLATE_WAFFLE = REGISTRATE.item("white_chocolate_waffle", Item::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(10)
                    .saturationMod(0.8F)
                    .build()))
            .register();
    public static final ItemEntry<Item> CARAMEL_WAFFLE = REGISTRATE.item("caramel_waffle", Item::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(9)
                    .saturationMod(0.7F)
                    .build()))
            .register();



    public static void register() {}
}
