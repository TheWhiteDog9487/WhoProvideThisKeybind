package xyz.thewhitedog9487.client.mixin;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.client.KeyMapping;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.include.com.google.common.io.Files;
import xyz.thewhitedog9487.client.MiscellaneousKt;
import xyz.thewhitedog9487.client.ModInfo;
import xyz.thewhitedog9487.client.WhoProvideThisKeybindModClient;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

@Mixin(KeyMapping.class)
public class KeyMappingMixin {
    @Inject(method = "<init>(Ljava/lang/String;Lcom/mojang/blaze3d/platform/InputConstants$Type;ILnet/minecraft/client/KeyMapping$Category;I)V",
            at = @At("TAIL"))
    void DiscoverStackTrace(String name, InputConstants.Type type, int value, KeyMapping.Category category, int order, CallbackInfo ci) throws IOException, ClassNotFoundException {
        var StackTraceCLassNames = Arrays.stream(Thread.currentThread().getStackTrace())
                .map(StackTraceElement::getClassName)
                .toList();
        for (String ClassName : StackTraceCLassNames) {
            if (IsFrameworkClass(ClassName)) continue;
            for (String PartOfClassName : ClassName.split("\\.")) {
                Optional<ModContainer> CandidateModContainer = FabricLoader.getInstance().getModContainer(PartOfClassName);
                if (CandidateModContainer.isPresent() && CandidateModContainer.get() != MiscellaneousKt.getVanillaMinecraftContainer()) {
                    var ModInfo = new ModInfo(CandidateModContainer.get());
                    MiscellaneousKt.getKeyMappingToMod().put((KeyMapping) (Object) this, ModInfo);
                    return; } } } }

    @Unique
    boolean IsFrameworkClass(@NotNull String ClassName) {
        return ClassName.startsWith("java.")
                || ClassName.startsWith("jdk.")
                || ClassName.startsWith("sun.")
                || ClassName.startsWith("com.sun.")
                || ClassName.startsWith("kotlin.")
                || ClassName.startsWith("net.minecraft")
                || ClassName.startsWith("net.fabricmc")
                || ClassName.startsWith("org.spongepowered.")

                // ↓ 第三方Mod兼容条目
                || ClassName.startsWith("dev.tr7zw.transition"); } }
