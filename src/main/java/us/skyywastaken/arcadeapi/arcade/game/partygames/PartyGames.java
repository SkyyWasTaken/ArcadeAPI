package us.skyywastaken.arcadeapi.arcade.game.partygames;

import us.skyywastaken.arcadeapi.arcade.game.ArcadeGame;
import us.skyywastaken.arcadeapi.arcade.game.partygames.game.PartyGame;

import java.util.List;
import java.util.regex.Pattern;

public class PartyGames implements ArcadeGame {
    private final Pattern END_GAME_REGEX = Pattern.compile("^ +1st Place -.+\\u272E$");
    private final boolean IS_PG_COPY;
    private final PartyGameManager PARTY_GAME_MANAGER;

    public PartyGames() {
        this.IS_PG_COPY = false;
        this.PARTY_GAME_MANAGER = new PartyGameManager();
    }

    public PartyGames(PartyGames passedPartyGames) {
        this.IS_PG_COPY = true;
        this.PARTY_GAME_MANAGER = passedPartyGames.PARTY_GAME_MANAGER.getCopy();
    }

    public PartyGame getCurrentPartyGame() {
        return this.PARTY_GAME_MANAGER.getCurrentPartyGame();
    }

    private void registerGames() {
    }

    @Override
    public List<Object> getListeners() {
        return null;
    }

    @Override
    public String getScoreboardDisplayName() {
        return "PARTY GAMES";
    }

    @Override
    public String getInternalScoreboardName() {
        return "PartyGames";
    }

    @Override
    public Pattern getEndGameRegexPattern() {
        return this.END_GAME_REGEX;
    }

    @Override
    public ArcadeGame getCopy() {
        return new PartyGames(this);
    }

    @Override
    public void reset() {
        this.PARTY_GAME_MANAGER.reset();
    }
}
