package net.antopfr.create_factory.client;

import net.antopfr.create_factory.CreateFactory;
import net.antopfr.create_factory.jar.renderer.JarItemRenderer;
import net.antopfr.create_factory.jar.renderer.JarRenderer;
import net.antopfr.create_factory.registry.CFBlocks;
import net.antopfr.create_factory.registry.CFBlockEntities;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

@EventBusSubscriber(modid = CreateFactory.MOD_ID, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(
                new IClientItemExtensions() {
                    @Override
                    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return JarItemRenderer.getInstance();
                    }
                },
                CFBlocks.WHITE_JAR.asItem(),
                CFBlocks.RED_JAR.asItem(),
                CFBlocks.ORANGE_JAR.asItem(),
                CFBlocks.YELLOW_JAR.asItem(),
                CFBlocks.LIME_JAR.asItem(),
                CFBlocks.GREEN_JAR.asItem(),
                CFBlocks.CYAN_JAR.asItem(),
                CFBlocks.LIGHT_BLUE_JAR.asItem(),
                CFBlocks.BLUE_JAR.asItem(),
                CFBlocks.PURPLE_JAR.asItem(),
                CFBlocks.MAGENTA_JAR.asItem(),
                CFBlocks.PINK_JAR.asItem(),
                CFBlocks.BROWN_JAR.asItem(),
                CFBlocks.GRAY_JAR.asItem(),
                CFBlocks.LIGHT_GRAY_JAR.asItem(),
                CFBlocks.BLACK_JAR.asItem()
        );
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(CFBlockEntities.JAR.get(), JarRenderer::new);
    }
}
