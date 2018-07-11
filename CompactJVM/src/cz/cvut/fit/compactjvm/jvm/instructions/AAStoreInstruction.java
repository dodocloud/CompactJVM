/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.compactjvm.jvm.instructions;

import cz.cvut.fit.compactjvm.exceptions.LoadingException;
import cz.cvut.fit.compactjvm.jvm.ObjectHeap;
import cz.cvut.fit.compactjvm.jvm.StackFrame;
import cz.cvut.fit.compactjvm.logging.JVMLogger;
import cz.cvut.fit.compactjvm.structures.SArrayRef;
import cz.cvut.fit.compactjvm.structures.SGenericRef;
import cz.cvut.fit.compactjvm.structures.SInt;
import cz.cvut.fit.compactjvm.structures.SObjectRef;

/**
 * 0x53 -> Store into reference array
 * 
 * @author Adam Vesecky
 */
public class AAStoreInstruction {
    
    public static void run(StackFrame stackFrame, ObjectHeap heap) throws LoadingException{
        
        SObjectRef value = stackFrame.operandStack.pop();
        SInt index = stackFrame.operandStack.pop();
        SArrayRef arrayRef = stackFrame.operandStack.pop();
        
        SObjectRef[] ref = heap.readObjectArrayFromHeap(arrayRef.getReference());
        ref[index.getValue()] = value;
        
        JVMLogger.log(JVMLogger.TAG_INSTR, "AAStoreN: object array: "+arrayRef.getArrayType().className+"["+index.getValue()+"] = "+value);
    }
}