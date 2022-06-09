package us.skyywastaken.hypixelapi.arcade.event;

import net.minecraftforge.fml.common.eventhandler.Event;
import us.skyywastaken.hypixelapi.arcade.game.ArcadeGame;

public class ArcadeGameEndEvent extends Event {
    public final ArcadeGame ENDED_GAME;

    public ArcadeGameEndEvent(ArcadeGame passedEndedGame) {
        this.ENDED_GAME = passedEndedGame;
    }

}
