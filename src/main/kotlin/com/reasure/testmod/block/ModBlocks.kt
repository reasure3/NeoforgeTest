package com.reasure.testmod.block

import com.reasure.testmod.TestMod
import com.reasure.testmod.item.ModItems
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockBehaviour
import net.neoforged.neoforge.registries.DeferredBlock
import net.neoforged.neoforge.registries.DeferredRegister

// THIS LINE IS REQUIRED FOR USING PROPERTY DELEGATES
import thedarkcolour.kotlinforforge.neoforge.forge.getValue

object ModBlocks {
    val BLOCKS = DeferredRegister.createBlocks(TestMod.ID)

    // If you get an "overload resolution ambiguity" error, include the arrow at the start of the closure.
    val EXAMPLE_BLOCK by registerBlock("example_block") { ->
        Block(BlockBehaviour.Properties.of().lightLevel { 15 }.strength(3.0f))
    }

    private fun <T: Block> registerBlock(name: String, block: () -> T): DeferredBlock<T> {
        val registry = BLOCKS.register(name, block)
        ModItems.ITEMS.register(name) { ->
            BlockItem(registry.get(), Item.Properties())
        }
        return registry
    }
}
