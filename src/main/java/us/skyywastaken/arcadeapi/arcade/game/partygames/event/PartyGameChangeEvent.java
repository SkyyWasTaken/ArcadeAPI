package us.skyywastaken.arcadeapi.arcade.game.partygames.event;

import net.minecraftforge.fml.common.eventhandler.Event;
import us.skyywastaken.arcadeapi.arcade.game.partygames.game.PartyGame;

public class PartyGameChangeEvent extends Event {
    public final PartyGame ENDED_PARTY_GAME;
    public final PartyGame NEW_PARTY_GAME;

    public PartyGameChangeEvent(PartyGame lastGame, PartyGame nextGame) {
        this.ENDED_PARTY_GAME = lastGame;
        this.NEW_PARTY_GAME = nextGame;
    }
}
