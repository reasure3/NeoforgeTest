package com.reasure.testmod.item

import com.reasure.testmod.TestMod
import net.minecraft.world.item.Item
import net.neoforged.neoforge.registries.DeferredRegister

// THIS LINE IS REQUIRED FOR USING PROPERTY DELEGATES
import thedarkcolour.kotlinforforge.neoforge.forge.getValue

object ModItems {
    val ITEMS: DeferredRegister.Items = DeferredRegister.createItems(TestMod.ID)

    val TEST_ITEM: TestItem by ITEMS.register("test") { -> TestItem(Item.Properties())}
}