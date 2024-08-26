package com.reasure.testmod.item

import net.minecraft.core.component.DataComponents
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.MutableComponent
import net.minecraft.network.chat.Style
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Rarity
import net.minecraft.world.item.TooltipFlag

class TestItem(properties: Properties) : Item(properties) {
    override fun getName(stack: ItemStack): Component {
        val rarity = stack[DataComponents.RARITY] ?: Rarity.COMMON
        return (super.getName(stack) as MutableComponent).withColor(0xFF0000)
    }

    override fun appendHoverText(
        stack: ItemStack,
        context: TooltipContext,
        tooltipComponents: MutableList<Component>,
        tooltipFlag: TooltipFlag
    ) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag)
        val component1 = Component.literal("First")
            .append(
                Component.literal("second").withStyle(Style.EMPTY.withColor(0xFF0000).withStrikethrough(false))
            )
            .withStyle(Style.EMPTY.withColor(0xF00FF00).withStrikethrough(true))
        val component2 = Component.literal("[Outer]: ${component1.style}")
        val component3 = Component.literal("[Inner]: ${component1.siblings[0].style}")
        tooltipComponents.addAll(arrayOf(component1, component2, component3))
    }

}