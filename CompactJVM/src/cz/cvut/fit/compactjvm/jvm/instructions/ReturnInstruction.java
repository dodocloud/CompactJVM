package cz.cvut.fit.compactjvm.jvm.instructions;

import cz.cvut.fit.compactjvm.exceptions.LoadingException;
import cz.cvut.fit.compactjvm.jvm.JVMStack;
import cz.cvut.fit.compactjvm.jvm.JVMLogger;
import cz.cvut.fit.compactjvm.structures.*;

/**
 * Return void from method. The interpreter then returns control to the invoker
 * of the method, reinstating the frame of the invoker.
 * https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.return
 *
 * @author Nick Nemame
 */
public class ReturnInstruction {


    public static void run(JVMStack stack) throws LoadingException{
        
        while(!stack.getCurrentFrame().operandStack.isEmpty()){
            stack.getCurrentFrame().operandStack.pop();
        }
        stack.removeCurrentFrame();
        JVMLogger.log(JVMLogger.TAG_INSTR, "Executed return instruction");
    }
}
