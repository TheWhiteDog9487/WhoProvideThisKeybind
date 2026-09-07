package xyz.thewhitedog9487.client

import net.fabricmc.api.ClientModInitializer
import org.slf4j.LoggerFactory
import xyz.thewhitedog9487.client.Event.RegisterClientLifecycleEvents

object WhoProvideThisKeybindModClient : ClientModInitializer {
    const val ModID: String = "whoprovidethiskeybind"
    const val FriendlyModID = "WhoProvideThisKeybind"
    val ClientLogger = LoggerFactory.getLogger(FriendlyModID)

    override fun onInitializeClient() {
        RegisterClientLifecycleEvents() } }