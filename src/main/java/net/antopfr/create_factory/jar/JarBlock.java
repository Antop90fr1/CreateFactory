package net.antopfr.create_factory.jar;

import com.mojang.serialization.MapCodec;
import com.simibubi.create.AllSoundEvents;
import com.simibubi.create.content.equipment.wrench.IWrenchable;
import com.simibubi.create.content.fluids.spout.FillingBySpout;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.antopfr.create_factory.registry.CFBlocks;
import net.antopfr.create_factory.registry.CFBlockEntities;
import net.antopfr.create_factory.registry.CFFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class JarBlock extends BaseEntityBlock implements IWrenchable {

    public static final VoxelShape SHAPE =
            Block.box(3.5, 0, 3.5, 12.5, 11, 12.5);

    public JarBlock() {
        super(Properties.of()
                .strength(0.3f)
                .sound(SoundType.GLASS)
                .noOcclusion()
                .pushReaction(PushReaction.NORMAL)
                .forceSolidOn());
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level,
                                           BlockPos pos, Player player, InteractionHand hand,
                                           BlockHitResult hit) {

        // dyeing
        if (stack.getItem() instanceof DyeItem dye) {
            if (!level.isClientSide()) {
                DyeColor color = dye.getDyeColor();
                BlockEntry<JarBlock> newJar = CFBlocks.JARS.get(color);
                if (newJar != null) {
                    BlockEntity be = level.getBlockEntity(pos);
                    CompoundTag data = new CompoundTag();
                    if (be instanceof JarBlockEntity jar) {
                        jar.saveAdditional(data, level.registryAccess());
                    }
                    level.setBlock(pos, newJar.get().defaultBlockState(), Block.UPDATE_ALL);
                    BlockEntity newBe = level.getBlockEntity(pos);
                    if (newBe instanceof JarBlockEntity newJarBe) {
                        newJarBe.loadAdditional(data, level.registryAccess());
                    }
                    if (!player.getAbilities().instabuild) stack.shrink(1);
                }
            }
            return ItemInteractionResult.sidedSuccess(level.isClientSide());
        }

        BlockEntity be = level.getBlockEntity(pos);
        if (!(be instanceof JarBlockEntity jar)) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }

        if (!level.isClientSide) {
            if (stack.getItem() instanceof BucketItem || stack.is(Items.BUCKET)) {
                // bucket
                FluidUtil.interactWithFluidHandler(player, hand, jar.getTank());

            } else if (stack.getItem() instanceof JarItem) {
                ItemStack single = stack.copyWithCount(1);

                FluidTank handTank = new FluidTank(JarBlockEntity.CAPACITY);
                CustomData handData = single.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
                CompoundTag handTag = handData.copyTag();
                if (handTag.contains("Tank")) {
                    handTank.readFromNBT(level.registryAccess(), handTag.getCompound("Tank"));
                }

                if (player.isShiftKeyDown()) {
                    // sneak
                    FluidStack drained = jar.getTank().drain(1000, IFluidHandler.FluidAction.SIMULATE);
                    if (!drained.isEmpty()) {
                        int filled = handTank.fill(drained, IFluidHandler.FluidAction.SIMULATE);
                        if (filled > 0) {
                            FluidStack actual = new FluidStack(drained.getFluid(), filled);
                            jar.getTank().drain(actual, IFluidHandler.FluidAction.EXECUTE);
                            handTank.fill(actual, IFluidHandler.FluidAction.EXECUTE);

                            CompoundTag newTag = handData.copyTag();
                            newTag.put("Tank", handTank.writeToNBT(level.registryAccess(), new CompoundTag()));
                            single.set(DataComponents.CUSTOM_DATA, CustomData.of(newTag));

                            stack.shrink(1);
                            if (!player.getInventory().add(single)) {
                                popResource(level, pos, single);
                            }
                        }
                    }
                } else {
                    // deposit
                    FluidStack available = handTank.drain(1000, IFluidHandler.FluidAction.SIMULATE);
                    if (!available.isEmpty()) {
                        int filled = jar.getTank().fill(available, IFluidHandler.FluidAction.SIMULATE);
                        if (filled > 0) {
                            FluidStack actual = new FluidStack(available.getFluid(), filled);
                            handTank.drain(actual, IFluidHandler.FluidAction.EXECUTE);
                            jar.getTank().fill(actual, IFluidHandler.FluidAction.EXECUTE);

                            CompoundTag newTag = handData.copyTag();
                            newTag.put("Tank", handTank.writeToNBT(level.registryAccess(), new CompoundTag()));
                            single.set(DataComponents.CUSTOM_DATA, CustomData.of(newTag));

                            stack.shrink(1);
                            if (!player.getInventory().add(single)) {
                                popResource(level, pos, single);
                            }
                        }
                    }
                }
            } else if (FillingBySpout.canItemBeFilled(level, stack)) {
                FluidStack fluidInJar = jar.getTank().getFluid();
                int requiredAmount = FillingBySpout.getRequiredAmountForItem(level, stack, fluidInJar);

                if (requiredAmount != -1 && fluidInJar.getAmount() >= requiredAmount) {
                    ItemStack result = FillingBySpout.fillItem(level, requiredAmount, stack, fluidInJar);
                    jar.setChanged();
                    level.sendBlockUpdated(pos, state, state, Block.UPDATE_ALL);

                    if (!result.isEmpty()) {

                        if (stack.isEmpty()) {
                            player.setItemInHand(hand, result);
                        } else {
                            if (!player.getInventory().add(result)) {
                                popResource(level, pos, result);
                            }
                        }

                        level.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1f, 1f);
                    }
                }
            } else {
                FluidUtil.interactWithFluidHandler(player, hand, jar.getTank());
            }
        }

        return ItemInteractionResult.sidedSuccess(level.isClientSide());
    }

    @Override
    public boolean canBeHydrated(@NotNull BlockState state, BlockGetter level, BlockPos pos,
                                 FluidState fluidState, BlockPos fluidPos) {
        return false;
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected VoxelShape getInteractionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return SHAPE;
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
        return true;
    }

    @Override
    protected float getShadeBrightness(BlockState state, BlockGetter level, BlockPos pos) {
        return 1.0f;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new JarBlockEntity(CFBlockEntities.JAR.get(), pos, state);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState();
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        BlockEntity be = level.getBlockEntity(pos);
        if (!level.isClientSide() && !player.getAbilities().instabuild && be instanceof JarBlockEntity jar) {
            popResource(level, pos, jar.toStack());
        }
        return super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    public InteractionResult onSneakWrenched(BlockState state, UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();

        if (!level.isClientSide()) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof JarBlockEntity jar) {
                ItemStack drop = jar.toStack();
                if (player != null) {
                    if (!player.getInventory().add(drop)) {
                        popResource(level, pos, drop);
                    }
                } else {
                    popResource(level, pos, drop);
                }
            }
            AllSoundEvents.WRENCH_REMOVE.playOnServer(level, pos);
            level.removeBlock(pos, false);
        }

        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state,
                            @Nullable LivingEntity placer, ItemStack stack) {

        super.setPlacedBy(level, pos, state, placer, stack);

        BlockEntity be = level.getBlockEntity(pos);
        if (!(be instanceof JarBlockEntity jar)) return;

        CustomData data = stack.get(DataComponents.CUSTOM_DATA);
        if (data == null) return;

        jar.loadAdditional(data.copyTag(), level.registryAccess());
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        BlockEntity be = level.getBlockEntity(pos);
        if (!(be instanceof JarBlockEntity jar)) return;

        FluidStack fluid = jar.getTank().getFluid();
        if (fluid.isEmpty() || !fluid.getFluid().isSame(CFFluids.GLOW_BERRIES_JAM.get())) return;

        if (random.nextFloat() < 0.3f) {
            double x = pos.getX() + random.triangle(0.5, 1.0);
            double y = pos.getY() + random.triangle(0.5, 1.0);
            double z = pos.getZ() + random.triangle(0.5, 1.0);

            level.addParticle(
                    ParticleTypes.WAX_ON,
                    x, y, z,
                    0.01, 0.02, 0.01
            );
        }
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof JarBlockEntity jar) {
            FluidStack fluid = jar.getTank().getFluid();
            if (!fluid.isEmpty() && fluid.getFluid().isSame(CFFluids.GLOW_BERRIES_JAM.get())) {
                return 15;
            }
        }
        return 0;
    }
}
