package us.skyywastaken.arcadeapi.arcade.game.enderspleef;

import us.skyywastaken.arcadeapi.arcade.game.ArcadeGame;
import us.skyywastaken.arcadeapi.arcade.game.creeperattack.CreeperAttack;

import java.util.List;
import java.util.regex.Pattern;

public class EnderSpleef implements ArcadeGame {
    private final Pattern END_GAME_PATTERN = Pattern.compile("^ +1st Place - .+");

    public EnderSpleef() {

    }

    public EnderSpleef (EnderSpleef passedEnderSpleef) {

    }

    @Override
    public List<Object> getListeners() {
        return null;
    }

    @Override
    public String getScoreboardDisplayName() {
        return "ENDER SPLEEF";
    }

    @Override
    public String getInternalScoreboardName() {
        return "SPLEEF";
    }

    @Override
    public Pattern getEndGameRegexPattern() {
        return this.END_GAME_PATTERN;
    }

    @Override
    public ArcadeGame getCopy() {
        return new EnderSpleef(this);
    }

    @Override
    public void reset() {

    }
}
