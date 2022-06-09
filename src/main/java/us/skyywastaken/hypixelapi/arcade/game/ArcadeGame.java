package us.skyywastaken.hypixelapi.arcade.game;


import java.util.List;
import java.util.regex.Pattern;

public interface ArcadeGame {

    List<Object> getListeners();

    String getScoreboardDisplayName();

    String getInternalScoreboardName();

    Pattern getEndGameRegexPattern();

    ArcadeGame getCopy();

    void reset();
}