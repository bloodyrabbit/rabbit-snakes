package moe.ruabbit.mirai.deptool

import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.console.plugin.version
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.subscribeMessages
import net.mamoe.mirai.message.code.MiraiCode.deserializeMiraiCode
import net.mamoe.mirai.message.data.LightApp
import net.mamoe.mirai.message.data.SimpleServiceMessage
import net.mamoe.mirai.message.data.time
import net.mamoe.mirai.message.nextMessage
import net.mamoe.mirai.utils.MiraiExperimentalApi
import net.mamoe.mirai.utils.info

object PluginMain : KotlinPlugin(
    JvmPluginDescription(
        id = "moe.ruabbit.mirai.devtool",
        name = "devtool",
        version = "0.1.0"
    )
) {
    @OptIn(MiraiExperimentalApi::class)
    override fun onEnable() {
        GlobalEventChannel.subscribeMessages {
            case("#Mirai取码") {
                subject.sendMessage("请在一分钟内发送需要取码的信息，返回Mirai码")
                val nextmsg = nextMessage()
                if (nextmsg.time - time < 60) {
                    if (nextmsg.isEmpty())
                        subject.sendMessage(nextmsg.toString())
                    else
                        subject.sendMessage(nextmsg.serializeToMiraiCode())
                }
            }

            case("#Mirai发码") {
                subject.sendMessage("请在一分钟内发送Mirai码")
                val nextmsg = nextMessage()
                if (nextmsg.time - time < 60)
                    subject.sendMessage(nextmsg.contentToString().deserializeMiraiCode())
            }

            case("#Json发码"){
                subject.sendMessage("请在一分钟内发送Json")
                val nextmsg = nextMessage()
                if (nextmsg.time - time < 60)
                    subject.sendMessage(LightApp(nextmsg.contentToString()))
            }

            case("#XML发码"){
                subject.sendMessage("请在一分钟内发送XML")
                val nextmsg = nextMessage()
                if (nextmsg.time - time < 60)
                    subject.sendMessage(SimpleServiceMessage(60,nextmsg.contentToString()))
            }
        }

        logger.info { "雪兔开发工具 verision：$version 加载完成" }
    }
}
