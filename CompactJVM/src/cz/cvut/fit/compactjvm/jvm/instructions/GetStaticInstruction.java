/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.compactjvm.jvm.instructions;

import cz.cvut.fit.compactjvm.classfile.ClassFile;
import cz.cvut.fit.compactjvm.classfile.FieldDefinition;
import cz.cvut.fit.compactjvm.cpentities.CPEntity;
import cz.cvut.fit.compactjvm.cpentities.CPFieldRef;
import cz.cvut.fit.compactjvm.classfile.FLEntity;
import cz.cvut.fit.compactjvm.exceptions.LoadingException;
import cz.cvut.fit.compactjvm.jvm.JVMStack;
import cz.cvut.fit.compactjvm.jvm.MethodArea;
import cz.cvut.fit.compactjvm.jvm.StackFrame;
import static cz.cvut.fit.compactjvm.jvm.instructions.InvokeStaticInstruction.loadArgumentsToLocalVariables;
import cz.cvut.fit.compactjvm.jvm.JVMLogger;


/**
 * @author Nick Nemame
 */
public class GetStaticInstruction {
    
    /**
     * 
     * @param stackFrame 
     */
    public static void run(JVMStack stack, MethodArea methodArea) throws LoadingException {
        //byte localVariableIndex = stack.getCurrentFrame().loadInstructionSingleParam();
 
        //FieldDefinition fieldDef = stack.getCurrentFrame().associatedClass.getFieldDefinition(localVariableIndex);
        //ClassFile cls = methodArea.getClassFile(fieldDef.getFieldClass());
        //FLEntity field = cls.getFieldInfo(fieldDef.getFieldName(), fieldDef.getFieldDescriptor());
        
        JVMLogger.log(JVMLogger.TAG_INSTR, "GetStatic TODO");
        
        // todo ...
    }

}
