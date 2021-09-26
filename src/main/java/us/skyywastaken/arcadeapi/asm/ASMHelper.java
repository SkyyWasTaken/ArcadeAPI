package us.skyywastaken.arcadeapi.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

import java.util.HashMap;
import java.util.Iterator;

public class ASMHelper {
    public static ClassNode getClassNode(byte[] passedClassBytes) {
        ClassReader classReader = new ClassReader(passedClassBytes);
        ClassNode classNode = new ClassNode();
        classReader.accept(classNode, 0);
        return classNode;
    }

    public static byte[] getByteArrayFromClassNode(ClassNode passedClassNode) {
        ClassWriter classWriter = new ClassWriter(0);
        passedClassNode.accept(classWriter);
        return classWriter.toByteArray();
    }

    public static void prependInsnListToMethodNodeInsns(MethodNode methodNode, InsnList insnListToPrepend) {
        AbstractInsnNode keyInsnNode = null;
        Iterator<AbstractInsnNode> iterator = methodNode.instructions.iterator();
        while(iterator.hasNext()) { // The new instructions have to be after the game makes sure it's on the main thread
            AbstractInsnNode currentInsn = iterator.next(); // or it fires the event twice.
            if(currentInsn.getOpcode() == Opcodes.INVOKESTATIC) {
                keyInsnNode = currentInsn;
                break;
            }
        }
        methodNode.instructions.insert(keyInsnNode, insnListToPrepend);
    }

    public static MethodNode getMethodNodeFromClassNode(ClassNode classNode, HashMap<String, String> mappings) {
        for (MethodNode currentMethodNode : classNode.methods) {
            if (currentMethodNode.name.equals(mappings.get("methodName"))
                    && currentMethodNode.desc.equals(mappings.get("methodDesc"))) {
                return currentMethodNode;
            }
        }
        return null;
    }

    public static InsnList generateNewEventInstructions(HashMap<String, String> mappings) {
        InsnList newInstructionList = new InsnList();

        LabelNode labelZero = new LabelNode();

        //label 0
        FieldInsnNode getEventBus = new FieldInsnNode(Opcodes.GETSTATIC,
                "net/minecraftforge/common/MinecraftForge", "EVENT_BUS",
                "Lnet/minecraftforge/fml/common/eventhandler/EventBus;");
        TypeInsnNode createNewEvent = new TypeInsnNode(Opcodes.NEW,
                mappings.get("eventClass"));
        InsnNode duplicateInsnNode = new InsnNode(Opcodes.DUP);
        VarInsnNode packetVariable = new VarInsnNode(Opcodes.ALOAD, 1);
        MethodInsnNode loadEventConstructor = new MethodInsnNode(Opcodes.INVOKESPECIAL,
                mappings.get("eventClass"), "<init>",
                mappings.get("eventDesc"), false);
        MethodInsnNode postEventInsn = new MethodInsnNode(Opcodes.INVOKEVIRTUAL,
                "net/minecraftforge/fml/common/eventhandler/EventBus", "post",
                "(Lnet/minecraftforge/fml/common/eventhandler/Event;)Z", false);
        InsnNode popInstruction = new InsnNode(Opcodes.POP);

        newInstructionList.add(labelZero);
        newInstructionList.add(getEventBus);
        newInstructionList.add(createNewEvent);
        newInstructionList.add(duplicateInsnNode);
        newInstructionList.add(packetVariable);
        newInstructionList.add(loadEventConstructor);
        newInstructionList.add(postEventInsn);
        newInstructionList.add(popInstruction);
        return newInstructionList;
    }
}
