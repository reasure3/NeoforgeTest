package com.reasure.testmod.client.color

import net.neoforged.api.distmarker.Dist
import net.neoforged.api.distmarker.OnlyIn
import kotlin.math.roundToInt

@OnlyIn(Dist.CLIENT)
open class ColorData(var r: Int = 255, var g: Int = 255, var b: Int = 255) {
    open fun toInterpolationInt(prevData: ColorData, partialTick: Float): Int {
        val red = (prevData.r + (this.r - prevData.r) * partialTick).roundToInt()
        val green = (prevData.g + (this.g - prevData.g) * partialTick).roundToInt()
        val blue = (prevData.b + (this.b - prevData.b) * partialTick).roundToInt()
        return (red shl 16) or (green shl 8) or blue
    }

    fun copy(data: ColorData) {
        r = data.r
        g = data.g
        b = data.b
    }
}

@OnlyIn(Dist.CLIENT)
class FieryRedColorData(g: Int = 0) : ColorData(255, g, 0) {
    override fun toInterpolationInt(prevData: ColorData, partialTick: Float): Int {
        val green = (prevData.g + (this.g - prevData.g) * partialTick).roundToInt()
        return 0xFF0000 or (green shl 8)
    }
}
