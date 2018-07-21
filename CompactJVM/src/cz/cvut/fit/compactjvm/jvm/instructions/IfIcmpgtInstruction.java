package cz.cvut.fit.compactjvm.jvm.instructions;

import cz.cvut.fit.compactjvm.exceptions.LoadingException;
import cz.cvut.fit.compactjvm.jvm.StackFrame;
import cz.cvut.fit.compactjvm.jvm.JVMLogger;
import cz.cvut.fit.compactjvm.structures.*;

/**
 * if value1 is greater than or equal to value2, branch to instruction at
 * branchoffset (signed short constructed from unsigned bytes branchbyte1 << 8 +
 * branchbyte2) @author Adam V
 *
 * esecky
 */
public class IfIcmpgtInstruction {

    public static void run(StackFrame stackFrame) throws LoadingException {

        int nextInstruction = stackFrame.loadInstructionJumpAddr();

        SIntable value2 = stackFrame.operandStack.pop();
        SIntable value1 = stackFrame.operandStack.pop();

        SInt val1 = value1.toInt();
        SInt val2 = value2.toInt();

        if (val1.getValue() > val2.getValue()) {
            stackFrame.setCurrentInstructionIndex(nextInstruction);
            JVMLogger.log(JVMLogger.TAG_INSTR_JUMP, "IfIcmpgt: " + value1 + " > " + value2 + "; goto " + nextInstruction);
        } else {
            JVMLogger.log(JVMLogger.TAG_INSTR_JUMP, "IfIcmpgt: " + value1 + " <= " + value2);
        }
    }

}
