package net.antopfr.create_factory.jei;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.math.Axis;
import com.simibubi.create.AllFluids;
import com.simibubi.create.compat.jei.category.animations.AnimatedKinetics;
import com.simibubi.create.content.fluids.transfer.FillingRecipe;
import com.simibubi.create.foundation.gui.AllGuiTextures;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.antopfr.create_factory.CreateFactory;
import net.antopfr.create_factory.jar.JarBlockEntity;
import net.antopfr.create_factory.registry.CFBlockEntities;
import net.antopfr.create_factory.registry.CFBlocks;
import net.antopfr.create_factory.registry.CFFluids;
import net.createmod.catnip.gui.element.GuiGameElement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;

import static net.antopfr.create_factory.util.CFUtil.getJarIcon;

public class JarDippingCategory implements IRecipeCategory<RecipeHolder<FillingRecipe>> {

    public static final RecipeType<RecipeHolder<FillingRecipe>> RECIPE_TYPE =
            RecipeType.create(CreateFactory.MOD_ID, "jar_dipping",
                    (Class<RecipeHolder<FillingRecipe>>) (Class<?>) RecipeHolder.class);

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawable slot;

    public JarDippingCategory(IGuiHelper guiHelper) {
        ItemStack jarIcon = getJarIcon(CFBlocks.YELLOW_JAR, AllFluids.HONEY, 4000);

        this.background = guiHelper.createBlankDrawable(170, 70);
        this.icon = guiHelper.createDrawableItemStack(jarIcon);
        this.slot = guiHelper.drawableBuilder(
                ResourceLocation.fromNamespaceAndPath("create", "textures/gui/jei/widgets.png"),
                0, 0, 18, 18
        ).build();
    }

    @Override
    public RecipeType<RecipeHolder<FillingRecipe>> getRecipeType() {
        return RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("jei.create_factory.jar_dipping");
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<FillingRecipe> recipeHolder,
                          IFocusGroup focuses) {
        FillingRecipe recipe = recipeHolder.value();

        // item input
        builder.addSlot(RecipeIngredientRole.INPUT, 51, 5)
                .setBackground(slot, -1, -1)
                .addIngredients(recipe.getIngredients().get(0));

        // fluid input
        FluidStack recipeFluid = recipe.getRequiredFluid().ingredient().getStacks().length > 0
                ? recipe.getRequiredFluid().ingredient().getStacks()[0]
                : FluidStack.EMPTY;
        builder.addSlot(RecipeIngredientRole.INPUT, 27, 42)
                .setBackground(slot, -1, -1)
                .setFluidRenderer(recipe.getRequiredFluid().amount(), false, 16, 16)
                .addFluidStack(recipeFluid.getFluid(), recipe.getRequiredFluid().amount());

        // item output
        builder.addSlot(RecipeIngredientRole.OUTPUT, 132, 42)
                .setBackground(slot, -1, -1)
                .addItemStack(recipe.getResultItem(
                        Minecraft.getInstance().level.registryAccess()));
    }

    @Override
    public void draw(RecipeHolder<FillingRecipe> recipeHolder, IRecipeSlotsView recipeSlotsView,
                     GuiGraphics graphics, double mouseX, double mouseY) {
        FillingRecipe recipe = recipeHolder.value();

        AllGuiTextures.JEI_SHADOW.render(graphics, 62, 47);
        AllGuiTextures.JEI_DOWN_ARROW.render(graphics, 74, 10);

        FluidStack recipeFluid = recipe.getRequiredFluid().ingredient().getStacks().length > 0
                ? recipe.getRequiredFluid().ingredient().getStacks()[0]
                : FluidStack.EMPTY;

        DyeColor[] colors = DyeColor.values();
        int index = (int) ((System.currentTimeMillis() / 1000) % colors.length);
        BlockState jarState = CFBlocks.JARS.get(colors[index]).get().defaultBlockState();

        PoseStack ms = graphics.pose();
        ms.pushPose();
        ms.translate(65, 51, 100);
        ms.mulPose(Axis.XP.rotationDegrees(-15.5f));
        ms.mulPose(Axis.YP.rotationDegrees(22.5f));


        JarBlockEntity tempBE = new JarBlockEntity(
                CFBlockEntities.JAR.get(),
                BlockPos.ZERO,
                jarState
        );
        if (!recipeFluid.isEmpty()) {
            tempBE.getTank().fill(
                    new FluidStack(recipeFluid.getFluid(), recipe.getRequiredFluid().amount()),
                    IFluidHandler.FluidAction.EXECUTE
            );
        }

        GuiGameElement.of(jarState, tempBE)
                .lighting(AnimatedKinetics.DEFAULT_LIGHTING)
                .scale(35)
                .render(graphics);
        ms.popPose();
    }
}