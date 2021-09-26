package us.skyywastaken.arcadeapi.arcade.game.farmhunt;

import us.skyywastaken.arcadeapi.arcade.game.ArcadeGame;
import us.skyywastaken.arcadeapi.arcade.game.football.Football;

import java.util.List;
import java.util.regex.Pattern;

public class FarmHunt implements ArcadeGame {
    private final Pattern END_GAME_REGEX = Pattern.compile("^ +Winning Team: .+");
    public FarmHunt() {

    }

    public FarmHunt(FarmHunt passedFarmHunt) {

    }

    @Override
    public List<Object> getListeners() {
        return null;
    }

    @Override
    public String getScoreboardDisplayName() {
        return "FARM HUNT";
    }

    @Override
    public String getInternalScoreboardName() {
        return "Farm Hunt";
    }

    @Override
    public Pattern getEndGameRegexPattern() {
        return this.END_GAME_REGEX;
    }

    @Override
    public ArcadeGame getCopy() {
        return new FarmHunt(this);
    }

    @Override
    public void reset() {

    }
}
