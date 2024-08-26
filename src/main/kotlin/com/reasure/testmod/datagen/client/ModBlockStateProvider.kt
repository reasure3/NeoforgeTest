package com.reasure.testmod.datagen.client

import com.reasure.testmod.TestMod
import com.reasure.testmod.block.ModBlocks
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.client.model.generators.BlockStateProvider
import net.neoforged.neoforge.common.data.ExistingFileHelper

class ModBlockStateProvider(output: PackOutput, exFileHelper: ExistingFileHelper) :
    BlockStateProvider(output, TestMod.ID, exFileHelper) {

    override fun registerStatesAndModels() {
        simpleBlock(ModBlocks.EXAMPLE_BLOCK)
    }
}