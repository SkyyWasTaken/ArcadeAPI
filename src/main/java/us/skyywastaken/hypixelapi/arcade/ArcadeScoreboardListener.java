package us.skyywastaken.hypixelapi.arcade;

import net.minecraft.util.StringUtils;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import us.skyywastaken.hypixelapi.arcade.game.ArcadeGame;
import us.skyywastaken.hypixelapi.event.ScoreboardObjectiveUpdateEvent;

import java.util.HashMap;

public class ArcadeScoreboardListener {
    private final HashMap<String, ArcadeGame> DISPLAY_NAME_GAME_MAP;
    private final HashMap<String, ArcadeGame> INTERNAL_NAME_GAME_MAP;
    private final ArcadeHandler ARCADE_HANDLER;

    public ArcadeScoreboardListener(ArcadeHandler passedArcadeHandler) {
        this.DISPLAY_NAME_GAME_MAP = new HashMap<String, ArcadeGame>();
        this.INTERNAL_NAME_GAME_MAP = new HashMap<String, ArcadeGame>();
        this.ARCADE_HANDLER = passedArcadeHandler;
    }

    @SubscribeEvent
    public void onObjectiveCreated(ScoreboardObjectiveUpdateEvent updateEvent) {
        if(updateEvent.OBJECTIVE_EVENT_TYPE != ScoreboardObjectiveUpdateEvent.ObjectiveEventType.CREATE_OBJECTIVE) {
            return;
        }
        handleCreateObjectiveEvent(updateEvent);
    }

    @SubscribeEvent
    public void onObjectiveRemoved(ScoreboardObjectiveUpdateEvent updateEvent) {
        if(updateEvent.OBJECTIVE_EVENT_TYPE != ScoreboardObjectiveUpdateEvent.ObjectiveEventType.REMOVE_OBJECTIVE) {
            return;
        }
        if(this.INTERNAL_NAME_GAME_MAP.containsKey(updateEvent.OBJECTIVE_NAME)) {
            ArcadeGame leftGame = Arcade.getArcade().getCurrentGame();
            this.ARCADE_HANDLER.onGameLeave(leftGame);
        }
    }

    void handleCreateObjectiveEvent(ScoreboardObjectiveUpdateEvent updateEvent) {
        ArcadeGame newGame;
        GamePhase newGamePhase;
        boolean playerWasInPreGame = Arcade.getArcade().getCurrentGamePhase() == GamePhase.PREGAME;
        ArcadeGame lastGame = Arcade.getArcade().getCurrentGame();
        if(matchesPreGameScoreboardRequirements(updateEvent)) {
            if(playerWasInPreGame) {
                this.ARCADE_HANDLER.onGameLeave(lastGame);
            }
            newGame = DISPLAY_NAME_GAME_MAP.get(StringUtils.stripControlCodes(updateEvent.OBJECTIVE_VALUE));
            newGamePhase = GamePhase.PREGAME;
        } else if(this.INTERNAL_NAME_GAME_MAP.containsKey(StringUtils.stripControlCodes(updateEvent.OBJECTIVE_NAME))) {
            newGame = INTERNAL_NAME_GAME_MAP.get(StringUtils.stripControlCodes(updateEvent.OBJECTIVE_NAME));
            newGamePhase = GamePhase.INGAME;
        } else {
            if(playerWasInPreGame) {
                this.ARCADE_HANDLER.onGameLeave(lastGame);
            }
            return;
        }

        if(playerWasInPreGame && !newGame.getClass().equals(lastGame.getClass())) {
            this.ARCADE_HANDLER.onGameLeave(lastGame);
        }

        if(newGamePhase == GamePhase.PREGAME) {
            this.ARCADE_HANDLER.onGameLobbyJoin(newGame);
        } else {
            this.ARCADE_HANDLER.onGameStart(newGame);
        }
    }

    boolean matchesPreGameScoreboardRequirements(ScoreboardObjectiveUpdateEvent e) {
        return e.OBJECTIVE_NAME.equals("PreScoreboard")
                && this.DISPLAY_NAME_GAME_MAP.containsKey(StringUtils.stripControlCodes(e.OBJECTIVE_VALUE));
    }

    void registerGame(ArcadeGame gameToRegister) {
        this.DISPLAY_NAME_GAME_MAP.put(gameToRegister.getScoreboardDisplayName(), gameToRegister);
        this.INTERNAL_NAME_GAME_MAP.put(gameToRegister.getInternalScoreboardName(), gameToRegister);
    }
}
