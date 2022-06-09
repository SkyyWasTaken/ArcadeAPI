package us.skyywastaken.hypixelapi.arcade.game.galaxywars;

import us.skyywastaken.hypixelapi.arcade.game.ArcadeGame;

import java.util.List;
import java.util.regex.Pattern;

public class GalaxyWars implements ArcadeGame {
    private final Pattern END_GAME_REGEX = Pattern.compile("^ +Winner:.+");
    public GalaxyWars() {

    }

    public GalaxyWars(GalaxyWars passedGalaxyWars) {

    }

    @Override
    public List<Object> getListeners() {
        return null;
    }

    @Override
    public String getScoreboardDisplayName() {
        return "GALAXY WARS";
    }

    @Override
    public String getInternalScoreboardName() {
        return "GALACTIC";
    }

    @Override
    public Pattern getEndGameRegexPattern() {
        return this.END_GAME_REGEX;
    }

    @Override
    public ArcadeGame getCopy() {
        return new GalaxyWars(this);
    }

    @Override
    public void reset() {

    }
}
