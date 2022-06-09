package us.skyywastaken.hypixelapi.arcade.game.dragonwars;

import us.skyywastaken.hypixelapi.arcade.game.ArcadeGame;
import us.skyywastaken.hypixelapi.arcade.game.creeperattack.CreeperAttack;

import java.util.List;
import java.util.regex.Pattern;

public class DragonWars implements ArcadeGame {
    private final Pattern END_GAME_PATTERN = Pattern.compile("");

    public DragonWars() {

    }

    public DragonWars (CreeperAttack creeperAttack) {

    }

    @Override
    public List<Object> getListeners() {
        return null;
    }

    @Override
    public String getScoreboardDisplayName() {
        return "DRAGON WARS";
    }

    @Override
    public String getInternalScoreboardName() {
        return "DRAGONWARS";
    }

    @Override
    public Pattern getEndGameRegexPattern() {
        return this.END_GAME_PATTERN;
    }

    @Override
    public ArcadeGame getCopy() {
        return null;
    }

    @Override
    public void reset() {

    }
}
