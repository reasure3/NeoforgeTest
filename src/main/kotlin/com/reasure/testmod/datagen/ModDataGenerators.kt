package com.reasure.testmod.datagen

import com.reasure.testmod.TestMod
import com.reasure.testmod.datagen.client.ModBlockStateProvider
import com.reasure.testmod.datagen.client.ModEnLangProvider
import com.reasure.testmod.datagen.client.ModItemModelProvider
import net.minecraft.data.loot.LootTableProvider
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.data.event.GatherDataEvent

@EventBusSubscriber(modid = TestMod.ID, bus = EventBusSubscriber.Bus.MOD)
object ModDataGenerators {
    @SubscribeEvent
    fun gatherData(event: GatherDataEvent) {
        with(event.generator) {
            val exFileHelper = event.existingFileHelper
            val output = this.packOutput
            val lookupProvider = event.lookupProvider

            addProvider(
                event.includeServer(),
                LootTableProvider(
                    output,
                    setOf(),
                    listOf(ModBlockLootTableProvider.entry()),
                    lookupProvider
                )
            )

            addProvider(
                event.includeClient(),
                ModBlockStateProvider(output, exFileHelper)
            )
            addProvider(event.includeClient(), ModItemModelProvider(output, exFileHelper))
            addProvider(event.includeClient(), ModEnLangProvider(output))
        }
    }
}