package xyz.thewhitedog9487.client.Event

import com.google.gson.GsonBuilder
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents
import net.minecraft.resources.Identifier
import xyz.thewhitedog9487.client.ConfigFilePath
import xyz.thewhitedog9487.client.CurrentConfigurationVersionCode
import xyz.thewhitedog9487.client.RegisterConfigFileReloadWatcher
import xyz.thewhitedog9487.client.Settings
import xyz.thewhitedog9487.client.SettingsHandler
import xyz.thewhitedog9487.client.SettingsInstance
import xyz.thewhitedog9487.client.WhoProvideThisKeybindModClient

fun RegisterClientLifecycleEvents() {
    ClientLifecycleEvents.CLIENT_STARTED.register { ClientInstance ->
        try {
            SettingsHandler = ConfigClassHandler
                .createBuilder(Settings::class.java)
                .id(Identifier.fromNamespaceAndPath(WhoProvideThisKeybindModClient.ModID, "settings_confighandler"))
                .serializer {
                    GsonConfigSerializerBuilder.create(it)
                        .setPath(ConfigFilePath)
                        .setJson5(true)
                        .appendGsonBuilder(GsonBuilder::setPrettyPrinting)
                        .build() }
                .build()

            WhoProvideThisKeybindModClient.ClientLogger.info("正在从${ConfigFilePath}加载设置...")
            SettingsHandler!!.load()
            SettingsInstance = SettingsHandler!!.instance()
            if (SettingsInstance.ConfigurationVersionCode != CurrentConfigurationVersionCode) {
                // 必要时手动处理升降级，现在不必要，就这么办了
                SettingsInstance.ConfigurationVersionCode = CurrentConfigurationVersionCode
                SettingsHandler!!.save() }
            RegisterConfigFileReloadWatcher() }
        catch (_: NoClassDefFoundError) {
            SettingsInstance = Settings()
            WhoProvideThisKeybindModClient.ClientLogger.warn("当前没有安装YetAnotherConfigLib，因此无法在调整和保持Mod配置") } }
    ClientLifecycleEvents.CLIENT_STOPPING.register { ClientInstance ->
        if (SettingsHandler == null) return@register
        WhoProvideThisKeybindModClient.ClientLogger.info("正在保存设置到$ConfigFilePath...")
        SettingsHandler!!.save() } }