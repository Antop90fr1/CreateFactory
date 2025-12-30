package net.antopfr.create_factory;

import com.simibubi.create.foundation.data.CreateRegistrate;
import net.antopfr.create_factory.registry.CFFluids;
import net.antopfr.create_factory.registry.CFItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
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
            .icon(() -> CFItems.CHOCOLATE_WAFFLE.get().asItem().getDefaultInstance())
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

        CFItems.register();
        CFFluids.register();
        CREATIVE_MODE_TABS.register(modEventBus);
    }

    public static CreateRegistrate registrate() {return REGISTRATE;}
}
