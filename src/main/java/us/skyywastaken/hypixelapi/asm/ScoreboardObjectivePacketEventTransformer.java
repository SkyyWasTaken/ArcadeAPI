package us.skyywastaken.hypixelapi.asm;

import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodNode;

import java.util.HashMap;

@IFMLLoadingPlugin.TransformerExclusions("us.skyywastaken.nametagenabler.asm")
@IFMLLoadingPlugin.MCVersion("1.8.9")
public class ScoreboardObjectivePacketEventTransformer implements IClassTransformer {
    private final HashMap<String, String> obfuscatedMappings = getObfuscatedMappingsMap();
    private final HashMap<String, String> deObfuscatedMappings = getDeObfuscatedMappingsMap();

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (obfuscatedMappings.get("className").equals(name)) {
            return transformClass(basicClass, obfuscatedMappings);
        } else if (deObfuscatedMappings.get("className").equals(name)) {
            return transformClass(basicClass, deObfuscatedMappings);
        } else {
            return basicClass;
        }
    }

    private byte[] transformClass(byte[] passedClass, HashMap<String, String> mappings) {
        ClassNode classNode = ASMHelper.getClassNode(passedClass);
        MethodNode methodNode = ASMHelper.getMethodNodeFromClassNode(classNode, mappings);
        HashMap<String, String> eventMappings = generateObjectivePacketEventMappings();
        InsnList eventInstructions = ASMHelper.generateNewEventInstructions(eventMappings);
        ASMHelper.prependInsnListToMethodNodeInsnsAfterThreadCheck(methodNode, eventInstructions);
        return ASMHelper.getByteArrayFromClassNode(classNode);
    }


    private HashMap<String, String> generateObjectivePacketEventMappings() {
        HashMap<String, String> returnMap = new HashMap<String, String>();
        returnMap.put("eventClass", "us/skyywastaken/hypixelapi/event/ScoreboardObjectiveUpdateEvent");
        returnMap.put("eventDesc", "(Lnet/minecraft/network/play/server/S3BPacketScoreboardObjective;)V");
        return returnMap;
    }

    private HashMap<String, String> getObfuscatedMappingsMap() {
        HashMap<String, String> obfuscatedMap = new HashMap<String, String>();
        obfuscatedMap.put("className", "bcy");
        obfuscatedMap.put("methodName", "a");
        obfuscatedMap.put("methodDesc", "(Lhq;)V");
        return obfuscatedMap;
    }

    private HashMap<String, String> getDeObfuscatedMappingsMap() {
        HashMap<String, String> deObfuscatedMap = new HashMap<String, String>();
        deObfuscatedMap.put("className", "net.minecraft.client.network.NetHandlerPlayClient");
        deObfuscatedMap.put("methodName", "handleScoreboardObjective");
        deObfuscatedMap.put("methodDesc", "(Lnet/minecraft/network/play/server/S3BPacketScoreboardObjective;)V");
        return deObfuscatedMap;
    }
}
