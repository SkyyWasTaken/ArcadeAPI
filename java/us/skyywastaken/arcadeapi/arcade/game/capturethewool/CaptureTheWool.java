package us.skyywastaken.arcadeapi.arcade.game.capturethewool;

import us.skyywastaken.arcadeapi.arcade.game.ArcadeGame;

import java.util.List;
import java.util.regex.Pattern;

public class CaptureTheWool implements ArcadeGame{
    private final Pattern END_GAME_PATTERN = Pattern.compile("^ +((Red)|(Blue)) Team has won the game!");
    public CaptureTheWool() {
    }

    public CaptureTheWool(CaptureTheWool passedCaptureTheWool) {
    }

    @Override
    public List<Object> getListeners() {
        return null;
    }

    @Override
    public String getScoreboardDisplayName() {
        return "CAPTURE THE WOOL";
    }

    @Override
    public String getInternalScoreboardName() {
        return "Pvpboard";
    }

    @Override
    public Pattern getEndGameRegexPattern() {
        return this.END_GAME_PATTERN;
    }

    @Override
    public ArcadeGame getCopy() {
        return new CaptureTheWool(this);
    }

    @Override
    public void reset() {

    }
}
