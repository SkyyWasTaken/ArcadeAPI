package us.skyywastaken.hypixelapi.event;

import net.minecraft.network.play.server.S3EPacketTeams;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.Collection;

public class TeamUpdateEvent extends Event {
    public final TeamUpdateType TEAM_UPDATE_TYPE;

    // todo: javadoc

    // Team update/creation variables. Will be null if the team is not being updated or created.
    public final String TEAM_NAME;
    public final String TEAM_PREFIX;
    public final String TEAM_SUFFIX;
    public final boolean FRIENDLY_FIRE_ENABLED;
    public final boolean FRIENDLY_INVISIBLES_VISIBLE;
    public final Team.EnumVisible FRIENDLY_NAMES_VISIBLE;
    public final EnumChatFormatting CHAT_FORMAT;

    // Entity addition/removal variables. Will be null if players are not being added/removed.
    public final int ENTITY_COUNT;
    public final Collection<String> ENTITY_LIST;

    public TeamUpdateEvent(S3EPacketTeams teamUpdatePacket) {
        int teamUpdateTypeID = teamUpdatePacket.func_149307_h();
        this.TEAM_UPDATE_TYPE = getTeamUpdateTypeFromInt(teamUpdateTypeID);

        this.TEAM_NAME = teamUpdatePacket.func_149312_c(); //On hypixel, this should be "team_(row number)"
        this.TEAM_PREFIX = teamUpdatePacket.func_149311_e();
        this.TEAM_SUFFIX = teamUpdatePacket.func_149309_f();
        this.FRIENDLY_FIRE_ENABLED = (teamUpdatePacket.func_149308_i() & 1) > 0;
        this.FRIENDLY_INVISIBLES_VISIBLE = (teamUpdatePacket.func_149308_i() & 2) > 0;
        this.FRIENDLY_NAMES_VISIBLE = Team.EnumVisible.func_178824_a(teamUpdatePacket.func_179814_i());
        this.CHAT_FORMAT = EnumChatFormatting.func_175744_a(teamUpdatePacket.func_179813_h());

        this.ENTITY_COUNT = teamUpdatePacket.func_149307_h();
        this.ENTITY_LIST = teamUpdatePacket.func_149310_g();
    }

    public enum TeamUpdateType {
        UPDATE_TEAM, CREATE_TEAM, REMOVE_TEAM, ADD_ENTITY, REMOVE_ENTITY
    }

    private TeamUpdateType getTeamUpdateTypeFromInt(int passedTeamUpdateID) {
        switch(passedTeamUpdateID) {
            case 0:
                return TeamUpdateType.CREATE_TEAM;
            case 1:
                return TeamUpdateType.REMOVE_TEAM;
            case 2:
                return TeamUpdateType.UPDATE_TEAM;
            case 3:
                return TeamUpdateType.ADD_ENTITY;
            case 4:
                return TeamUpdateType.REMOVE_ENTITY;
            default:
                throw new IllegalArgumentException("Unknown team update type: " + passedTeamUpdateID);
        }
    }
}
