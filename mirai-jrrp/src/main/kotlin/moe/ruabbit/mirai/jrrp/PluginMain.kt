package moe.ruabbit.mirai.jrrp

import moe.ruabbit.jrrp.UserData
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.console.plugin.version
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.subscribeMessages
import net.mamoe.mirai.message.data.LightApp
import net.mamoe.mirai.utils.info
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object PluginMain : KotlinPlugin(
    JvmPluginDescription(
        id = "moe.ruabbit.mirai.Jrrp",
        name = "jrrp",
        version = "0.1.0"
    )
) {
    override fun onEnable() {
        // 用户数据加载
        UserData.reload()

        GlobalEventChannel.subscribeMessages {
            always {
                if (message.contentToString() == ".jrrp" || message.contentToString() == "。jrrp" || message.contentToString() == "今日人品") {
                    // 创建miniapp类
                    val miniapp = Miniclass(sender,senderName)
                    val userdata = jrrpRandom(sender.id)
                    miniapp.adddata(0,"今日人品",userdata.jrrp.toString())
                    miniapp.adddata(1,"今日适宜",userdata.suitable)

                    subject.sendMessage(LightApp(miniapp.toLightapp()))

                }
            }
        }
    logger.info { "今日人品 Version：$version 插件加载完成" }
    }

    // 生成今日人品并返回
    private fun jrrpRandom(id: Long): UserData.Userjrrp {
        //获取时间
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.BASIC_ISO_DATE
        val formatted = current.format(formatter)
        //日期变更清空数据
        if (UserData.time != formatted){
            UserData.time = formatted
            UserData.jrrpmap.clear()
        }
        //返回已经生成的今日人品
        UserData.jrrpmap[id]?.let {
            return it
        }

        val suitable:List<String> = listOf("女装","打游戏","逛街","抽卡","氪金","学习","睡觉","修仙","看番","强化")
        val jrrp = UserData.Userjrrp((1..100).random(), suitable.random())
        UserData.jrrpmap[id] = jrrp
        return jrrp
    }

}



