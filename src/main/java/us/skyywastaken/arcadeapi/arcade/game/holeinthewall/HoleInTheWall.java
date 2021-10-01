package us.skyywastaken.arcadeapi.arcade.game.holeinthewall;

import us.skyywastaken.arcadeapi.arcade.game.ArcadeGame;
import us.skyywastaken.arcadeapi.arcade.game.hypixelsays.HypixelSays;

import java.util.List;
import java.util.regex.Pattern;

public class HoleInTheWall implements ArcadeGame {
    private final Pattern END_GAME_REGEX = Pattern.compile("^ +Hole In The Wall.+"); // Forgive me for I hath sinned
    public HoleInTheWall() {

    }

    public HoleInTheWall(HoleInTheWall passedHoleInTheWall) {

    }

    @Override
    public List<Object> getListeners() {
        return null;
    }

    @Override
    public String getScoreboardDisplayName() {
        return "HOLE IN THE WALL";
    }

    @Override
    public String getInternalScoreboardName() {
        return "HoITW";
    }

    @Override
    public Pattern getEndGameRegexPattern() {
        return this.END_GAME_REGEX;
    }

    @Override
    public ArcadeGame getCopy() {
        return new HoleInTheWall(this);
    }

    @Override
    public void reset() {

    }
}
