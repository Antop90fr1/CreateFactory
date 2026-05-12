package net.antopfr.create_factory.registry;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.antopfr.create_factory.CreateFactory;
import net.antopfr.create_factory.jar.JarBlockEntity;

public class CFBlockEntities {
    private static final CreateRegistrate REGISTRATE =
            CreateFactory.registrate();

    public static final BlockEntityEntry<JarBlockEntity> JAR =
            CreateFactory.REGISTRATE
                    .blockEntity("jar", JarBlockEntity::new)
                    .validBlocks(
                            CFBlocks.WHITE_JAR,
                            CFBlocks.RED_JAR,
                            CFBlocks.ORANGE_JAR,
                            CFBlocks.YELLOW_JAR,
                            CFBlocks.LIME_JAR,
                            CFBlocks.GREEN_JAR,
                            CFBlocks.CYAN_JAR,
                            CFBlocks.LIGHT_BLUE_JAR,
                            CFBlocks.BLUE_JAR,
                            CFBlocks.PURPLE_JAR,
                            CFBlocks.MAGENTA_JAR,
                            CFBlocks.PINK_JAR,
                            CFBlocks.BROWN_JAR,
                            CFBlocks.GRAY_JAR,
                            CFBlocks.LIGHT_GRAY_JAR,
                            CFBlocks.BLACK_JAR
                    )
                    .register();

    public static void register() {}
}