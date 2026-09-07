package xyz.thewhitedog9487.client

import dev.isxander.yacl3.config.v2.api.ConfigClassHandler
import dev.isxander.yacl3.config.v2.api.SerialEntry
import kotlinx.io.IOException
import net.fabricmc.loader.api.FabricLoader
import java.nio.file.FileSystems
import java.nio.file.Path
import java.nio.file.StandardWatchEventKinds
import java.nio.file.StandardWatchEventKinds.*

const val CurrentConfigurationVersionCode: Long = 0
class Settings {
    @SerialEntry(comment =
        """
        配置文件版本号
        用于在配置文件结构发生变化且需要手动干预时进行兼容性处理
        非必要情况下你不应该手动修改这个数据，否则可能导致本Mod的配置文件出现损坏或其他问题
        类型：整数
        取值范围：0 ~ Long.MAX_VALUE
        当前值：0
        """)
    var ConfigurationVersionCode = CurrentConfigurationVersionCode

    @SerialEntry(comment =
        """
        是否启用本模组的功能
        类型：布尔值
        取值范围：true false
        默认值：true
        """)
    var ModEnabled = true

    @SerialEntry(comment =
        """
        是否在悬停信息中显示键位提供者的ID
        类型：布尔值
        取值范围：true false
        默认值：false
        """)
    var ShowModID = false

    @SerialEntry(comment =
        """
        是否在悬停信息中显示键位本身的ID
        类型：布尔值
        取值范围：true false
        默认值：false
        """)
    var ShowKeybindID = false

    @SerialEntry(comment =
        """
        是否显示调试信息
        这些信息用于出现和本Mod相关的问题时进行排查，一般情况下不需要开启
        类型：布尔值
        取值范围：true false
        默认值：false
        """)
    var ShowDebugInfo = false }

var SettingsHandler: ConfigClassHandler<Settings>? = null

val ConfigFilePath = FabricLoader
    .getInstance()
    .configDir
    .resolve(WhoProvideThisKeybindModClient.FriendlyModID + ".json5")
    .toAbsolutePath()

@Volatile var SettingsInstance = Settings()

//    region 配置文件变更监听器
fun RegisterConfigFileReloadWatcher() {
    // 由GPT 5.4 Mini生成
    Thread.startVirtualThread {
        WhoProvideThisKeybindModClient.ClientLogger.info("正在为${ConfigFilePath}注册配置文件变更监听器...")
        val ConfigDirectory = ConfigFilePath.parent
        try {
            val WatchService = FileSystems.getDefault().newWatchService()
            ConfigDirectory.register(WatchService, ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE)
            while (!Thread.currentThread().isInterrupted) {
                val WatchKey = WatchService.take()
                var ShouldReload = false

                for (EventInstance in WatchKey.pollEvents()) {
                    val EventKind = EventInstance.kind()
                    if (EventKind == StandardWatchEventKinds.OVERFLOW) {
                        continue }
                    val ChangedPath = EventInstance.context() as Path?
                    if (ChangedPath != null && ChangedPath == ConfigFilePath.fileName) {
                        ShouldReload = true } }
                if (ShouldReload) {
                    SettingsHandler!!.load()
                    SettingsInstance = SettingsHandler!!.instance()
                    WhoProvideThisKeybindModClient.ClientLogger.info("检测到配置文件变更，已重新加载设置。") }
                if (!WatchKey.reset()) {
                    break } }
        } catch (e: InterruptedException) {
            Thread.currentThread().interrupt()
        } catch (e: IOException) {
            throw RuntimeException(e) } } }
//    endregion