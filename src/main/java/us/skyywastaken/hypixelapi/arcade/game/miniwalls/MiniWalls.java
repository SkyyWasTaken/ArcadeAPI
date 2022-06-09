package us.skyywastaken.hypixelapi.arcade.game.miniwalls;

import us.skyywastaken.hypixelapi.arcade.game.ArcadeGame;

import java.util.List;
import java.util.regex.Pattern;

public class MiniWalls implements ArcadeGame {
    private final Pattern END_GAME_REGEX = Pattern.compile("^ + Winning Team - .+");
    public MiniWalls() {

    }

    public MiniWalls(MiniWalls passedMiniWalls) {

    }

    @Override
    public List<Object> getListeners() {
        return null;
    }

    @Override
    public String getScoreboardDisplayName() {
        return "MINI WALLS";
    }

    @Override
    public String getInternalScoreboardName() {
        return "Mini Walls";
    }

    @Override
    public Pattern getEndGameRegexPattern() {
        return this.END_GAME_REGEX;
    }

    @Override
    public ArcadeGame getCopy() {
        return new MiniWalls(this);
    }

    @Override
    public void reset() {

    }
}
