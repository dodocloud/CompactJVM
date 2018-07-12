/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.compactjvm.jvm.instructions;

import cz.cvut.fit.compactjvm.classfile.Word;
import cz.cvut.fit.compactjvm.exceptions.LoadingException;
import cz.cvut.fit.compactjvm.jvm.StackFrame;
import cz.cvut.fit.compactjvm.jvm.JVMLogger;
import cz.cvut.fit.compactjvm.structures.SInt;

/**
 * Store short into local variable. int value is popped from the operand stack.
 * https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.istore
 * @author Nick Nemame
 */
public class SiPushInstruction {
    
    /**
     * 
     * @param stackFrame 
     */
    public static void run(StackFrame stackFrame) throws LoadingException{
        byte[] bytes = stackFrame.loadInstructionParams(2);
        int value = Word.fromByteArray(bytes);
        JVMLogger.log(JVMLogger.TAG_INSTR, "SiPush: "+value);
        stackFrame.operandStack.push(new SInt(value));
    }

}
