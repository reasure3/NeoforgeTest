package com.reasure.testmod.item

import com.reasure.testmod.TestMod
import com.reasure.testmod.client.color.AnimatedColors.FieryRed
import com.reasure.testmod.client.color.AnimatedColors.Rainbow
import net.minecraft.network.chat.Style
import net.minecraft.world.item.Rarity
import net.neoforged.fml.common.asm.enumextension.EnumProxy
import net.neoforged.fml.loading.FMLEnvironment
import java.util.function.UnaryOperator
import kotlin.reflect.KProperty

object ModRarity {
    @JvmField
    val EXPERT_PROXY: EnumProxy<Rarity> = EnumProxy(
        Rarity::class.java, -12, "${TestMod.ID}:expert",
        UnaryOperator { style: Style ->
            if (FMLEnvironment.dist.isClient) style.withColor(Rainbow.color)
            else style.withColor(0x000000)
        }
    )

    @JvmField
    val MASTER_PROXY: EnumProxy<Rarity> = EnumProxy(
        Rarity::class.java, -13, "${TestMod.ID}:master",
        UnaryOperator { style: Style ->
            if (FMLEnvironment.dist.isClient) style.withColor(FieryRed.color)
            else style.withColor(0xFF0000)
        }
    )

    val EXPERT: Rarity by EXPERT_PROXY
    val MASTER: Rarity by MASTER_PROXY

    operator fun EnumProxy<Rarity>.getValue(any: Any?, property: KProperty<*>): Rarity {
        return getValue()
    }
}