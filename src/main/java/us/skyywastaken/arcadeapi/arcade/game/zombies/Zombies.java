package us.skyywastaken.arcadeapi.arcade.game.zombies;

import us.skyywastaken.arcadeapi.arcade.game.ArcadeGame;

import java.util.List;
import java.util.regex.Pattern;

public class Zombies implements ArcadeGame {
    private final Pattern END_GAME_REGEX = Pattern.compile("^ +Zombies - \\d+:\\d+ \\(Round \\d+\\)");
    public Zombies() {

    }

    public Zombies(Zombies partyGames) {

    }

    @Override
    public List<Object> getListeners() {
        return null;
    }

    @Override
    public String getScoreboardDisplayName() {
        return "ZOMBIES";
    }

    @Override
    public String getInternalScoreboardName() {
        return "ZScoreboard";
    }

    @Override
    public Pattern getEndGameRegexPattern() {
        return this.END_GAME_REGEX;
    }

    @Override
    public ArcadeGame getCopy() {
        return new Zombies(this);
    }

    @Override
    public void reset() {

    }
}
