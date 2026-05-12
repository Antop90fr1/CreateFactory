package net.antopfr.create_factory.jar;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.fluids.FluidStack;

import java.util.List;

public class JarItem extends BlockItem {

    public JarItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();

        if (player != null && player.isShiftKeyDown()) {
            BlockState state = level.getBlockState(pos);
            if (state.getBlock() instanceof JarBlock) {
                ItemInteractionResult result = state.useItemOn(
                        context.getItemInHand(), level, player,
                        context.getHand(), new BlockHitResult(
                                context.getClickLocation(),
                                context.getClickedFace(),
                                pos, false
                        )
                );
                return result.result();
            }
        }

        return super.useOn(context);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context,
                                List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

        boolean shift = Screen.hasShiftDown();

        tooltipComponents.add(
                Component.empty()
                        .append(Component.translatable("tooltip.create_factory.jar.hold")
                                .withStyle(ChatFormatting.DARK_GRAY))
                        .append(Component.literal("Shift")
                                .withStyle(shift ? ChatFormatting.WHITE : ChatFormatting.GRAY))
                        .append(Component.translatable("tooltip.create_factory.jar.contents")
                                .withStyle(ChatFormatting.DARK_GRAY))
        );

        if (!shift) return;

        CustomData data = stack.get(DataComponents.CUSTOM_DATA);
        if (data == null) {
            tooltipComponents.add(Component.translatable("gui.goggles.fluid_container.empty")
                    .withStyle(ChatFormatting.GRAY));
            return;
        }

        CompoundTag tag = data.copyTag();
        if (!tag.contains("Tank")) return;

        FluidStack fluid = FluidStack.parseOptional(
                context.registries(),
                tag.getCompound("Tank").getCompound("Fluid")
        );

        tooltipComponents.add(Component.translatable("gui.goggles.fluid_container")
                .withStyle(ChatFormatting.WHITE));

        if (fluid.isEmpty()) {
            tooltipComponents.add(Component.translatable("gui.goggles.fluid_container.empty")
                    .withStyle(ChatFormatting.GRAY));
        } else {
            tooltipComponents.add(
                    Component.empty()
                            .append(Component.literal("  "))
                            .append(Component.literal(fluid.getHoverName().getString()))
                    .withStyle(ChatFormatting.GRAY));
            tooltipComponents.add(
                    Component.empty()
                            .append(Component.literal("  ")) // indentation
                            .append(Component.literal(fluid.getAmount() + "mB")
                                    .withStyle(ChatFormatting.GOLD))
                            .append(Component.literal(" / " + JarBlockEntity.CAPACITY + "mB")
                                    .withStyle(ChatFormatting.DARK_GRAY))
            );
        }
    }
}
