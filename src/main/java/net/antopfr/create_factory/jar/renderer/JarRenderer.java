package net.antopfr.create_factory.jar.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.foundation.fluid.FluidRenderer;
import net.antopfr.create_factory.jar.JarBlockEntity;
import net.createmod.catnip.render.FluidRenderHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidStack;

import java.util.function.Function;

public class JarRenderer implements BlockEntityRenderer<JarBlockEntity> {

    public JarRenderer(BlockEntityRendererProvider.Context context) {}

    @Override
    public void render(JarBlockEntity be, float partialTicks, PoseStack ms, MultiBufferSource buffer, int light, int overlay) {

        FluidStack fluidStack = be.getTank().getFluid();

        if (fluidStack.isEmpty())
            return;

        float fill =
                (float) fluidStack.getAmount()
                        / be.getTank().getCapacity();

        float min = 4.5f / 16f;
        float max = 11.5f / 16f;

        float minY = 0.5f / 16f;
        float maxY = minY + (fill * (8.35f / 16f));

        Fluid fluid = fluidStack.getFluid();

        IClientFluidTypeExtensions clientFluid =
                IClientFluidTypeExtensions.of(fluid);

        Function<ResourceLocation, TextureAtlasSprite> atlas =
                Minecraft.getInstance()
                        .getTextureAtlas(InventoryMenu.BLOCK_ATLAS);

        TextureAtlasSprite still =
                atlas.apply(clientFluid.getStillTexture(fluidStack));

        TextureAtlasSprite flowing =
                atlas.apply(clientFluid.getFlowingTexture(fluidStack));

        int color = clientFluid.getTintColor(fluidStack);

        var builder = FluidRenderHelper.getFluidBuilder(buffer);

        // TOP
        FluidRenderHelper.renderStillTiledFace(Direction.UP, min, min, max, max, maxY, builder, ms, light, color, still);
        // BOTTOM
        FluidRenderHelper.renderStillTiledFace(Direction.DOWN, min, min, max, max, minY, builder, ms, light, color, still);
        // NORTH
        FluidRenderer.renderFlowingTiledFace(Direction.NORTH, min, minY, max, maxY, min, builder, ms, light, color, flowing);
        // SOUTH
        FluidRenderer.renderFlowingTiledFace(Direction.SOUTH, min, minY, max, maxY, max, builder, ms, light, color, flowing);
        // WEST
        FluidRenderer.renderFlowingTiledFace(Direction.WEST, min, minY, max, maxY, min, builder, ms, light, color, flowing);
        // EAST
        FluidRenderer.renderFlowingTiledFace(Direction.EAST, min, minY, max, maxY, max, builder, ms, light, color, flowing);
    }
}
