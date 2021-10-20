package us.skyywastaken.arcadeapi.arcade.game.partygames;

import us.skyywastaken.arcadeapi.arcade.game.ArcadeGame;
import us.skyywastaken.arcadeapi.arcade.game.partygames.game.PartyGameListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class PartyGames implements ArcadeGame {
    private final Pattern END_GAME_REGEX = Pattern.compile("^ +1st Place -.+\\u272E$");
    private final PartyGameListener PARTY_GAME_LISTENER = new PartyGameListener();
    public PartyGames() {

    }

    public PartyGames(PartyGames partyGames) {

    }

    @Override
    public List<Object> getListeners() {
        return new ArrayList<Object>(Collections.singletonList(this.PARTY_GAME_LISTENER));
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

    }
}
