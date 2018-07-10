/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.compactjvm.jvm.instructions;

import cz.cvut.fit.compactjvm.exceptions.LoadingException;
import cz.cvut.fit.compactjvm.jvm.StackFrame;
import cz.cvut.fit.compactjvm.logging.JVMLogger;
import cz.cvut.fit.compactjvm.structures.*;

/**
 * store a reference into a local variable #index (or 0, 1, 2, 3)
 * @author Adam Vesecky
 */
public class AStoreNInstruction {
    
    public static void run(StackFrame stackFrame) throws LoadingException{
        int index = stackFrame.loadInstructionSingleParam();
        run(stackFrame, index);
    }
    
    public static void run(StackFrame stackFrame, int index) throws LoadingException{
        
        SGenericRef value = stackFrame.operandStack.pop();
        JVMLogger.log(JVMLogger.TAG_INSTR, "AStoreN: index: "+index+"; value:"+value);
        stackFrame.localVariables.setVar(index, value);
    }
}
