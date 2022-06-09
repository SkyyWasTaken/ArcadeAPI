package us.skyywastaken.hypixelapi.asm;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import java.util.Map;

@IFMLLoadingPlugin.MCVersion("1.8.9")
public class HypixelAPICorePlugin implements IFMLLoadingPlugin {
    @Override
    public String[] getASMTransformerClass() {
        return new String[]{"us.skyywastaken.arcadeapi.asm.TeamPacketEventTransformer",
                "us.skyywastaken.arcadeapi.asm.ScoreboardObjectivePacketEventTransformer",
                "us.skyywastaken.arcadeapi.asm.BossBarUpdateEventTransformer"};
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
