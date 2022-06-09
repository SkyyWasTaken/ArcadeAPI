package us.skyywastaken.hypixelapi.arcade.game.partygames.event;

import net.minecraftforge.fml.common.eventhandler.Event;
import us.skyywastaken.hypixelapi.arcade.game.partygames.game.PartyGame;

public class PartyGameEndEvent extends Event {
    public final PartyGame ENDED_PARTY_GAME;

    public PartyGameEndEvent(PartyGame passedPartyGame) {
        this.ENDED_PARTY_GAME = passedPartyGame;
    }
}
