/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.compactjvm.jvm.instructions;

import cz.cvut.fit.compactjvm.exceptions.LoadingException;
import cz.cvut.fit.compactjvm.jvm.AnotherGarbageCollector;
import cz.cvut.fit.compactjvm.jvm.MethodArea;
import cz.cvut.fit.compactjvm.jvm.StackFrame;
import cz.cvut.fit.compactjvm.jvm.JVMLogger;
import cz.cvut.fit.compactjvm.structures.*;

/**
 * Substract int
 * The values are popped from the operand stack.
 * The int result is value1 - value2. The result is pushed onto the operand stack.
 * https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.isub
 * @author Nick Nemame
 */
public class ISubInstruction {
    
    public static void run(StackFrame stackFrame)throws LoadingException {
        SInt operand2 = stackFrame.operandStack.pop();
        SInt operand1 = stackFrame.operandStack.pop();
        
        JVMLogger.log(JVMLogger.TAG_INSTR, "ISub: "+operand1+" - "+operand2);
        
        SStruct value = new SInt(operand1.getValue() - operand2.getValue());
        stackFrame.operandStack.push(value);
        
      
        // run garbage collector
        //new AnotherGarbageCollector(stackFrame.jvmThread.getHeap(),stackFrame.jvmThread.getStack()).runGC();
    }

}