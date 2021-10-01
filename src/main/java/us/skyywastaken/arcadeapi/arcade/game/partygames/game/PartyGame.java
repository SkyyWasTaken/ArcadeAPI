package us.skyywastaken.arcadeapi.arcade.game.partygames.game;

import us.skyywastaken.arcadeapi.arcade.game.ArcadeGame;

import java.util.List;
import java.util.regex.Pattern;

public interface PartyGame {
    List<Object> getListeners();
    String getGameName();
    ArcadeGame getCopy();
    void reset();
}
