package moe.ruabbit

import net.mamoe.mirai.console.MiraiConsole
import net.mamoe.mirai.console.plugin.PluginManager.INSTANCE.enable
import net.mamoe.mirai.console.plugin.PluginManager.INSTANCE.load
import net.mamoe.mirai.console.terminal.MiraiConsoleTerminalLoader
import moe.ruabbit.mirai.jrrp.PluginMain as JrrpPluginMain
import moe.ruabbit.mirai.deptool.PluginMain as DevtoolPluginMain


suspend fun main() {
    MiraiConsoleTerminalLoader.startAsDaemon()

    JrrpPluginMain.load()
    DevtoolPluginMain.load()
    JrrpPluginMain.enable()
    DevtoolPluginMain.enable()


//    测试请在此处添加qq号，或者手动登录
//    提交代码请注意删除账号信息避免泄露
    /*val bot = MiraiConsole.addBot(123456, "") {
        fileBasedDeviceInfo()
    }.alsoLogin()*/

    MiraiConsole.job.join()
}
