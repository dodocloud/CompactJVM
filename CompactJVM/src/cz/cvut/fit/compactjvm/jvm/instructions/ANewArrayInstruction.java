/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.compactjvm.jvm.instructions;

import cz.cvut.fit.compactjvm.core.ClassFile;
import cz.cvut.fit.compactjvm.core.Word;
import cz.cvut.fit.compactjvm.entities.CPClass;
import cz.cvut.fit.compactjvm.entities.CPUtf8;
import cz.cvut.fit.compactjvm.exceptions.LoadingException;
import cz.cvut.fit.compactjvm.exceptions.OutOfHeapMemException;
import cz.cvut.fit.compactjvm.jvm.MethodArea;
import cz.cvut.fit.compactjvm.jvm.ObjectHeap;
import cz.cvut.fit.compactjvm.jvm.StackFrame;
import cz.cvut.fit.compactjvm.logging.JVMLogger;
import cz.cvut.fit.compactjvm.structures.SArrayRef;
import cz.cvut.fit.compactjvm.structures.SInt;
import cz.cvut.fit.compactjvm.structures.SObjectRef;

/**
 * Instruction for allocating object array
 * 
 * @author Adam Vesecky
 */
public class ANewArrayInstruction {
 
    
    public static void run(StackFrame stackFrame, MethodArea methodArea, ObjectHeap heap) throws LoadingException, OutOfHeapMemException{

        // get class name 
        byte[] bytes = stackFrame.loadInstructionParams(2);
        int cpIndex = Word.fromByteArray(bytes);
        int classNameIndex = ((CPClass) stackFrame.associatedClass.cpEntities[cpIndex]).nameIndex;
        String className = ((CPUtf8) stackFrame.associatedClass.cpEntities[classNameIndex]).value;
        ClassFile cls = methodArea.getClassFile(className);
        
        // get array size
        SInt size = stackFrame.operandStack.pop();
        
        if(size.getValue() < 0) throw new LoadingException("Array size is lower than 0 !!");
        
        SArrayRef arrayReference = heap.allocObjectArray(cls, size.getValue());

        stackFrame.operandStack.push(arrayReference);
        
        JVMLogger.log(JVMLogger.TAG_INSTR, "New object array: "+className);

    } 
}