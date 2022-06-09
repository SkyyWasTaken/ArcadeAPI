package us.skyywastaken.hypixelapi.arcade.game.partygames;

import net.minecraftforge.common.MinecraftForge;
import us.skyywastaken.hypixelapi.arcade.game.partygames.event.PartyGameChangeEvent;
import us.skyywastaken.hypixelapi.arcade.game.partygames.event.PartyGameEndEvent;
import us.skyywastaken.hypixelapi.arcade.game.partygames.game.PartyGame;

public class PartyGameManager {
    private final boolean IS_COPY;
    private PartyGame currentPartyGame = null;

    public PartyGameManager() {
        this.IS_COPY = false;
    }

    public PartyGameManager(PartyGameManager passedManager) {
        this.IS_COPY = true;
        this.currentPartyGame = passedManager.currentPartyGame;
    }

    void onPartyGameChange(PartyGame newPartyGame) {
        if (this.IS_COPY) {
            return;
        }
        postGameChangeEvent(newPartyGame);
        registerPartyGameEvents(newPartyGame);
        this.currentPartyGame = newPartyGame;
    }

    private void postGameChangeEvent(PartyGame newGame) {
        PartyGame lastPartyGame = currentPartyGame.getCopy();
        PartyGame newPartyGameCopy = newGame.getCopy();
        PartyGameChangeEvent startEvent = new PartyGameChangeEvent(lastPartyGame, newPartyGameCopy);
        MinecraftForge.EVENT_BUS.post(startEvent);
    }

    private void registerPartyGameEvents(PartyGame partyGame) {
        for (Object currentListener : partyGame.getListeners()) {
            MinecraftForge.EVENT_BUS.register(currentListener);
        }
    }


    void onPartyGameEnd() {
        if (this.IS_COPY) {
            return;
        }
        PartyGameEndEvent endEvent = new PartyGameEndEvent(currentPartyGame.getCopy());
        MinecraftForge.EVENT_BUS.post(endEvent);
        unregisterCurrentGameListeners();
        this.currentPartyGame.reset();
        this.currentPartyGame = null;
    }

    private void unregisterCurrentGameListeners() {
        for (Object currentListener : currentPartyGame.getListeners()) {
            MinecraftForge.EVENT_BUS.unregister(currentListener);
        }
    }

    public PartyGame getCurrentPartyGame() {
        return this.currentPartyGame.getCopy();
    }

    PartyGameManager getCopy() {
        return new PartyGameManager(this);
    }

    void reset() {
        unregisterCurrentGameListeners();
    }
}
