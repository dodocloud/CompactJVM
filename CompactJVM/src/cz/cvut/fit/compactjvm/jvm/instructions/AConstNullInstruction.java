/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.compactjvm.jvm.instructions;

import cz.cvut.fit.compactjvm.exceptions.LoadingException;
import cz.cvut.fit.compactjvm.jvm.ObjectHeap;
import cz.cvut.fit.compactjvm.jvm.StackFrame;
import cz.cvut.fit.compactjvm.structures.SArrayRef;
import cz.cvut.fit.compactjvm.structures.SInt;
import cz.cvut.fit.compactjvm.structures.SObjectRef;
import cz.cvut.fit.compactjvm.jvm.JVMLogger;

/**
 * Push null (nothing more)
 * @author Adam Vesecky
 */
public class AConstNullInstruction {
    
    public static void run(StackFrame stackFrame) throws LoadingException{
        
        // just push null object
        stackFrame.operandStack.push(new SObjectRef());
        
        JVMLogger.log(JVMLogger.TAG_INSTR, "AConstNull");
    }
    
}
