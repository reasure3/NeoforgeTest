package com.reasure.testmod.client.event

import com.reasure.testmod.TestMod
import com.reasure.testmod.client.color.AnimatedColors
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.client.event.ClientTickEvent
import net.neoforged.neoforge.client.event.RenderFrameEvent

@EventBusSubscriber(modid = TestMod.ID, bus = EventBusSubscriber.Bus.GAME, value = [Dist.CLIENT])
object GameClient {
    @SubscribeEvent
    fun clientTick(event: ClientTickEvent.Pre) {
        AnimatedColors.updateTick()
    }

    @SubscribeEvent
    fun onFrame(event: RenderFrameEvent.Pre) {
        AnimatedColors.updateFrame(event.partialTick.realtimeDeltaTicks)
    }
}