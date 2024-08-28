package com.reasure.testmod.datagen.client

import com.reasure.testmod.block.ModBlocks
import com.reasure.testmod.item.ModItems
import com.reasure.testmod.locale.TranslationKeys
import net.minecraft.data.PackOutput

class ModEnLangProvider(output: PackOutput): ModBaseLangProvider(output, "en_us") {
    override fun addTranslations() {
        add(TranslationKeys.TEST_TAB, "Test")
        add(ModBlocks.EXAMPLE_BLOCK, "Example Block")
        add(ModItems.TEST_ITEM, "Test Item")
        add(ModItems.TEST_EXPERT_ITEM, "Test Expert Item")
        add(ModItems.TEST_MASTER_ITEM, "Test Master Item")
    }
}