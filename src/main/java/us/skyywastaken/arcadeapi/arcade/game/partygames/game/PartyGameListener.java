package us.skyywastaken.arcadeapi.arcade.game.partygames.game;

import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.regex.Pattern;

public class PartyGameListener {
    private final static Pattern PLACE_REGEX = Pattern.compile("^ +((1st)|(2nd)|(3rd)) +\\(.+");
    @SubscribeEvent
    public void onMessageReceived(ClientChatReceivedEvent e) {
        String unformattedText = StringUtils.stripControlCodes(e.message.getUnformattedText());
        if(!PLACE_REGEX.matcher(unformattedText).matches()) {
            return;
        }
    }
}
