package net.antopfr.create_factory.jei;

import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.content.fluids.transfer.FillingRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.antopfr.create_factory.CreateFactory;
import net.antopfr.create_factory.registry.CFBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.List;

@JeiPlugin
public class JarDippingJeiPlugin implements IModPlugin {

    public static final ResourceLocation PLUGIN_ID =
            ResourceLocation.fromNamespaceAndPath(CreateFactory.MOD_ID, "jei_plugin");

    @Override
    public ResourceLocation getPluginUid() {
        return PLUGIN_ID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(
                new JarDippingCategory(registration.getJeiHelpers().getGuiHelper())
        );
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        CFBlocks.JARS.values().forEach(jar ->
                registration.addRecipeCatalyst(
                        new ItemStack(jar.asItem()),
                        JarDippingCategory.RECIPE_TYPE
                )
        );
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        List<RecipeHolder<FillingRecipe>> recipes = Minecraft.getInstance()
                .level
                .getRecipeManager()
                .getAllRecipesFor(AllRecipeTypes.FILLING.getType());

        registration.addRecipes(JarDippingCategory.RECIPE_TYPE, recipes);
    }
}
