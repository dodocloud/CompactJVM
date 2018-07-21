package cz.cvut.fit.compactjvm.jvm.instructions;

import cz.cvut.fit.compactjvm.classfile.MethodDefinition;
import cz.cvut.fit.compactjvm.classfile.MethodExcTableItem;
import cz.cvut.fit.compactjvm.exceptions.LoadingException;
import cz.cvut.fit.compactjvm.jvm.JVMStack;
import cz.cvut.fit.compactjvm.jvm.MethodArea;
import cz.cvut.fit.compactjvm.jvm.StackFrame;
import cz.cvut.fit.compactjvm.jvm.JVMLogger;
import cz.cvut.fit.compactjvm.structures.SObjectRef;
import java.io.IOException;

/**
 * Throws exception of error
 *
 * @author Adam Vesecky
 */
public class AThrowInstruction {

    public static void run(JVMStack stack, MethodArea methodArea) throws LoadingException, IOException {

        // first of all, get exception reference
        SObjectRef exception = stack.getCurrentFrame().operandStack.pop();

        if (exception.isNull()) {
            throw new LoadingException("Exception that shouldn't be null is null !!!");
        }

        StackFrame actualFrame;

        while (!stack.isEmpty()) {
            actualFrame = stack.getCurrentFrame();
            MethodDefinition method = actualFrame.methodDefinition;

            JVMLogger.log(JVMLogger.TAG_INSTR_JUMP, "AThrow:: " + exception.getClassFile().getClassName());
            
            if (method == null) {
                // propably main method
                throw new RuntimeException("Method definition not found and exception has been thrown!! Leaving main method and exiting... ");
            }

            MethodExcTableItem[] excTable = method.getExceptionTable();

            // search exception handler in exception table
            if (excTable != null) {
                for (int i = 0; i < excTable.length; i++) {
                    MethodExcTableItem excItem = excTable[i];

                    if (excItem.startPc <= actualFrame.getCurrentInstructionIndex()
                            && (excItem.endPc) >= actualFrame.getCurrentInstructionIndex()) {
                        if (excItem.catchClass != null && methodArea.isSuperClass(excItem.catchClass, exception.getClassFile())) {
                            actualFrame.setCurrentInstructionIndex(excItem.handlerPc);
                            actualFrame.operandStack.push(exception);

                            JVMLogger.log(JVMLogger.TAG_INSTR_JUMP, "Catched as a " + excItem.catchClass.getClassName());
                            return;
                        }
                    }
                }
            }
            stack.removeCurrentFrame();
        }

        throw new RuntimeException("No exception handler found -> EXIT");
    }
}
