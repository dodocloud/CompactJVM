package compactjvm.jvm.instructions;

import compactjvm.exceptions.LoadingException;
import compactjvm.jvm.StackFrame;
import compactjvm.jvm.JVMLogger;
import compactjvm.structures.*;

/**
 * if value1 is less than or equal to value2, branch to instruction at branchoffset 
 * (signed short constructed from unsigned bytes branchbyte1 << 8 + branchbyte2) 
 * branchbyte2) @author Adam Vesecky
 */
public class IfIcmpleInstruction {

    public static void run(StackFrame stackFrame) throws LoadingException {

        int nextInstruction = stackFrame.loadInstructionJumpAddr();

        SIntable value2 = stackFrame.operandStack.pop();
        SIntable value1 = stackFrame.operandStack.pop();

        SInt val1 = value1.toInt();
        SInt val2 = value2.toInt();

        if (val1.getValue() <= val2.getValue()) {
            stackFrame.setCurrentInstructionIndex(nextInstruction);
            JVMLogger.log(JVMLogger.TAG_INSTR_JUMP, "IfIcmple: " + value1 + " <= " + value2 + "; goto " + nextInstruction);
        } else {
            JVMLogger.log(JVMLogger.TAG_INSTR_JUMP, "IfIcmple: " + value1 + " > " + value2);
        }
    }
}
