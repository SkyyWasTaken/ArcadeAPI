package us.skyywastaken.hypixelapi.arcade.game.football;

import us.skyywastaken.hypixelapi.arcade.game.ArcadeGame;

import java.util.List;
import java.util.regex.Pattern;

public class Football implements ArcadeGame{
    private final Pattern END_GAME_REGEX = Pattern.compile("^ +Winner - .+");
    public Football() {

    }

    public Football(Football passedFootball) {

    }

    @Override
    public List<Object> getListeners() {
        return null;
    }

    @Override
    public String getScoreboardDisplayName() {
        return "FOOTBALL";
    }

    @Override
    public String getInternalScoreboardName() {
        return "Football";
    }

    @Override
    public Pattern getEndGameRegexPattern() {
        return this.END_GAME_REGEX;
    }

    @Override
    public ArcadeGame getCopy() {
        return new Football(this);
    }

    @Override
    public void reset() {

    }
}
