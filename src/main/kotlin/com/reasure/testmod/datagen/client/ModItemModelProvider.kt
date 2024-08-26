package com.reasure.testmod.datagen.client

import com.reasure.testmod.TestMod
import com.reasure.testmod.block.ModBlocks
import com.reasure.testmod.item.ModItems
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.data.PackOutput
import net.minecraft.world.level.block.Block
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder
import net.neoforged.neoforge.client.model.generators.ItemModelProvider
import net.neoforged.neoforge.common.data.ExistingFileHelper

class ModItemModelProvider(output: PackOutput, exFileHelper: ExistingFileHelper) :
    ItemModelProvider(output, TestMod.ID, exFileHelper) {
    override fun registerModels() {
        blockItem(ModBlocks.EXAMPLE_BLOCK)
        basicItem(ModItems.TEST_ITEM)
    }

    private fun blockItem(item: Block): ItemModelBuilder {
        val name = BuiltInRegistries.BLOCK.getKey(item).path
        return withExistingParent(name, modLoc("block/${name}"))
    }
}