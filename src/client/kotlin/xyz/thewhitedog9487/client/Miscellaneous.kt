package xyz.thewhitedog9487.client

import net.fabricmc.loader.api.FabricLoader
import net.fabricmc.loader.api.ModContainer
import net.fabricmc.loader.api.metadata.ModMetadata
import net.minecraft.client.KeyMapping
import net.minecraft.client.gui.components.Button
import net.minecraft.client.gui.components.Tooltip
import net.minecraft.network.chat.Component
import kotlin.io.path.pathString

val VanillaMinecraftContainer: ModContainer = FabricLoader
    .getInstance()
    .getModContainer("minecraft")
    .get()

val KeyMappingToMod: HashMap<KeyMapping, ModInfo> = HashMap()

val KeyMapping.Provider: ModInfo get() {
    val KeyBindTranslationKey = name
    val CandidateModIds = KeyBindTranslationKey.split(".")
    for (CandidateModId in CandidateModIds) {
        val ModContainer = FabricLoader.getInstance().getModContainer(CandidateModId)
        if (ModContainer.isPresent) {
            return ModContainer.map(::ModInfo).get() } }
    if (SettingsInstance.SearchInStackTrace) {
        KeyMappingToMod[this]?.let { return it } }
    if (CandidateModIds.size == 1){
        return ModInfo(null) }
    return ModInfo(VanillaMinecraftContainer) }

data class ModInfo(val ModContainerInstance: ModContainer?) {
    val ID: String = ModContainerInstance?.metadata?.id ?: Component.translatable("special_provider_UNKNOWN").string
    val MetaData: ModMetadata? = ModContainerInstance?.metadata
    val DisplayName: String = MetaData?.name ?: Component.translatable("special_provider_UNKNOWN").string
    val HumanFriendlyName: Component = Component.translatableWithFallback("modmenu.nameTranslation." + (MetaData?.id ?: "unknown"), DisplayName)
    val FilePath: String = ModContainerInstance?.origin?.paths?.joinToString("\n") { it.pathString } ?: Component.translatable("special_string_UNKNOWN").string }

fun Button.SetNewTooltip(Key: KeyMapping) {
    if (SettingsInstance.ModEnabled == false) return

    val Mod = Key.Provider
    val NewTooltip = tooltip
        .get()
        ?.message
        ?.copy()
        ?.append(Component.literal("\n"))
        ?.append(Component.literal("\n"))
        ?: Component.empty()

    // 第一行：Mod的人类可读名称
    NewTooltip.append(Component.translatable("tooltip.provided_by",
        Mod.HumanFriendlyName))
    // 第二行：Mod的ID
    if (SettingsInstance.ShowModID) {
        NewTooltip.append(Component.literal("\n"))
            .append(Component.translatable("tooltip.provider_mod_id",
                Component.literal(Mod.ID))); }
    // 第三行：按键自身的ID
    if (SettingsInstance.ShowKeybindID) {
        NewTooltip.append(Component.literal("\n"))
            .append(Component.translatable("tooltip.key_id",
                Component.literal(Key.name))) }

    // 第四行：文件路径
    if (SettingsInstance.ShowFilePath) {
        NewTooltip.append(Component.literal("\n"))
            .append(Component.translatable("tooltip.mod_file_path", Mod.FilePath)) }

    setTooltip(Tooltip.create(NewTooltip)) }