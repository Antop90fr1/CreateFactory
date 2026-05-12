package net.antopfr.create_factory.jar;

import net.minecraft.core.RegistryAccess;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandlerItem;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;


public class JarItemFluidHandler implements IFluidHandlerItem {

    private final ItemStack stack;
    private final FluidTank tank;

    public JarItemFluidHandler(ItemStack stack) {
        this.stack = stack;
        this.tank = new FluidTank(JarBlockEntity.CAPACITY);

        CustomData data = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
        CompoundTag tag = data.copyTag();
        if (tag.contains("Tank")) {
            tank.readFromNBT(RegistryAccess.fromRegistryOfRegistries(
                    BuiltInRegistries.REGISTRY), tag.getCompound("Tank"));
        }
    }

    private void save() {
        CustomData existing = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
        CompoundTag tag = existing.copyTag();
        tag.put("Tank", tank.writeToNBT(RegistryAccess.fromRegistryOfRegistries(
                BuiltInRegistries.REGISTRY), new CompoundTag()));
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
    }

    @Override
    public ItemStack getContainer() {
        return stack;
    }

    @Override
    public int getTanks() { return 1; }

    @Override
    public FluidStack getFluidInTank(int tank) { return this.tank.getFluid(); }

    @Override
    public int getTankCapacity(int tank) { return JarBlockEntity.CAPACITY; }

    @Override
    public boolean isFluidValid(int tank, FluidStack fluid) { return true; }

    @Override
    public int fill(FluidStack resource, FluidAction action) {
        int filled = tank.fill(resource, action);
        if (action.execute()) save();
        return filled;
    }

    @Override
    public FluidStack drain(FluidStack resource, FluidAction action) {
        FluidStack limited = new FluidStack(resource.getFluid(), Math.min(resource.getAmount(), 1000));
        FluidStack drained = tank.drain(limited, action);
        if (action.execute()) save();
        return drained;
    }

    @Override
    public FluidStack drain(int maxDrain, FluidAction action) {
        FluidStack drained = tank.drain(Math.min(maxDrain, 1000), action);
        if (action.execute()) save();
        return drained;
    }
}