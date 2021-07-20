package moe.ruabbit.mirai.chat

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.request.*
import moe.ruabbit.mirai.chat.storage.ChatConfig
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.subscribeMessages

object PluginMain : KotlinPlugin(
    JvmPluginDescription(
        id = "moe.ruabbit.mirai.chat",
        name = "chat",
        version = "0.1.0"
    )
) {
    val client = HttpClient(OkHttp)

    override fun onEnable() {
        ChatConfig.reload()
        GlobalEventChannel.subscribeMessages {
            atBot{
                subject.sendMessage(getDataByGet(message.contentToString()))
            }
        }
    }

    override fun onDisable() {
        // 安全退出httpclient客户端，防止堵塞进程
        client.close()
    }

    private suspend fun getDataByGet(params: String): String {
        return client.get("http://i.itpk.cn/api.php?question=$params&api_key=${ChatConfig.ApiKey}&api_secret=${ChatConfig.ApiSecret}")
    }
}


