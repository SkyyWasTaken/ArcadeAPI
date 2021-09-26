package us.skyywastaken.arcadeapi.arcade.game.pixelpainters;

import us.skyywastaken.arcadeapi.arcade.game.ArcadeGame;
import us.skyywastaken.arcadeapi.arcade.game.throwout.ThrowOut;

import java.util.List;
import java.util.regex.Pattern;

public class PixelPainters implements ArcadeGame {
    private final Pattern END_GAME_REGEX = Pattern.compile("^ +1st - .+");
    public PixelPainters() {

    }

    public PixelPainters(PixelPainters passedPixelPainters) {

    }

    @Override
    public List<Object> getListeners() {
        return null;
    }

    @Override
    public String getScoreboardDisplayName() {
        return "PIXEL PAINTERS";
    }

    @Override
    public String getInternalScoreboardName() {
        return "PP";
    }

    @Override
    public Pattern getEndGameRegexPattern() {
        return this.END_GAME_REGEX;
    }

    @Override
    public ArcadeGame getCopy() {
        return new PixelPainters(this);
    }

    @Override
    public void reset() {

    }
}
