package us.skyywastaken.hypixelapi.arcade.game.partygames;

import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import us.skyywastaken.hypixelapi.arcade.game.partygames.game.PartyGame;
import us.skyywastaken.hypixelapi.event.TeamUpdateEvent;

import java.util.HashMap;
import java.util.regex.Pattern;

public class PartyGameListener {
    private final PartyGameManager PARTY_GAME_MANAGER;
    private final HashMap<String, PartyGame> PARTY_GAME_NAME_MAP = new HashMap<String, PartyGame>();
    private final static Pattern PLACE_REGEX = Pattern.compile("^ +((1st)|(2nd)|(3rd)) +\\(.+");

    public PartyGameListener(PartyGameManager passedPartyGameManager) {
        this.PARTY_GAME_MANAGER = passedPartyGameManager;
    }

    public void registerPartyGame(PartyGame gameToRegister) {
        this.PARTY_GAME_NAME_MAP.put(gameToRegister.getGameName(), gameToRegister);
    }

    @SubscribeEvent
    public void onScoreboardPartyGameUpdate(TeamUpdateEvent updateEvent) {
        if (!updateEvent.TEAM_NAME.equals("team_12")) {
            return;
        }
        String unformattedRow = StringUtils.stripControlCodes(updateEvent.TEAM_PREFIX + updateEvent.TEAM_SUFFIX);
        PartyGame newPartyGame = this.PARTY_GAME_NAME_MAP.get(unformattedRow);
        if (newPartyGame == null) {
            return;
        }
        this.PARTY_GAME_MANAGER.onPartyGameChange(newPartyGame);
    }


    @SubscribeEvent
    public void onMessageReceived(ClientChatReceivedEvent e) {
        String unformattedText = StringUtils.stripControlCodes(e.message.getUnformattedText());
        if (!PLACE_REGEX.matcher(unformattedText).matches()) {
            return;
        }
        this.PARTY_GAME_MANAGER.onPartyGameEnd();
    }
}
