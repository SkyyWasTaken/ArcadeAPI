package us.skyywastaken.arcadeapi.arcade.game.partygames.game;

import java.util.List;

public interface PartyGame {
    List<Object> getListeners();

    String getGameName();

    String getGameStartText();

    PartyGame getCopy();

    void reset();
}
