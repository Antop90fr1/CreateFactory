package net.antopfr.create_factory;

import com.simibubi.create.foundation.data.CreateRegistrate;
import net.antopfr.create_factory.jar.JarItemFluidHandler;
import net.antopfr.create_factory.registry.CFBlocks;
import net.antopfr.create_factory.registry.CFBlockEntities;
import net.antopfr.create_factory.registry.CFFluids;
import net.antopfr.create_factory.registry.CFItems;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.ApiStatus;

@Mod(CreateFactory.MOD_ID)
public class CreateFactory {
    public static final String MOD_ID = "create_factory";

    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MOD_ID)
            .defaultCreativeTab((ResourceKey<CreativeModeTab>) null);

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CF_TAB = CREATIVE_MODE_TABS.register(MOD_ID, () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .icon(() -> {
                ItemStack stack = new ItemStack(CFBlocks.RED_JAR.asItem());
                CompoundTag fluidTag = new CompoundTag();
                fluidTag.putInt("amount", 4000);
                fluidTag.putString("id", CFFluids.SWEET_BERRIES_JAM.getId().toString());
                CompoundTag tankTag = new CompoundTag();
                tankTag.put("Fluid", fluidTag);
                CompoundTag root = new CompoundTag();
                root.put("Tank", tankTag);
                stack.set(DataComponents.CUSTOM_DATA, CustomData.of(root));
                return stack;
            })
            .title(Component.translatable("creativetab.createfactory_tab"))
            .displayItems((itemDisplayParameters, output) -> REGISTRATE.getAll(Registries.ITEM).forEach((item -> {
                output.accept(item.get());
            })))
            .build());

    @ApiStatus.Internal
    public static void register(IEventBus modEventBus) {
        CREATIVE_MODE_TABS.register(modEventBus);
    }

    public CreateFactory(IEventBus modEventBus, ModContainer modContainer) {
        REGISTRATE.registerEventListeners(modEventBus);
        modEventBus.addListener(CreateFactory::registerCapabilities);

        CFItems.register();
        CFFluids.register();
        CFBlockEntities.register();
        CFBlocks.register();
        CREATIVE_MODE_TABS.register(modEventBus);
    }

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(
                Capabilities.FluidHandler.BLOCK,
                CFBlockEntities.JAR.get(),
                (be, side) -> be.getTank()  // ← retournes-tu bien le tank pour TOUS les sides (y compris null) ?
        );
        event.registerItem(
                Capabilities.FluidHandler.ITEM,
                (stack, ctx) -> new JarItemFluidHandler(stack),

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

    public static CreateRegistrate registrate() {return REGISTRATE;}
}
