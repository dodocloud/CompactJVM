/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.compactjvm.jvm.instructions;

import cz.cvut.fit.compactjvm.jvm.StackFrame;
import cz.cvut.fit.compactjvm.logging.JVMLogger;

/**
 *
 * @author Adam Vesecky
 */
public class ALoadInstruction {

    public static void run(StackFrame stackFrame){
        // for instruction ALOAD where index is specified by parameter
        byte index = stackFrame.loadInstructionSingleParam();
        run(stackFrame, index);
    }
    
    public static void run(StackFrame stackFrame, int index) {
        int value = stackFrame.localVariables.getInt(index);
        stackFrame.operandStack.pushInt(value);
        
        JVMLogger.log(JVMLogger.TAG_INSTR, "ALoad; index: "+index+"; value: "+value);
    }

}