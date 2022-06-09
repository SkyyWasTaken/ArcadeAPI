package us.skyywastaken.hypixelapi.arcade.game.partygames.event;

import us.skyywastaken.hypixelapi.arcade.game.partygames.game.PartyGame;

public class PartyGameStartEvent {
    public final PartyGame NEW_PARTY_GAME;

    public PartyGameStartEvent(PartyGame passedGame) {
        this.NEW_PARTY_GAME = passedGame;
    }
}
