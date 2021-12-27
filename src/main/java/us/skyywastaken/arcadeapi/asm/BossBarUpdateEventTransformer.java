package us.skyywastaken.arcadeapi.asm;

import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LineNumberNode;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

import java.util.HashMap;

@IFMLLoadingPlugin.TransformerExclusions("us.skyywastaken.arcadeapi.asm")
@IFMLLoadingPlugin.MCVersion("1.8.9")
public class BossBarUpdateEventTransformer implements IClassTransformer {
    private final HashMap<String, String> obfuscatedMappings = getObfuscatedMappingsMap();
    private final HashMap<String, String> deObfuscatedMappings = getDeObfuscatedMappingsMap();

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (obfuscatedMappings.get("bossStatusClassName").equals(name)) {
            return transformClass(basicClass, obfuscatedMappings);
        } else if (deObfuscatedMappings.get("bossStatusClassName").equals(name)) {
            return transformClass(basicClass, deObfuscatedMappings);
        } else {
            return basicClass;
        }
    }

    private byte[] transformClass(byte[] passedClass, HashMap<String, String> mappings) {
        ClassNode bossClass = ASMHelper.getClassNode(passedClass);
        for (MethodNode methodNode : bossClass.methods) {
            if (methodNode.name.equals(mappings.get("bossStatusMethodName"))
                    && methodNode.desc.equals(mappings.get("bossStatusMethodDesc"))) {
                methodNode.maxStack = 4;
                methodNode.maxLocals = 3;
                methodNode.instructions = getNewBossClassInstructions(methodNode, mappings);
            }
        }
        return ASMHelper.getByteArrayFromClassNode(bossClass);
    }


    private HashMap<String, String> getObfuscatedMappingsMap() {
        HashMap<String, String> obfuscatedMap = new HashMap<String, String>();
        obfuscatedMap.put("bossStatusClassName", "bfc");
        obfuscatedMap.put("asmBossStatusClassName", "bfc");
        obfuscatedMap.put("bossStatusMethodName", "a");
        obfuscatedMap.put("bossStatusMethodDesc", "(Luc;Z)V");

        obfuscatedMap.put("bossDisplayDataDesc", "Luc;");

        obfuscatedMap.put("healthScaleFieldName", "a");
        obfuscatedMap.put("statusBarTimeFieldName", "b");
        obfuscatedMap.put("bossNameFieldName", "c");
        obfuscatedMap.put("colorModifierFlagFieldName", "d");
        return obfuscatedMap;
    }

    private HashMap<String, String> getDeObfuscatedMappingsMap() {
        HashMap<String, String> deObfuscatedMap = new HashMap<String, String>();
        deObfuscatedMap.put("bossStatusClassName", "net.minecraft.entity.boss.BossStatus");
        deObfuscatedMap.put("asmBossStatusClassName", "net/minecraft/entity/boss/BossStatus");
        deObfuscatedMap.put("bossStatusMethodName", "setBossStatus");
        deObfuscatedMap.put("bossStatusMethodDesc", "(Lnet/minecraft/entity/boss/IBossDisplayData;Z)V");

        deObfuscatedMap.put("bossDisplayDataDesc", "Lnet/minecraft/entity/boss/IBossDisplayData;");

        deObfuscatedMap.put("healthScaleFieldName", "healthScale");
        deObfuscatedMap.put("statusBarTimeFieldName", "statusBarTime");
        deObfuscatedMap.put("bossNameFieldName", "bossName");
        deObfuscatedMap.put("colorModifierFlagFieldName", "hasColorModifier");
        return deObfuscatedMap;
    }

    private InsnList getNewBossClassInstructions(MethodNode passedMethodNode, HashMap<String, String> passedMappings) {
        LabelNode labelZero = new LabelNode();
        LabelNode labelOne = new LabelNode();
        LabelNode labelTwo = new LabelNode();
        LabelNode labelThree = new LabelNode();
        LabelNode labelFour = new LabelNode();
        LabelNode labelFive = new LabelNode();
        LabelNode labelSix = new LabelNode();
        LabelNode labelSeven = new LabelNode();

        //Label 0
        LineNumberNode lineNumberNodeZero = new LineNumberNode(14, labelZero);
        TypeInsnNode newUpdateEvent = new TypeInsnNode(Opcodes.NEW,
                "us/skyywastaken/arcadeapi/event/BossBarUpdateEvent");
        InsnNode duplicateInsnNode = new InsnNode(Opcodes.DUP);
        VarInsnNode loadBossDisplayData = new VarInsnNode(Opcodes.ALOAD, 0);
        VarInsnNode loadColorFlag = new VarInsnNode(Opcodes.ILOAD, 1);
        MethodInsnNode createNewEvent = new MethodInsnNode(Opcodes.INVOKESPECIAL,
                "us/skyywastaken/arcadeapi/event/BossBarUpdateEvent", "<init>", passedMappings.get("bossStatusMethodDesc"),
                false);
        VarInsnNode storeEvent = new VarInsnNode(Opcodes.ASTORE, 2);

        //Label 1
        LineNumberNode lineNumberNodeOne = new LineNumberNode(15, labelOne);
        FieldInsnNode getEventBus = new FieldInsnNode(Opcodes.GETSTATIC,
                "net/minecraftforge/common/MinecraftForge", "EVENT_BUS",
                "Lnet/minecraftforge/fml/common/eventhandler/EventBus;");
        VarInsnNode loadBarUpdateEvent = new VarInsnNode(Opcodes.ALOAD, 2);
        MethodInsnNode postEvent = new MethodInsnNode(Opcodes.INVOKEVIRTUAL,
                "net/minecraftforge/fml/common/eventhandler/EventBus", "post",
                "(Lnet/minecraftforge/fml/common/eventhandler/Event;)Z", false);
        InsnNode popInsn = new InsnNode(Opcodes.POP);

        //Label 2
        LineNumberNode lineNumberNodeTwo = new LineNumberNode(17, labelTwo);
        VarInsnNode loadBossEventOne = new VarInsnNode(Opcodes.ALOAD, 2);
        FieldInsnNode accessHealthField = new FieldInsnNode(Opcodes.GETFIELD,
                "us/skyywastaken/arcadeapi/event/BossBarUpdateEvent", "percentHealthRemaining", "F");
        FieldInsnNode setHealthRemaining = new FieldInsnNode(Opcodes.PUTSTATIC,
                passedMappings.get("asmBossStatusClassName"), passedMappings.get("healthScaleFieldName"), "F");

        //Label 3
        LineNumberNode lineNumberNodeThree = new LineNumberNode(18, labelThree);
        VarInsnNode loadBossEventTwo = new VarInsnNode(Opcodes.ALOAD, 2);
        FieldInsnNode accessStatusBarField = new FieldInsnNode(Opcodes.GETFIELD,
                "us/skyywastaken/arcadeapi/event/BossBarUpdateEvent", "statusBarTime", "I");
        FieldInsnNode setStatusBarVar = new FieldInsnNode(Opcodes.PUTSTATIC,
                passedMappings.get("asmBossStatusClassName"), passedMappings.get("statusBarTimeFieldName"), "I");

        //Label 4
        LineNumberNode lineNumberNodeFour = new LineNumberNode(19, labelFour);
        VarInsnNode loadBossEventThree = new VarInsnNode(Opcodes.ALOAD, 2);
        FieldInsnNode accessBossNameField = new FieldInsnNode(Opcodes.GETFIELD,
                "us/skyywastaken/arcadeapi/event/BossBarUpdateEvent", "bossName",
                "Ljava/lang/String;");
        FieldInsnNode setBossNameVar = new FieldInsnNode(Opcodes.PUTSTATIC,
                passedMappings.get("asmBossStatusClassName"), passedMappings.get("bossNameFieldName"),
                "Ljava/lang/String;");

        //Label 5
        LineNumberNode lineNumberNodeFive = new LineNumberNode(20, labelFive);
        VarInsnNode loadBossEventFour = new VarInsnNode(Opcodes.ALOAD, 2);
        FieldInsnNode accessColorModifier = new FieldInsnNode(Opcodes.GETFIELD,
                "us/skyywastaken/arcadeapi/event/BossBarUpdateEvent", "hasColorModifier", "Z");
        FieldInsnNode setColorModifierVar = new FieldInsnNode(Opcodes.PUTSTATIC,
                passedMappings.get("asmBossStatusClassName"), passedMappings.get("colorModifierFlagFieldName"),
                "Z");

        //Label 6
        LineNumberNode lineNumberNodeSix = new LineNumberNode(21, labelSix);
        InsnNode returnInsn = new InsnNode(Opcodes.RETURN);

        //Label 7
        passedMethodNode.localVariables.clear();
        passedMethodNode.localVariables.add(new LocalVariableNode("displayData",
                passedMappings.get("bossDisplayDataDesc"), null, labelZero, labelSeven, 0));
        passedMethodNode.localVariables.add(new LocalVariableNode("hasColorModifierIn", "Z", null,
                labelZero, labelSeven, 1));
        passedMethodNode.localVariables.add(new LocalVariableNode("updateEvent",
                "Lus/skyywastaken/arcadeapi/event/bossbarupdateevent;", null, labelOne, labelSeven,
                2));

        InsnList returnInsns = new InsnList();
        returnInsns.add(labelZero);
        returnInsns.add(lineNumberNodeZero);
        returnInsns.add(newUpdateEvent);
        returnInsns.add(duplicateInsnNode);
        returnInsns.add(loadBossDisplayData);
        returnInsns.add(loadColorFlag);
        returnInsns.add(createNewEvent);
        returnInsns.add(storeEvent);

        returnInsns.add(labelOne);
        returnInsns.add(lineNumberNodeOne);
        returnInsns.add(getEventBus);
        returnInsns.add(loadBarUpdateEvent);
        returnInsns.add(postEvent);
        returnInsns.add(popInsn);

        returnInsns.add(labelTwo);
        returnInsns.add(lineNumberNodeTwo);
        returnInsns.add(loadBossEventOne);
        returnInsns.add(accessHealthField);
        returnInsns.add(setHealthRemaining);

        returnInsns.add(labelThree);
        returnInsns.add(lineNumberNodeThree);
        returnInsns.add(loadBossEventTwo);
        returnInsns.add(accessStatusBarField);
        returnInsns.add(setStatusBarVar);

        returnInsns.add(labelFour);
        returnInsns.add(lineNumberNodeFour);
        returnInsns.add(loadBossEventThree);
        returnInsns.add(accessBossNameField);
        returnInsns.add(setBossNameVar);

        returnInsns.add(labelFive);
        returnInsns.add(lineNumberNodeFive);
        returnInsns.add(loadBossEventFour);
        returnInsns.add(accessColorModifier);
        returnInsns.add(setColorModifierVar);

        returnInsns.add(labelSix);
        returnInsns.add(lineNumberNodeSix);
        returnInsns.add(returnInsn);

        returnInsns.add(labelSeven);
        return returnInsns;
    }
}
