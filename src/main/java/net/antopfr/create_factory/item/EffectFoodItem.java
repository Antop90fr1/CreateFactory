package net.antopfr.create_factory.item;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class EffectFoodItem extends Item {

    public EffectFoodItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);

        FoodProperties food = stack.getItem().getFoodProperties(stack, null);

        if (food == null) return;

        for (FoodProperties.PossibleEffect possibleEffect : food.effects()) {
            MobEffectInstance effect = possibleEffect.effect();
            MutableComponent name = Component.translatable(effect.getDescriptionId());

            if (effect.getAmplifier() > 0) {
                name = Component.translatable("potion.withAmplifier", name, Component.translatable("potion.potency." + effect.getAmplifier())
                );
            }
            if (effect.getDuration() > 20) {
                name = Component.translatable("potion.withDuration", name, MobEffectUtil.formatDuration(effect, 1.0F, context.tickRate()));
            }
            tooltip.add(
                    name.withStyle(effect.getEffect().value().getCategory().getTooltipFormatting())
            );
        }
    }
}
