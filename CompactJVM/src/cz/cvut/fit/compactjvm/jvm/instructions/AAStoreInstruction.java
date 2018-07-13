/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.compactjvm.jvm.instructions;

import cz.cvut.fit.compactjvm.exceptions.ArrayOutOfBoundsException;
import cz.cvut.fit.compactjvm.exceptions.LoadingException;
import cz.cvut.fit.compactjvm.exceptions.OutOfHeapMemException;
import cz.cvut.fit.compactjvm.jvm.ObjectHeap;
import cz.cvut.fit.compactjvm.jvm.StackFrame;
import cz.cvut.fit.compactjvm.structures.SArrayRef;
import cz.cvut.fit.compactjvm.structures.SGenericRef;
import cz.cvut.fit.compactjvm.structures.SInt;
import cz.cvut.fit.compactjvm.structures.SObjectRef;
import cz.cvut.fit.compactjvm.jvm.JVMLogger;
import cz.cvut.fit.compactjvm.jvm.MethodArea;

/**
 * 0x53 -> Store into reference array
 * 
 * @author Adam Vesecky
 */
public class AAStoreInstruction {
    
    public static void run(StackFrame stackFrame, ObjectHeap heap, MethodArea methodArea) throws LoadingException, ArrayOutOfBoundsException, OutOfHeapMemException{
        
        SObjectRef value = stackFrame.operandStack.pop();
        SInt index = stackFrame.operandStack.pop();
        SArrayRef arrayRef = stackFrame.operandStack.pop();
        
        if(index.getValue() >= arrayRef.getSize()){
            AAAException.throwException(new ArrayOutOfBoundsException("Maximum index is "+(arrayRef.getSize() - 1)+", "+index.getValue()+" given.")
                    , stackFrame.jvmThread.getStack(), heap, methodArea);
            //throw new ArrayOutOfBoundsException("Maximum index is "+(arrayRef.getSize() - 1)+", "+index.getValue()+" given.");
            return;
        }
        
        SGenericRef[] ref = heap.readObjectArrayFromHeap(arrayRef.getReference());
        ref[index.getValue()] = value;
        
        JVMLogger.log(JVMLogger.TAG_INSTR, "AAStoreN: object array: "+arrayRef.getArrayType().className+"["+index.getValue()+"] = "+value);
    }
}
