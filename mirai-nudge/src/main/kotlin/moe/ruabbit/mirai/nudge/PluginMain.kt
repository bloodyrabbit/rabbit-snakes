package moe.ruabbit.mirai.nudge

import moe.ruabbit.mirai.nudge.storage.NudgeMessage
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.events.NudgeEvent

object PluginMain : KotlinPlugin(
    JvmPluginDescription(
        id = "moe.ruabbit.mirai.nudge",
        name = "nudge",
        version = "0.1.0"
    )
){
    override fun onEnable() {
        NudgeMessage.reload()

        GlobalEventChannel.subscribeAlways<NudgeEvent> {
            if (target.id == bot.id){
                subject.sendMessage(NudgeMessage.botnudge.random())
            }

            if (target.id == 2275072700){
                subject.sendMessage(NudgeMessage.rabbitnudge.random())
            }
        }

    }

    override fun onDisable() {

    }
}