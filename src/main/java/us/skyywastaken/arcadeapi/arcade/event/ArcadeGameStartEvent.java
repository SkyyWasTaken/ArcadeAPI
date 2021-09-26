package us.skyywastaken.arcadeapi.arcade.event;

import net.minecraftforge.fml.common.eventhandler.Event;
import us.skyywastaken.arcadeapi.arcade.game.ArcadeGame;

public class ArcadeGameStartEvent extends Event {
    public final ArcadeGame STARTED_GAME;

    public ArcadeGameStartEvent(ArcadeGame startedGame) {
        this.STARTED_GAME = startedGame;
    }
}
