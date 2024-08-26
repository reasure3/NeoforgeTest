package com.reasure.testmod.datagen

import com.reasure.testmod.block.ModBlocks
import net.minecraft.core.HolderLookup
import net.minecraft.data.loot.BlockLootSubProvider
import net.minecraft.data.loot.LootTableProvider.SubProviderEntry
import net.minecraft.world.flag.FeatureFlags
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets

class ModBlockLootTableProvider(registries: HolderLookup.Provider) :
    BlockLootSubProvider(setOf(), FeatureFlags.REGISTRY.allFlags(), registries) {
    override fun generate() {
        dropSelf(ModBlocks.EXAMPLE_BLOCK)
    }

    override fun getKnownBlocks(): MutableIterable<Block> =
        ModBlocks.BLOCKS.entries.map { it.value() }.toMutableList()

    companion object {
        fun entry(): SubProviderEntry =
            SubProviderEntry(::ModBlockLootTableProvider, LootContextParamSets.BLOCK)
    }
}