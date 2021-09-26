package us.skyywastaken.arcadeapi.arcade.game.hideandseek;

import us.skyywastaken.arcadeapi.arcade.game.ArcadeGame;
import us.skyywastaken.arcadeapi.arcade.game.hypixelsays.HypixelSays;

import java.util.List;
import java.util.regex.Pattern;

public class HideAndSeek implements ArcadeGame {
    private final Pattern END_GAME_REGEX = Pattern.compile("^ +Winner:.+");
    public HideAndSeek() {

    }

    public HideAndSeek(HideAndSeek passedHideAndSeek) {

    }

    @Override
    public List<Object> getListeners() {
        return null;
    }

    @Override
    public String getScoreboardDisplayName() {
        return "HIDE AND SEEK";
    }

    @Override
    public String getInternalScoreboardName() {
        return "HideAndSeek";
    }

    @Override
    public Pattern getEndGameRegexPattern() {
        return this.END_GAME_REGEX;
    }

    @Override
    public ArcadeGame getCopy() {
        return new HideAndSeek(this);
    }

    @Override
    public void reset() {

    }
}
