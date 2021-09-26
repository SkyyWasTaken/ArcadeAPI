package us.skyywastaken.arcadeapi;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import us.skyywastaken.arcadeapi.arcade.event.ArcadeGameEndEvent;
import us.skyywastaken.arcadeapi.arcade.event.ArcadeGameLeaveEvent;
import us.skyywastaken.arcadeapi.arcade.event.ArcadeGameLobbyJoinEvent;
import us.skyywastaken.arcadeapi.arcade.event.ArcadeGameStartEvent;
import us.skyywastaken.arcadeapi.event.ScoreboardObjectiveUpdateEvent;

public class TestClass {

    @SubscribeEvent
    public void onObjectiveCreation(ScoreboardObjectiveUpdateEvent e) {
        if(e.OBJECTIVE_EVENT_TYPE != ScoreboardObjectiveUpdateEvent.ObjectiveEventType.CREATE_OBJECTIVE) {
            return;
        }
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Objective created: " + e.OBJECTIVE_NAME));
    }

    @SubscribeEvent
    public void onLobbyJoined(ArcadeGameLobbyJoinEvent e) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("You just joined a " + e.JOINED_GAME.getScoreboardDisplayName() + " lobby!"));
    }

    @SubscribeEvent
    public void onGameStarted(ArcadeGameStartEvent e) {

        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(e.STARTED_GAME.getScoreboardDisplayName() + "has started!"));
    }

    @SubscribeEvent
    public void onGameExit(ArcadeGameLeaveEvent e) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Exit triggered: " + e.LEFT_GAME.getScoreboardDisplayName()));
    }

    @SubscribeEvent
    public void onGameEnd(ArcadeGameEndEvent e) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Game ended!"));
    }

}
