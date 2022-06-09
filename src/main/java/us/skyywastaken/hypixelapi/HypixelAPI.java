package us.skyywastaken.hypixelapi;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import us.skyywastaken.hypixelapi.arcade.Arcade;

@Mod(modid = HypixelAPI.MODID, name = HypixelAPI.NAME, version = HypixelAPI.VERSION)
public class HypixelAPI {
    public static final String MODID = "hypixelapi";
    public static final String VERSION = "0.0.1";
    public static final String NAME = "Hypixel API";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    @Mod.EventHandler
    public void onEnable(FMLInitializationEvent event) {
        if (event.getSide() == Side.SERVER) {
            return;
        }
        Arcade.getArcade().sendStartupMessage(); // instantiates the arcade instance, loading the rest of the mod. jank.
    }
}
