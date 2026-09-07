package xyz.thewhitedog9487.client.mixin;

import xyz.thewhitedog9487.client.MiscellaneousKt;
import xyz.thewhitedog9487.client.SettingsKt;
import xyz.thewhitedog9487.client.WhoProvideThisKeybindModClient;
import xyz.thewhitedog9487.client.ModInfo;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.options.controls.KeyBindsList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 针对原版的实现
 */
@Mixin(KeyBindsList.KeyEntry.class)
public abstract class KeyBindsListKeyEntryMixin {
    @Shadow @Final private KeyMapping key;
    @Shadow @Final private Button changeButton;

    @Inject(method = "refreshEntry", at = @At("RETURN"))
    private void SetTooltip(CallbackInfo ci) {
        if (SettingsKt.getSettingsInstance().getShowDebugInfo()) {
            WhoProvideThisKeybindModClient.INSTANCE.getClientLogger().debug("SetTooltip函数被调用，Key：{}", key.getName()); }
        MiscellaneousKt.SetNewTooltip(changeButton, key); } }