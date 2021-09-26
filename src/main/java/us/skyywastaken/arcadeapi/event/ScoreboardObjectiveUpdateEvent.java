package us.skyywastaken.arcadeapi.event;

import net.minecraft.network.play.server.S3BPacketScoreboardObjective;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraftforge.fml.common.eventhandler.Event;

public class ScoreboardObjectiveUpdateEvent extends Event {
    public final ObjectiveEventType OBJECTIVE_EVENT_TYPE;

    //TODO: javadoc
    public final String OBJECTIVE_NAME; // Internal objective name used for referencing within commands or code.

    // Only used when creating or updating an objective
    public final String OBJECTIVE_VALUE; // The objective's display name. Shown at the top of the scoreboard.
    public final IScoreObjectiveCriteria.EnumRenderType RENDER_TYPE; // hearts or integer.
    public ScoreboardObjectiveUpdateEvent(S3BPacketScoreboardObjective passedPacket) {
        int packetModeID = passedPacket.func_149338_e();
        this.OBJECTIVE_EVENT_TYPE = ObjectiveEventType.fromModeID(packetModeID);

        this.OBJECTIVE_NAME = passedPacket.func_149339_c();
        this.OBJECTIVE_VALUE = passedPacket.func_149337_d();
        this.RENDER_TYPE = passedPacket.func_179817_d();
    }

    public enum ObjectiveEventType {
        CREATE_OBJECTIVE, REMOVE_OBJECTIVE, UPDATE_DISPLAY_TEXT;
        public static ObjectiveEventType fromModeID(int passedModeID) {
            switch(passedModeID) {
                case 0:
                    return CREATE_OBJECTIVE;
                case 1:
                    return REMOVE_OBJECTIVE;
                case 2:
                    return UPDATE_DISPLAY_TEXT;
                default:
                    throw new IllegalArgumentException("Unknown scoreboard objective type: " + passedModeID);
            }
        }
    }
}
