package xyz.thewhitedog9487.client

import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi
import dev.isxander.yacl3.api.ConfigCategory
import dev.isxander.yacl3.api.Option
import dev.isxander.yacl3.api.OptionDescription
import dev.isxander.yacl3.api.YetAnotherConfigLib
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder
import net.minecraft.network.chat.Component

class ModMenuEntryPoint: ModMenuApi {
    override fun getModConfigScreenFactory() = ConfigScreenFactory { ParentScreen ->
        YetAnotherConfigLib.createBuilder()
            .title(Component.translatable("title.config"))
            .category {
                ConfigCategory.createBuilder()
                    .name(Component.translatable("config.category.general"))
                    .option {
                        Option.createBuilder<Boolean>()
                            .name(Component.translatable("option.general.ModEnabled"))
                            .description { OptionDescription.of(Component.translatable("option.general.ModEnabled.description") ) }
                            .binding( true, { SettingsInstance.ModEnabled }, { SettingsInstance.ModEnabled = it } )
                            .controller(BooleanControllerBuilder::create )
                            .build() }
                    .build() }
            .category {
                ConfigCategory.createBuilder()
                    .name(Component.translatable("config.category.feature"))
                    .option {
                        Option.createBuilder<Boolean>()
                            .name(Component.translatable("option.feature.ShowModID"))
                            .description { OptionDescription.of(Component.translatable("option.feature.ShowModID.description") ) }
                            .binding( false, { SettingsInstance.ShowModID }, { SettingsInstance.ShowModID = it } )
                            .controller(BooleanControllerBuilder::create )
                            .build() }
                    .option {
                        Option.createBuilder<Boolean>()
                            .name(Component.translatable("option.feature.ShowKeybindID"))
                            .description { OptionDescription.of(Component.translatable("option.feature.ShowKeybindID.description") ) }
                            .binding( false, { SettingsInstance.ShowKeybindID }, { SettingsInstance.ShowKeybindID = it } )
                            .controller(BooleanControllerBuilder::create )
                            .build() }
                    .option {
                        Option.createBuilder<Boolean>()
                            .name(Component.translatable("option.feature.ShowFilePath"))
                            .description { OptionDescription.of(Component.translatable("option.feature.ShowFilePath.description") ) }
                            .binding( false, { SettingsInstance.ShowFilePath }, { SettingsInstance.ShowFilePath = it } )
                            .controller(BooleanControllerBuilder::create )
                            .build() }
                    .build() }
            .category {
                ConfigCategory.createBuilder()
                    .name(Component.translatable("config.category.debug"))
                    .option {
                        Option.createBuilder<Boolean>()
                            .name(Component.translatable("option.debug.ShowDebugInfo"))
                            .description { OptionDescription.of(Component.translatable("option.debug.ShowDebugInfo.description") ) }
                            .binding( false, { SettingsInstance.ShowDebugInfo }, { SettingsInstance.ShowDebugInfo = it } )
                            .controller(BooleanControllerBuilder::create )
                            .build() }
                    .build() }
            .build()
            .generateScreen(ParentScreen) } }