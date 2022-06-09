package us.skyywastaken.hypixelapi.arcade.game.hypixelsays;

import us.skyywastaken.hypixelapi.arcade.game.ArcadeGame;

import java.util.List;
import java.util.regex.Pattern;

public class HypixelSays implements ArcadeGame{
    private final Pattern END_GAME_REGEX = Pattern.compile("^ +1st Place.+");
    public HypixelSays() {

    }

    public HypixelSays(HypixelSays passedHypixelSays) {

    }

    @Override
    public List<Object> getListeners() {
        return null;
    }

    @Override
    public String getScoreboardDisplayName() {
        return "HYPIXEL SAYS";
    }

    @Override
    public String getInternalScoreboardName() {
        return "SantaSays";
    }

    @Override
    public Pattern getEndGameRegexPattern() {
        return this.END_GAME_REGEX;
    }

    @Override
    public ArcadeGame getCopy() {
        return new HypixelSays(this);
    }

    @Override
    public void reset() {

    }
}
