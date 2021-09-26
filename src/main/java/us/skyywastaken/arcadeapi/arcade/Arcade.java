package us.skyywastaken.arcadeapi.arcade;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import us.skyywastaken.arcadeapi.ArcadeAPI;
import us.skyywastaken.arcadeapi.arcade.game.ArcadeGame;
import us.skyywastaken.arcadeapi.arcade.game.blockingdead.BlockingDead;
import us.skyywastaken.arcadeapi.arcade.game.bountyhunters.BountyHunters;
import us.skyywastaken.arcadeapi.arcade.game.capturethewool.CaptureTheWool;
import us.skyywastaken.arcadeapi.arcade.game.creeperattack.CreeperAttack;
import us.skyywastaken.arcadeapi.arcade.game.enderspleef.EnderSpleef;
import us.skyywastaken.arcadeapi.arcade.game.farmhunt.FarmHunt;
import us.skyywastaken.arcadeapi.arcade.game.football.Football;
import us.skyywastaken.arcadeapi.arcade.game.galaxywars.GalaxyWars;
import us.skyywastaken.arcadeapi.arcade.game.hideandseek.HideAndSeek;
import us.skyywastaken.arcadeapi.arcade.game.holeinthewall.HoleInTheWall;
import us.skyywastaken.arcadeapi.arcade.game.hypixelsays.HypixelSays;
import us.skyywastaken.arcadeapi.arcade.game.miniwalls.MiniWalls;
import us.skyywastaken.arcadeapi.arcade.game.partygames.PartyGames;
import us.skyywastaken.arcadeapi.arcade.game.pixelpainters.PixelPainters;
import us.skyywastaken.arcadeapi.arcade.game.throwout.ThrowOut;
import us.skyywastaken.arcadeapi.arcade.game.zombies.Zombies;

import java.util.ArrayList;
import java.util.ServiceLoader;
import java.util.logging.LogManager;

public class Arcade {
    private final static Arcade ARCADE_INSTANCE = new Arcade();
    private final ArcadeHandler ARCADE_HANDLER = new ArcadeHandler(this);
    private final ArcadeScoreboardListener ARCADE_SCOREBOARD_LISTENER = new ArcadeScoreboardListener(this.ARCADE_HANDLER);
    private GamePhase currentGamePhase;
    private ArcadeGame currentGame;

    public Arcade() {
        registerGames();
        registerArcadeListeners();
    }

    private void registerArcadeListeners() {
        MinecraftForge.EVENT_BUS.register(this.ARCADE_SCOREBOARD_LISTENER);
        MinecraftForge.EVENT_BUS.register(new ArcadeChatHandler(this.ARCADE_HANDLER));
    }

    public static Arcade getArcade() {
        return ARCADE_INSTANCE;
    }

    public GamePhase getCurrentGamePhase() {
        return currentGamePhase;
    }

    void setCurrentGame(ArcadeGame newGame) {
        this.currentGame = newGame;
    }

    void setGamePhase(GamePhase newGamePhase) {
        this.currentGamePhase = newGamePhase;
    }

    public ArcadeGame getCurrentGame() {
        return this.currentGame;
    }

    private void registerListeners(ArcadeGame arcadeGame) {
        if(arcadeGame.getListeners() == null) {
            return;
        }
        for(Object currentObject : arcadeGame.getListeners()) {
            MinecraftForge.EVENT_BUS.register(currentObject);
        }
    }

    private void registerGames() {
        registerGame(new BlockingDead());
        registerGame(new BountyHunters());
        registerGame(new CaptureTheWool());
        registerGame(new CreeperAttack());
        registerGame(new EnderSpleef());
        registerGame(new GalaxyWars());
        registerGame(new FarmHunt());
        registerGame(new Football());
        registerGame(new HideAndSeek());
        registerGame(new HoleInTheWall());
        registerGame(new HypixelSays());
        registerGame(new MiniWalls());
        registerGame(new PartyGames());
        registerGame(new PixelPainters());
        registerGame(new ThrowOut());
        registerGame(new Zombies());
    }

    private void registerGame(ArcadeGame arcadeGame) {
        this.ARCADE_SCOREBOARD_LISTENER.registerGame(arcadeGame);
        registerListeners(arcadeGame);
    }

    public void sendStartupMessage() {
        ArcadeAPI.LOGGER.info("Getting ready to take over the arcade!");
    }

}
