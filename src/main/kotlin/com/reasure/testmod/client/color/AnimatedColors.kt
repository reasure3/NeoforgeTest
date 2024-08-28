package com.reasure.testmod.client.color

import net.neoforged.api.distmarker.Dist
import net.neoforged.api.distmarker.OnlyIn
import kotlin.math.max
import kotlin.math.min

@OnlyIn(Dist.CLIENT)
object AnimatedColors {
    /**
     * 매 프레임당 호출
     *
     * 보간법을 사용해서 컬러 계산
     */
    fun updateFrame(partialTick: Float) {
        Rainbow.updateFrame(partialTick)
        FieryRed.updateFrame(partialTick)
    }

    /**
     * 클라이언트 사이드에서 1틱에 한번 실행
     *
     * 20 프레임
     */
    fun updateTick() {
        Rainbow.updateTick()
        FieryRed.updateTick()
    }

    @OnlyIn(Dist.CLIENT)
    object Rainbow {
        private var state = State.INCREASE_GREEN
        private val colorData = ColorData(255, 0, 0)
        private val nextColorData = ColorData(255, 21, 0)
        var color = 0xFF0000

        internal fun updateFrame(partialTick: Float) {
            color = nextColorData.toInterpolationInt(colorData, partialTick)
        }

        internal fun updateTick() {
            colorData.copy(nextColorData)
            when (state) {
                State.INCREASE_GREEN -> {
                    nextColorData.g = min(nextColorData.g + 21, 255)
                    if (nextColorData.g == 255) state = State.DECREASE_RED
                }

                State.DECREASE_RED -> {
                    nextColorData.r = max(nextColorData.r - 21, 0)
                    if (nextColorData.r == 0) state = State.INCREASE_BLUE
                }

                State.INCREASE_BLUE -> {
                    nextColorData.b = min(nextColorData.b + 21, 255)
                    if (nextColorData.b == 255) state = State.DECREASE_GREEN
                }

                State.DECREASE_GREEN -> {
                    nextColorData.g = max(nextColorData.g - 21, 0)
                    if (nextColorData.g == 0) state = State.INCREASE_RED
                }

                State.INCREASE_RED -> {
                    nextColorData.r = min(nextColorData.r + 21, 255)
                    if (nextColorData.r == 255) state = State.DECREASE_BLUE
                }

                State.DECREASE_BLUE -> {
                    nextColorData.b = max(nextColorData.b - 21, 0)
                    if (nextColorData.b == 0) state = State.INCREASE_GREEN
                }
            }
        }

        enum class State {
            INCREASE_GREEN, DECREASE_RED, INCREASE_BLUE, DECREASE_GREEN, INCREASE_RED, DECREASE_BLUE
        }
    }

    @OnlyIn(Dist.CLIENT)
    object FieryRed {
        private var state = State.INCREASE_GREEN
        private val colorData = FieryRedColorData()
        private val nextColorData = FieryRedColorData(30)
        var color = 0xFF0000

        internal fun updateFrame(partialTick: Float) {
            color = nextColorData.toInterpolationInt(colorData, partialTick)
        }

        internal fun updateTick() {
            colorData.copy(nextColorData)
            when (state) {
                State.INCREASE_GREEN -> {
                    nextColorData.g = min(nextColorData.g + 30, 200)
                    if (nextColorData.g == 200) state = State.DECREASE_GREEN
                }

                State.DECREASE_GREEN -> {
                    nextColorData.g = max(nextColorData.g - 30, 0)
                    if (nextColorData.g == 0) state = State.INCREASE_GREEN
                }
            }
        }

        enum class State {
            INCREASE_GREEN, DECREASE_GREEN
        }
    }
}