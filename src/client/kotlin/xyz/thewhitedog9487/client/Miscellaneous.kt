package xyz.thewhitedog9487.client

import net.fabricmc.loader.api.FabricLoader
import net.fabricmc.loader.api.ModContainer
import net.fabricmc.loader.api.metadata.ModMetadata
import net.minecraft.client.KeyMapping
import net.minecraft.client.gui.components.Button
import net.minecraft.client.gui.components.Tooltip
import net.minecraft.network.chat.Component

val VanillaMinecraftContainer: ModContainer = FabricLoader
    .getInstance()
    .getModContainer("minecraft")
    .get()

val KeyMapping.Provider: ModInfo get() {
    val KeyBindTranslationKey = name
    val CandidateModIds = KeyBindTranslationKey.split(".")
    if (CandidateModIds.size == 1){
        WhoProvideThisKeybindModClient.ClientLogger.warn("这是个什么东西，按键绑定ID只有一个部分，ID： $KeyBindTranslationKey")
        return ModInfo(null) }
    for (CandidateModId in CandidateModIds) {
        val ModContainer = FabricLoader.getInstance().getModContainer(CandidateModId)
        if (ModContainer.isPresent) {
            return ModContainer.map(::ModInfo).get() } }
    return ModInfo(VanillaMinecraftContainer) }

data class ModInfo(val ModContainerInstance: ModContainer?) {
    val ID: String = ModContainerInstance?.metadata?.id ?: Component.translatable("special_provider_UNKNOWN").string
    val MetaData: ModMetadata? = ModContainerInstance?.metadata
    val DisplayName: String = MetaData?.name ?: Component.translatable("special_provider_UNKNOWN").string
    val HumanFriendlyName: Component = Component.translatableWithFallback("modmenu.nameTranslation." + (MetaData?.id ?: "unknown"), DisplayName) }

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

    setTooltip(Tooltip.create(NewTooltip)) }