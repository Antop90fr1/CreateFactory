package net.antopfr.create_factory.registry;

import com.simibubi.create.AllFluids;
import com.simibubi.create.AllTags;
import com.simibubi.create.foundation.data.CreateRegistrate;
import net.antopfr.create_factory.CreateFactory;
import com.tterrag.registrate.util.entry.FluidEntry;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public class CFFluids {
    private static final CreateRegistrate REGISTRATE = CreateFactory.registrate();

    public static final FluidEntry<ForgeFlowingFluid.Flowing> SWEET_BERRIES_JAM =
            REGISTRATE.fluid("sweet_berries_jam",
                            new ResourceLocation("create_factory","fluid/sweet_berries_jam_still"),
                            new ResourceLocation("create_factory","fluid/sweet_berries_jam_flow"),
                            NoColorFluidAttributes::new)
                    .lang("Sweet Berries Jam")
                    .properties(b -> b.viscosity(2000)
                            .density(1400))
                    .tag(AllTags.forgeFluidTag("jam"))
                    .fluidProperties(p -> p.levelDecreasePerBlock(2)
                            .tickRate(25)
                            .slopeFindDistance(3)
                            .explosionResistance(100f))
                    .source(ForgeFlowingFluid.Source::new)
                    .bucket()
                    .build()
                    .register();

    public static void register() {
    }

    private static class NoColorFluidAttributes extends AllFluids.TintedFluidType {

        public NoColorFluidAttributes(Properties properties, ResourceLocation stillTexture,
                                      ResourceLocation flowingTexture) {
            super(properties, stillTexture, flowingTexture);
        }

        @Override
        protected int getTintColor(FluidStack stack) {
            return NO_TINT;
        }

        @Override
        public int getTintColor(FluidState state, BlockAndTintGetter world, BlockPos pos) {
            return 0x00ffffff;
        }

    }
}
