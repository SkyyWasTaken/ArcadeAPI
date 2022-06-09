package us.skyywastaken.hypixelapi.arcade;

import net.minecraftforge.common.MinecraftForge;
import us.skyywastaken.hypixelapi.arcade.event.ArcadeGameEndEvent;
import us.skyywastaken.hypixelapi.arcade.event.ArcadeGameLeaveEvent;
import us.skyywastaken.hypixelapi.arcade.event.ArcadeGameLobbyJoinEvent;
import us.skyywastaken.hypixelapi.arcade.event.ArcadeGameStartEvent;
import us.skyywastaken.hypixelapi.arcade.game.ArcadeGame;

public class ArcadeHandler {
    private final Arcade ARCADE;
    public ArcadeHandler(Arcade passedArcade) {
        this.ARCADE = passedArcade;
    }

    public void onGameEnd() {
        ArcadeGameEndEvent endEvent = new ArcadeGameEndEvent(this.ARCADE.getCurrentGame().getCopy());
        MinecraftForge.EVENT_BUS.post(endEvent);
        this.ARCADE.setGamePhase(GamePhase.POSTGAME);
    }

    void onGameLobbyJoin(ArcadeGame passedGame) {
        ArcadeGameLobbyJoinEvent joinEvent = new ArcadeGameLobbyJoinEvent(passedGame.getCopy());
        MinecraftForge.EVENT_BUS.post(joinEvent);
        this.ARCADE.setGamePhase(GamePhase.PREGAME);
        this.ARCADE.handleGameChange(passedGame);
    }

    void onGameStart(ArcadeGame passedGame) {
        ArcadeGameStartEvent startEvent = new ArcadeGameStartEvent(passedGame.getCopy());
        MinecraftForge.EVENT_BUS.post(startEvent);
        this.ARCADE.setGamePhase(GamePhase.INGAME);
        this.ARCADE.handleGameChange(passedGame);
    }

    void onGameLeave(ArcadeGame leftGame) {
        ArcadeGameLeaveEvent leaveEvent = new ArcadeGameLeaveEvent(leftGame.getCopy());
        MinecraftForge.EVENT_BUS.post(leaveEvent);
        this.ARCADE.setGamePhase(GamePhase.NONE);
        this.ARCADE.handleGameChange(null);
    }
}
