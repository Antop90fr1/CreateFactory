package net.antopfr.create_factory;

import net.antopfr.create_factory.registry.CFItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CFCreativeTab {

    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CreateFactory.MOD_ID);

    public static final RegistryObject<CreativeModeTab> CF_TAB = TABS.register("createfactory_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(CFItems.CHOCOLATE_WAFFLE.get()))
                    .title(Component.translatable("creativetab.createfactory_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(CFItems.WAFFLE.get());
                        pOutput.accept(CFItems.UNCOOKED_WAFFLE.get());
                        pOutput.accept(CFItems.CAKE_PASTE.get());
                        pOutput.accept(CFItems.HONEY_ROLL.get());
                        pOutput.accept(CFItems.CHOCOLATE_ROLL.get());
                        pOutput.accept(CFItems.DARK_CHOCOLATE_ROLL.get());
                        pOutput.accept(CFItems.RUBY_CHOCOLATE_ROLL.get());
                        pOutput.accept(CFItems.WHITE_CHOCOLATE_ROLL.get());
                        pOutput.accept(CFItems.CARAMEL_ROLL.get());
                        pOutput.accept(CFItems.SWEET_BERRIES_JAM_ROLL.get());
                        pOutput.accept(CFItems.CARAMEL_WAFFLE.get());
                        pOutput.accept(CFItems.HONEY_BERRIES.get());
                        pOutput.accept(CFItems.CHOCOLATE_APPLE.get());
                        pOutput.accept(CFItems.DARK_CHOCOLATE_APPLE.get());
                        pOutput.accept(CFItems.RUBY_CHOCOLATE_APPLE.get());
                        pOutput.accept(CFItems.WHITE_CHOCOLATE_APPLE.get());
                        pOutput.accept(CFItems.CARAMEL_APPLE.get());
                        pOutput.accept(CFItems.HONEY_WAFFLE.get());
                        pOutput.accept(CFItems.CHOCOLATE_WAFFLE.get());
                        pOutput.accept(CFItems.DARK_CHOCOLATE_WAFFLE.get());
                        pOutput.accept(CFItems.RUBY_CHOCOLATE_WAFFLE.get());
                        pOutput.accept(CFItems.WHITE_CHOCOLATE_WAFFLE.get());
                        pOutput.accept(CFItems.CARAMEL_WAFFLE.get());
                        pOutput.accept(CFItems.SWEET_BERRIES_JAM_WAFFLE.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        TABS.register(eventBus);
    }
}
