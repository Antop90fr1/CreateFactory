package net.antopfr.create_factory;

import com.simibubi.create.foundation.data.CreateRegistrate;
import net.antopfr.create_factory.registry.CFItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CreateFactory.MOD_ID)
public class CreateFactory
{
    public static final String MOD_ID = "create_factory";
    public static final String NAME = "Create: Factory";
    private static final CreateRegistrate REGISTRATE = CreateRegistrate.create(CreateFactory.MOD_ID);

    public CreateFactory()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;

        MinecraftForge.EVENT_BUS.register(this);

        CreateFactory.REGISTRATE.registerEventListeners(modEventBus);

        CFCreativeTab.register(modEventBus);
        CFItems.register();
    }

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static CreateRegistrate registrate() {
        return REGISTRATE;
    }
}