package us.skyywastaken.hypixelapi.arcade;

import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import us.skyywastaken.hypixelapi.arcade.game.ArcadeGame;

public class ArcadeChatHandler {
    private final ArcadeHandler ARCADE_HANDLER;
    public ArcadeChatHandler(ArcadeHandler passedArcadeHandler) {
        this.ARCADE_HANDLER = passedArcadeHandler;
    }

    @SubscribeEvent
    public void onMessageReceived(ClientChatReceivedEvent e) {
        if(Arcade.getArcade().getCurrentGamePhase() != GamePhase.INGAME) {
            return;
        }
        String rawMessage = StringUtils.stripControlCodes(e.message.getUnformattedText());
        ArcadeGame currentGame = Arcade.getArcade().getCurrentGame();
        if (currentGame.getEndGameRegexPattern().matcher(rawMessage).matches()) {
            this.ARCADE_HANDLER.onGameEnd();
        }
    }
}
