package us.skyywastaken.arcadeapi;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import us.skyywastaken.arcadeapi.arcade.Arcade;

@Mod(modid = ArcadeAPI.MODID, name = ArcadeAPI.NAME, version = ArcadeAPI.VERSION)
public class ArcadeAPI {
    public static final String MODID = "arcadeapi";
    public static final String VERSION = "0.0.1";
    public static final String NAME = "Arcade API";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    @Mod.EventHandler
    public void onEnable(FMLInitializationEvent event) {
        if(event.getSide() == Side.SERVER) {
            return;
        }
        Arcade.getArcade().sendStartupMessage(); // instantiates the arcade instance, loading the rest of the mod. jank.
    }
}
