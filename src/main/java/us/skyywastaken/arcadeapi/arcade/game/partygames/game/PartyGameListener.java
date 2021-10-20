package us.skyywastaken.arcadeapi.arcade.game.partygames.game;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import us.skyywastaken.arcadeapi.event.TeamUpdateEvent;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class PartyGameListener {
    private final static Pattern PLACE_REGEX = Pattern.compile("^ +((1st)|(2nd)|(3rd)) +\\(.+");
    int i = 0;
    @SubscribeEvent
    public void onMessageReceived(ClientChatReceivedEvent e) {
        String unformattedText = StringUtils.stripControlCodes(e.message.getUnformattedText());
        if(!PLACE_REGEX.matcher(unformattedText).matches()) {
            return;
        }
    }

    @SubscribeEvent
    public void onGameNameTeamUpdated(TeamUpdateEvent updateEvent) {
        if(updateEvent.TEAM_UPDATE_TYPE == TeamUpdateEvent.TeamUpdateType.ADD_ENTITY
                || updateEvent.TEAM_UPDATE_TYPE == TeamUpdateEvent.TeamUpdateType.REMOVE_ENTITY) {
            return;
        }
        if(updateEvent.TEAM_NAME.equals("team_12")) {
            ArrayList<String> updateStrings = new ArrayList<String>();
            updateStrings.add("----Update " + i++ + "----");
            updateStrings.add("Prefix: " + updateEvent.TEAM_PREFIX);
            updateStrings.add("Suffix: " + updateEvent.TEAM_SUFFIX);
            updateStrings.add("\n");
            for(String currentString : updateStrings) {
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(currentString));
            }
        }
    }
}
