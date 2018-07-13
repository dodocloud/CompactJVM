/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.compactjvm.jvm.instructions;

import cz.cvut.fit.compactjvm.classfile.ClassFile;
import cz.cvut.fit.compactjvm.classfile.MethodDefinition;
import cz.cvut.fit.compactjvm.classfile.Word;
import cz.cvut.fit.compactjvm.exceptions.LoadingException;
import cz.cvut.fit.compactjvm.jvm.JVMStack;
import cz.cvut.fit.compactjvm.jvm.MethodArea;
import cz.cvut.fit.compactjvm.jvm.StackFrame;
import cz.cvut.fit.compactjvm.jvm.JVMLogger;
import cz.cvut.fit.compactjvm.jvm.ObjectHeap;
import cz.cvut.fit.compactjvm.natives.NativeObject;
import java.nio.ByteBuffer;
import cz.cvut.fit.compactjvm.structures.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * @author Nick Nemame
 */
public class InvokeVirtualInstruction {

    public static final int PARAM_COUNT = 2;

    public static void run(JVMStack stack, MethodArea methodArea) throws LoadingException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        byte[] bytes = stack.getCurrentFrame().loadInstructionParams(PARAM_COUNT);
        int methodRefIndex = Word.fromByteArray(bytes); //index v CP ve tride, ktera invokuje, nikoliv v te, na ktere je metoda volana
        MethodDefinition method = stack.getCurrentFrame().associatedClass.getMethodDefinition(methodRefIndex,
                stack.getCurrentFrame().associatedMethod, methodArea);

        ClassFile classFile = methodArea.getClassFile(method.getMethodClass());

        if (method.isNativeMethod()) {
            // following specification, the object we are looking for is BEFORE all parameters
            int params = method.getMethodParams().size();
            
            // on the peek there should be a object whose method is called
            SObjectRef objectRef = stack.getCurrentFrame().operandStack.get(params);
            if(!objectRef.hasNativeObject()){
                throw new LoadingException("Native object expected but not found");
            }
            
            NativeObject nativeObj = objectRef.getNativeObject();
            
            // use reflection to call native object method
            Method methodToInvoke = nativeObj.getClass().getMethod(method.getMethodName(), JVMStack.class, ObjectHeap.class);
            methodToInvoke.invoke(nativeObj, stack, stack.jvmThread.getHeap());
            
            JVMLogger.log(JVMLogger.TAG_INSTR, "InvokeVirtual native: " + method.getMethodName());
        } else {
            //Lookup metody v rodicovskych tridach, pokud neni nalezena v aktualni tride
            int methodIndex;
            while ((methodIndex = classFile.getMethodDefIndex(method.getMethodName(), method.getMethodDescriptor())) == -1) {
                if (classFile.getSuperclassName() == null) {
                    throw new LoadingException("Invoke virtual lookup failed - no method found");
                }
                classFile = classFile.getSuperClass();
            }

            StackFrame frame = new StackFrame(classFile, methodIndex, method, stack.jvmThread);
            loadArgumentsToLocalVariables(stack.getCurrentFrame(), frame, method);
            stack.push(frame);

            JVMLogger.log(JVMLogger.TAG_INSTR, "InvokeVirtual: " + method.getMethodName());
        }
    }

    /**
     * Nactu ze zasobniku
     *
     * @param currentFrame
     * @param newFrame
     * @param method
     */
    public static void loadArgumentsToLocalVariables(StackFrame currentFrame, StackFrame newFrame, MethodDefinition method) throws LoadingException {

        JVMLogger.log(JVMLogger.TAG_INSTR, "LoadArgumentsToLocalVariables");

        int locIndex = method.getMethodParamsWordsCount() + 1; // +1 kvuli tomu, ze 1. prvek je objectRef
        //Kdyz od locIndex odectu pocet slov vkladaneho argumentu, pak dostanu index,
        //na ktery mam do lokalnich promennych argument vlozit
        for (int i = method.getMethodParams().size() - 1; i >= 0; --i) {
            switch (method.getMethodParams().get(i)) {
                // local variable is set once below (powerful generics will distinct types)

                case "Z": //boolean
                    locIndex -= Word.BOOLEAN_WORDS;
                    //newFrame.localVariables.setBoolean(locIndex, currentFrame.operandStack.popBoolean());
                    break;
                case "B": //byte
                    locIndex -= Word.BYTE_WORDS;
                    //newFrame.localVariables.setByte(locIndex, currentFrame.operandStack.popByte());
                    break;
                case "C": //char
                    locIndex -= Word.CHAR_WORDS;
                    //newFrame.localVariables.setChar(locIndex, currentFrame.operandStack.popChar());
                    break;
                case "S": //short
                    locIndex -= Word.SHORT_WORDS;
                    //newFrame.localVariables.setShort(locIndex, currentFrame.operandStack.popShort());
                    break;
                case "I": //int
                    locIndex -= Word.INT_WORDS;
                    //newFrame.localVariables.setInt(locIndex, currentFrame.operandStack.popInt());
                    break;
                case "J": //long
                    locIndex -= Word.LONG_WORDS;
                    //newFrame.localVariables.setLong(locIndex, currentFrame.operandStack.popLong());
                    break;
                case "F": //float
                    locIndex -= Word.FLOAT_WORDS;
                    //newFrame.localVariables.setFloat(locIndex, currentFrame.operandStack.popFloat());
                    break;
                case "D": //double
                    locIndex -= Word.DOUBLE_WORDS;
                    //newFrame.localVariables.setDouble(locIndex, currentFrame.operandStack.popDouble());
                    break;
                default: //array, class, ...
                    locIndex -= Word.REFERENCE_WORDS;
                    //newFrame.localVariables.setInt(locIndex, currentFrame.operandStack.popInt());
                    break;
            }

            newFrame.localVariables.setVar(locIndex, currentFrame.operandStack.pop());
        }
        //nastavi na pozici 0 lokalnich promennych referenci na objekt
        newFrame.localVariables.setVar(0, currentFrame.operandStack.pop());
    }

}
