package net.antopfr.create_factory.jar.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.foundation.fluid.FluidRenderer;
import net.antopfr.create_factory.jar.JarBlockEntity;
import net.createmod.catnip.render.FluidRenderHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidStack;

import java.util.function.Function;

public class JarItemRenderer extends BlockEntityWithoutLevelRenderer {

    private static JarItemRenderer instance;

    public static JarItemRenderer getInstance() {
        if (instance == null) instance = new JarItemRenderer();
        return instance;
    }

    private JarItemRenderer() {
        super(
                Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                Minecraft.getInstance().getEntityModels()
        );
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext displayContext,
                             PoseStack ms, MultiBufferSource buffer, int light, int overlay) {

        CustomData data = stack.get(DataComponents.CUSTOM_DATA);

        Minecraft mc = Minecraft.getInstance();


        ms.pushPose();

        Block block = ((BlockItem) stack.getItem()).getBlock();
        mc.getBlockRenderer().renderSingleBlock(
                block.defaultBlockState(),
                ms, buffer, light, overlay
        );
        ms.popPose();

        if (data == null) return;
        CompoundTag tag = data.copyTag();
        if (!tag.contains("Tank")) return;

        FluidStack fluidStack = FluidStack.parseOptional(
                mc.player.registryAccess(),
                tag.getCompound("Tank").getCompound("Fluid")
        );

        if (fluidStack.isEmpty()) return;

        float fill = (float) fluidStack.getAmount() / JarBlockEntity.CAPACITY;
        float min = 4.5f / 16f;
        float max = 11.5f / 16f;
        float minY = 0.5f / 16f;
        float maxY = minY + (fill * (8.35f / 16f));

        Fluid fluid = fluidStack.getFluid();
        IClientFluidTypeExtensions clientFluid = IClientFluidTypeExtensions.of(fluid);
        Function<ResourceLocation, TextureAtlasSprite> atlas =
                Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS);

        TextureAtlasSprite still = atlas.apply(clientFluid.getStillTexture(fluidStack));
        TextureAtlasSprite flowing = atlas.apply(clientFluid.getFlowingTexture(fluidStack));
        int color = clientFluid.getTintColor(fluidStack);

        var builder = FluidRenderHelper.getFluidBuilder(buffer);

        FluidRenderHelper.renderStillTiledFace(Direction.UP, min, min, max, max, maxY, builder, ms, light, color, still);
        FluidRenderHelper.renderStillTiledFace(Direction.DOWN, min, min, max, max, minY, builder, ms, light, color, still);
        FluidRenderer.renderFlowingTiledFace(Direction.NORTH, min, minY, max, maxY, min, builder, ms, light, color, flowing);
        FluidRenderer.renderFlowingTiledFace(Direction.SOUTH, min, minY, max, maxY, max, builder, ms, light, color, flowing);
        FluidRenderer.renderFlowingTiledFace(Direction.WEST, min, minY, max, maxY, min, builder, ms, light, color, flowing);
        FluidRenderer.renderFlowingTiledFace(Direction.EAST, min, minY, max, maxY, max, builder, ms, light, color, flowing);
    }
}
