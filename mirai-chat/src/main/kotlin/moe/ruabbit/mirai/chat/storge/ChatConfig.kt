package moe.ruabbit.mirai.chat.storge

import net.mamoe.mirai.console.data.ReadOnlyPluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

object ChatConfig: ReadOnlyPluginConfig("chatbot-config") {
    @ValueDescription("请到http://www.itpk.cn/  申请api")
    val ApiKey:String by value("")
    val ApiSecret by value("")
}