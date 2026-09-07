package xyz.thewhitedog9487.client.mixin.Compatibility.Controlling;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.components.Button;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.thewhitedog9487.client.MiscellaneousKt;
import xyz.thewhitedog9487.client.SettingsKt;
import xyz.thewhitedog9487.client.WhoProvideThisKeybindModClient;

/**
 * <a href="https://modrinth.com/mod/controlling">Controlling</a>把原版的UI直接给换了，因此需要注入到修改之后的UI中
 *
 * @see xyz.thewhitedog9487.client.mixin.Compatibility.Controlling.MixinPlugin
 */
@Pseudo
@Mixin(targets = "com.blamejared.controlling.client.NewKeyBindsList$KeyEntry")
public abstract class NewKeyBindsListMixin {
    @Shadow @Final private KeyMapping key;
    @Shadow @Final private Button btnChangeKeyBinding;
    @Inject(method = "refreshEntry", at = @At("TAIL"))
    private void SetNewTooltipInControllingUI(final CallbackInfo ci) {
        if (SettingsKt.getSettingsInstance().getShowDebugInfo()) {
            WhoProvideThisKeybindModClient.INSTANCE.getClientLogger().debug("SetNewTooltipInControllingUI函数被调用，Key：{}", key.getName()); }
        MiscellaneousKt.SetNewTooltip(btnChangeKeyBinding, key); } }
