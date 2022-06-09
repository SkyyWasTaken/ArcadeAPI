package us.skyywastaken.hypixelapi.arcade.event;

import net.minecraftforge.fml.common.eventhandler.Event;
import us.skyywastaken.hypixelapi.arcade.game.ArcadeGame;

public class ArcadeGameLobbyJoinEvent extends Event {
    public final ArcadeGame JOINED_GAME;

    public ArcadeGameLobbyJoinEvent(ArcadeGame joinedGame) {
        this.JOINED_GAME = joinedGame;
    }
}
