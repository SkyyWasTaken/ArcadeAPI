package us.skyywastaken.arcadeapi.asm;

import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodNode;

import java.util.HashMap;

@IFMLLoadingPlugin.TransformerExclusions("us.skyywastaken.arcadeapi.asm")
@IFMLLoadingPlugin.MCVersion("1.8.9")
public class TeamPacketEventTransformer implements IClassTransformer {
    private HashMap<String, String> obfuscatedMappings = getObfuscatedMappingsMap();
    private HashMap<String, String> deObfuscatedMappings = getDeObfuscatedMappingsMap();

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if(obfuscatedMappings.get("className").equals(name)) {
            return transformClass(basicClass, obfuscatedMappings);
        } else if(deObfuscatedMappings.get("className").equals(name)) {
            return transformClass(basicClass, deObfuscatedMappings);
        } else {
            return basicClass;
        }
    }

    private byte[] transformClass(byte[] passedClass, HashMap<String, String> mappings) {
        ClassNode classNode = ASMHelper.getClassNode(passedClass);
        MethodNode methodNode = ASMHelper.getMethodNodeFromClassNode(classNode, mappings);
        HashMap<String, String> eventMappings = generateTeamPacketEventMappings();
        InsnList eventInstructions = ASMHelper.generateNewEventInstructions(eventMappings);
        ASMHelper.prependInsnListToMethodNodeInsns(methodNode, eventInstructions);
        return ASMHelper.getByteArrayFromClassNode(classNode);
    }

    private HashMap<String, String> generateTeamPacketEventMappings() {
        HashMap<String, String> returnMap = new HashMap<String, String>();
        returnMap.put("eventClass", "us/skyywastaken/arcadeapi/event/TeamUpdateEvent");
        returnMap.put("eventDesc", "(Lnet/minecraft/network/play/server/S3EPacketTeams;)V");
        return returnMap;
    }

    private HashMap<String, String> getObfuscatedMappingsMap() {
        HashMap<String, String> obfuscatedMap = new HashMap<String, String>();
        obfuscatedMap.put("className", "bcy");
        obfuscatedMap.put("methodName", "a");
        obfuscatedMap.put("methodDesc", "(Lhr;)V");
        return obfuscatedMap;
    }

    private HashMap<String, String> getDeObfuscatedMappingsMap() {
        HashMap<String, String> deObfuscatedMap = new HashMap<String, String>();
        deObfuscatedMap.put("className", "net.minecraft.client.network.NetHandlerPlayClient");
        deObfuscatedMap.put("methodName", "handleTeams");
        deObfuscatedMap.put("methodDesc", "(Lnet/minecraft/network/play/server/S3EPacketTeams;)V");
        return deObfuscatedMap;
    }
}
