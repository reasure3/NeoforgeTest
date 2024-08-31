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
    abstract class AnimatedColor(
        private val colorData: ColorData,
        private val nextColorData: ColorData,
    ) {
        protected abstract fun updateColor(nextColor: ColorData)
        var color = 0xFF0000
            private set

        internal fun updateFrame(partialTick: Float) {
            color = nextColorData.toInterpolationInt(colorData, partialTick)
        }

        internal fun updateTick() {
            colorData.copy(nextColorData)
            updateColor(nextColorData)
        }
    }

    @OnlyIn(Dist.CLIENT)
    object Rainbow : AnimatedColor(
        ColorData(255, 0, 0),
        ColorData(255, 21, 0)
    ) {
        private var state = State.INCREASE_GREEN

        override fun updateColor(nextColor: ColorData) {
            when (state) {
                State.INCREASE_GREEN -> {
                    nextColor.g = min(nextColor.g + 21, 255)
                    if (nextColor.g == 255) state = State.DECREASE_RED
                }

                State.DECREASE_RED -> {
                    nextColor.r = max(nextColor.r - 21, 0)
                    if (nextColor.r == 0) state = State.INCREASE_BLUE
                }

                State.INCREASE_BLUE -> {
                    nextColor.b = min(nextColor.b + 21, 255)
                    if (nextColor.b == 255) state = State.DECREASE_GREEN
                }

                State.DECREASE_GREEN -> {
                    nextColor.g = max(nextColor.g - 21, 0)
                    if (nextColor.g == 0) state = State.INCREASE_RED
                }

                State.INCREASE_RED -> {
                    nextColor.r = min(nextColor.r + 21, 255)
                    if (nextColor.r == 255) state = State.DECREASE_BLUE
                }

                State.DECREASE_BLUE -> {
                    nextColor.b = max(nextColor.b - 21, 0)
                    if (nextColor.b == 0) state = State.INCREASE_GREEN
                }
            }
        }

        enum class State {
            INCREASE_GREEN, DECREASE_RED, INCREASE_BLUE, DECREASE_GREEN, INCREASE_RED, DECREASE_BLUE
        }
    }

    @OnlyIn(Dist.CLIENT)
    object FieryRed : AnimatedColor(
        FieryRedColorData(),
        FieryRedColorData(30)
    ) {
        private var state = State.INCREASE_GREEN

        override fun updateColor(nextColor: ColorData) {
            when (state) {
                State.INCREASE_GREEN -> {
                    nextColor.g = min(nextColor.g + 30, 200)
                    if (nextColor.g == 200) state = State.DECREASE_GREEN
                }

                State.DECREASE_GREEN -> {
                    nextColor.g = max(nextColor.g - 30, 0)
                    if (nextColor.g == 0) state = State.INCREASE_GREEN
                }
            }
        }

        enum class State {
            INCREASE_GREEN, DECREASE_GREEN
        }
    }
}