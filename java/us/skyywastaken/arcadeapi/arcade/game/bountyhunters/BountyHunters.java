package us.skyywastaken.arcadeapi.arcade.game.bountyhunters;

import us.skyywastaken.arcadeapi.arcade.game.ArcadeGame;

import java.util.List;
import java.util.regex.Pattern;

public class BountyHunters implements ArcadeGame {
    private final Pattern END_GAME_PATTERN = Pattern.compile("^ +1st Place -.+");
    public BountyHunters() {
    }

    public BountyHunters(BountyHunters passedBountyHunters) {
    }

    @Override
    public List<Object> getListeners() {
        return null;
    }

    @Override
    public String getScoreboardDisplayName() {
        return "BOUNTY HUNTERS";
    }

    @Override
    public String getInternalScoreboardName() {
        return "BOUNTY";
    }

    @Override
    public Pattern getEndGameRegexPattern() {
        return this.END_GAME_PATTERN;
    }

    @Override
    public ArcadeGame getCopy() {
        return new BountyHunters(this);
    }

    @Override
    public void reset() {

    }
}
