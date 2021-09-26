package us.skyywastaken.arcadeapi.arcade.game.creeperattack;

import us.skyywastaken.arcadeapi.arcade.game.ArcadeGame;

import java.util.List;
import java.util.regex.Pattern;

public class CreeperAttack implements ArcadeGame {
    private final Pattern END_GAME_PATTERN = Pattern.compile("^ +You survived \\d+ rounds!");

    public CreeperAttack() {

    }

    public CreeperAttack (CreeperAttack creeperAttack) {

    }

    @Override
    public List<Object> getListeners() {
        return null;
    }

    @Override
    public String getScoreboardDisplayName() {
        return "CREEPER ATTACK";
    }

    @Override
    public String getInternalScoreboardName() {
        return "Creeper Attack";
    }

    @Override
    public Pattern getEndGameRegexPattern() {
        return this.END_GAME_PATTERN;
    }

    @Override
    public ArcadeGame getCopy() {
        return new CreeperAttack(this);
    }

    @Override
    public void reset() {

    }
}
