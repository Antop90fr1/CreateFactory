package net.antopfr.create_factory.registry;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.antopfr.create_factory.CreateFactory;
import net.antopfr.create_factory.jar.JarBlock;
import net.antopfr.create_factory.jar.JarItem;
import net.minecraft.world.item.DyeColor;

import java.util.EnumMap;
import java.util.Map;

public class CFBlocks {
    private static final CreateRegistrate REGISTRATE =
            CreateFactory.registrate();

        public static final BlockEntry<JarBlock> WHITE_JAR = CreateFactory.REGISTRATE
                .block("white_jar", p -> new JarBlock())
                .item(JarItem::new)
                .build()
                .register();

        public static final BlockEntry<JarBlock> RED_JAR = CreateFactory.REGISTRATE
                .block("red_jar", p -> new JarBlock())
                .item(JarItem::new)
                .build()
                .register();

        public static final BlockEntry<JarBlock> ORANGE_JAR = CreateFactory.REGISTRATE
            .block("orange_jar", p -> new JarBlock())
            .item(JarItem::new)
            .build()
            .register();

        public static final BlockEntry<JarBlock> YELLOW_JAR = CreateFactory.REGISTRATE
            .block("yellow_jar", p -> new JarBlock())
            .item(JarItem::new)
            .build()
            .register();

        public static final BlockEntry<JarBlock> LIME_JAR = CreateFactory.REGISTRATE
            .block("lime_jar", p -> new JarBlock())
            .item(JarItem::new)
            .build()
            .register();

    public static final BlockEntry<JarBlock> GREEN_JAR = CreateFactory.REGISTRATE
            .block("green_jar", p -> new JarBlock())
            .item(JarItem::new)
            .build()
            .register();

    public static final BlockEntry<JarBlock> CYAN_JAR = CreateFactory.REGISTRATE
            .block("cyan_jar", p -> new JarBlock())
            .item(JarItem::new)
            .build()
            .register();

    public static final BlockEntry<JarBlock> LIGHT_BLUE_JAR = CreateFactory.REGISTRATE
            .block("light_blue_jar", p -> new JarBlock())
            .item(JarItem::new)
            .build()
            .register();

    public static final BlockEntry<JarBlock> BLUE_JAR = CreateFactory.REGISTRATE
            .block("blue_jar", p -> new JarBlock())
            .item(JarItem::new)
            .build()
            .register();

    public static final BlockEntry<JarBlock> PURPLE_JAR = CreateFactory.REGISTRATE
            .block("purple_jar", p -> new JarBlock())
            .item(JarItem::new)
            .build()
            .register();

    public static final BlockEntry<JarBlock> MAGENTA_JAR = CreateFactory.REGISTRATE
            .block("magenta_jar", p -> new JarBlock())
            .item(JarItem::new)
            .build()
            .register();

    public static final BlockEntry<JarBlock> PINK_JAR = CreateFactory.REGISTRATE
            .block("pink_jar", p -> new JarBlock())
            .item(JarItem::new)
            .build()
            .register();

    public static final BlockEntry<JarBlock> BROWN_JAR = CreateFactory.REGISTRATE
            .block("brown_jar", p -> new JarBlock())
            .item(JarItem::new)
            .build()
            .register();

    public static final BlockEntry<JarBlock> GRAY_JAR = CreateFactory.REGISTRATE
            .block("gray_jar", p -> new JarBlock())
            .item(JarItem::new)
            .build()
            .register();

    public static final BlockEntry<JarBlock> LIGHT_GRAY_JAR = CreateFactory.REGISTRATE
            .block("light_gray_jar", p -> new JarBlock())
            .item(JarItem::new)
            .build()
            .register();

    public static final BlockEntry<JarBlock> BLACK_JAR = CreateFactory.REGISTRATE
            .block("black_jar", p -> new JarBlock())
            .item(JarItem::new)
            .build()
            .register();

    public static final Map<DyeColor, BlockEntry<JarBlock>> JARS = new EnumMap<>(DyeColor.class) {{
        put(DyeColor.WHITE, WHITE_JAR);
        put(DyeColor.RED, RED_JAR);
        put(DyeColor.ORANGE, ORANGE_JAR);
        put(DyeColor.YELLOW, YELLOW_JAR);
        put(DyeColor.LIME, LIME_JAR);
        put(DyeColor.GREEN, GREEN_JAR);
        put(DyeColor.CYAN, CYAN_JAR);
        put(DyeColor.LIGHT_BLUE, LIGHT_BLUE_JAR);
        put(DyeColor.BLUE, BLUE_JAR);
        put(DyeColor.PURPLE, PURPLE_JAR);
        put(DyeColor.MAGENTA, MAGENTA_JAR);
        put(DyeColor.PINK, PINK_JAR);
        put(DyeColor.BROWN, BROWN_JAR);
        put(DyeColor.GRAY, GRAY_JAR);
        put(DyeColor.LIGHT_GRAY, LIGHT_GRAY_JAR);
        put(DyeColor.BLACK, BLACK_JAR);
    }};

    public static final BlockEntry<JarBlock> JAR = JARS.get(DyeColor.WHITE);

    public static void register() {}
};

