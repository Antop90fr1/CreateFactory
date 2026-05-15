package net.antopfr.create_factory.util;

import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.FluidEntry;
import net.antopfr.create_factory.jar.JarBlock;
import net.antopfr.create_factory.jar.JarItemFluidHandler;
import net.antopfr.create_factory.registry.CFBlockEntities;
import net.antopfr.create_factory.registry.CFBlocks;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;

public class CFUtil {
    public static ItemStack getJarIcon(BlockEntry<JarBlock> jar, FluidEntry<BaseFlowingFluid.Flowing> fluid, int amount) {
        ItemStack stack = new ItemStack(jar.asItem());
        CompoundTag fluidTag = new CompoundTag();
        fluidTag.putInt("amount", amount);
        fluidTag.putString("id", fluid.getId().toString());
        CompoundTag tankTag = new CompoundTag();
        tankTag.put("Fluid", fluidTag);
        CompoundTag root = new CompoundTag();
        root.put("Tank", tankTag);
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(root));
        return stack;
    }

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(
                Capabilities.FluidHandler.BLOCK,
                CFBlockEntities.JAR.get(),
                (be, side) -> be.getTank()
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
}
