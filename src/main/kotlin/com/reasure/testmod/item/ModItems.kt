package com.reasure.testmod.item

import com.reasure.testmod.TestMod
import net.minecraft.world.item.Item
import net.neoforged.neoforge.registries.DeferredRegister

// THIS LINE IS REQUIRED FOR USING PROPERTY DELEGATES
import thedarkcolour.kotlinforforge.neoforge.forge.getValue

object ModItems {
    val ITEMS: DeferredRegister.Items = DeferredRegister.createItems(TestMod.ID)

    val TEST_ITEM: TestItem by ITEMS.register("test_item") { -> TestItem(Item.Properties()) }
    val TEST_EXPERT_ITEM: Item by ITEMS.register("test_expert_item") { ->
        Item(Item.Properties().rarity(ModRarity.EXPERT))
    }
    val TEST_MASTER_ITEM: Item by ITEMS.register("test_master_item") { ->
        Item(Item.Properties().rarity(ModRarity.MASTER))
    }
}