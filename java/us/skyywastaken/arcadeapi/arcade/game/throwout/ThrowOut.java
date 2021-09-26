package us.skyywastaken.arcadeapi.arcade.game.throwout;

import us.skyywastaken.arcadeapi.arcade.game.ArcadeGame;
import us.skyywastaken.arcadeapi.arcade.game.partygames.PartyGames;

import java.util.List;
import java.util.regex.Pattern;

public class ThrowOut implements ArcadeGame{
    private final Pattern END_GAME_REGEX = Pattern.compile("^ +1st Place - .+");
    public ThrowOut() {

    }

    public ThrowOut(ThrowOut passedThrowOut) {

    }

    @Override
    public List<Object> getListeners() {
        return null;
    }

    @Override
    public String getScoreboardDisplayName() {
        return "THROW OUT";
    }

    @Override
    public String getInternalScoreboardName() {
        return "THROWOUT";
    }

    @Override
    public Pattern getEndGameRegexPattern() {
        return this.END_GAME_REGEX;
    }

    @Override
    public ArcadeGame getCopy() {
        return new ThrowOut(this);
    }

    @Override
    public void reset() {

    }
}
