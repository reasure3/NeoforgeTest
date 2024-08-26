package com.reasure.testmod.datagen.client

import com.reasure.testmod.TestMod
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.common.data.LanguageProvider

abstract class ModBaseLangProvider(output: PackOutput, locale: String) : LanguageProvider(output, TestMod.ID, locale) {
    protected fun addConfigDesc(config: String, name: String) = add("${TestMod.ID}.configuration.$config", name)
}