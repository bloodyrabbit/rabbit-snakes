package moe.ruabbit.mirai.jrrp.storage

import kotlinx.serialization.Serializable
import net.mamoe.mirai.console.data.AutoSavePluginData
import net.mamoe.mirai.console.data.value

object UserData : AutoSavePluginData("JrrpData") {
    var time:String by value("")
    var jrrpmap: MutableMap<Long, Userjrrp> by value(mutableMapOf())

    @Serializable
    data class Userjrrp  (
        // 今日人品数值
        val jrrp:Int,
        // 今日适宜
        val suitable:String
    )
}
