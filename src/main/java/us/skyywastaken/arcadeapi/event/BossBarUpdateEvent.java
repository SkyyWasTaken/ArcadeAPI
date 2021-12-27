package us.skyywastaken.arcadeapi.event;

import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraftforge.fml.common.eventhandler.Event;

public class BossBarUpdateEvent extends Event {
    // Health / max health
    public float percentHealthRemaining;
    public int statusBarTime;
    public String bossName;
    public boolean hasColorModifier;


    public BossBarUpdateEvent(IBossDisplayData displayData, boolean passedColorModifierFlag) {
        this.percentHealthRemaining = displayData.getHealth() / displayData.getMaxHealth();
        this.statusBarTime = 100;
        this.bossName = displayData.getDisplayName().getFormattedText();
        this.hasColorModifier = passedColorModifierFlag;
    }
}
