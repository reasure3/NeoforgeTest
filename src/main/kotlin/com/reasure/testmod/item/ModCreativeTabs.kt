package com.reasure.testmod.item

import com.reasure.testmod.TestMod
import com.reasure.testmod.block.ModBlocks
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.neoforged.neoforge.registries.DeferredRegister

// THIS LINE IS REQUIRED FOR USING PROPERTY DELEGATES
import thedarkcolour.kotlinforforge.neoforge.forge.getValue

object ModCreativeTabs {
    val CREATIVE_TABS: DeferredRegister<CreativeModeTab> =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TestMod.ID)

    val TEST_TAB: CreativeModeTab by CREATIVE_TABS.register("test_tab") { ->
        CreativeModeTab.builder()
            .icon { ItemStack(ModBlocks.EXAMPLE_BLOCK) }
            .title(Component.literal("Test Tab"))
            .displayItems { _, entries ->
                ModItems.ITEMS.entries
                    .map { it.get() }
                    .forEach { entries.accept { it } }
            }
            .withSearchBar()
            .build()
    }
}