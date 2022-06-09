package us.skyywastaken.hypixelapi.arcade.game.blockingdead;

import us.skyywastaken.hypixelapi.arcade.game.ArcadeGame;

import java.util.List;
import java.util.regex.Pattern;

public class BlockingDead implements ArcadeGame {
    private final Pattern END_GAME_PATTERN = Pattern.compile("^ +1st Place -.+");
    public BlockingDead() {
    }

    public BlockingDead(BlockingDead passedBlockingDead) {
    }

    @Override
    public List<Object> getListeners() {
        return null;
    }

    @Override
    public String getScoreboardDisplayName() {
        return "BLOCKING DEAD";
    }

    @Override
    public String getInternalScoreboardName() {
        return "Blocking Dead";
    }

    @Override
    public Pattern getEndGameRegexPattern() {
        return this.END_GAME_PATTERN;
    }

    @Override
    public ArcadeGame getCopy() {
        return new BlockingDead(this);
    }

    @Override
    public void reset() {

    }
}
