package moe.ruabbit.mirai.nudge.storage

import net.mamoe.mirai.console.data.ReadOnlyPluginConfig
import net.mamoe.mirai.console.data.value

object NudgeMessage: ReadOnlyPluginConfig("nudgemessage") {
    val botnudge: MutableList<String> by value(mutableListOf("别戳了，戳多了兔子会假孕的！"))
    val rabbitnudge: MutableList<String> by value(mutableListOf("再戳就变成小萝莉了"))
}