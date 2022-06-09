package us.skyywastaken.hypixelapi.arcade.event;

import net.minecraftforge.fml.common.eventhandler.Event;
import us.skyywastaken.hypixelapi.arcade.game.ArcadeGame;

public class ArcadeGameLeaveEvent extends Event {
    // Important: this event can be fired before an arcade game starts. If the game phase is pregame, this could be a thing.
    public final ArcadeGame LEFT_GAME;

    public ArcadeGameLeaveEvent(ArcadeGame passedGame) {
        this.LEFT_GAME = passedGame;
    }
}
