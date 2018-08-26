package compactjvm.jvm;

import compactjvm.parsing.ClassFileLoader;
import compactjvm.exceptions.ParsingException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/** ==========================================================================
      ██╗██╗     ██╗ ███╗   ███╗
      ██║██║     ██║ ████╗ ████║
      ██║██║     ██║  ██╔████╔██║
██   ██║╚██╗  ██╔╝  ██║╚██╔╝██║
╚█████╔╝╚████╔╝    ██║ ╚═╝ ██║
 ╚════╝   ╚═══╝  ╚═╝     ╚═╝
 * Java Virtual Machine
 * @author Adam Vesecky
 * ===========================================================================
 */
public class CompactJVM {
    private static CompactJVM instance;
    private final MethodArea methodArea;
    private final ObjectHeap heap;
    
    private List<JVMThread> threads = new LinkedList<>();
    
    public static CompactJVM getInstance(){
        return instance;
    }
    
    public CompactJVM(int heapSize){
        // this type of access is only for logger :-)
        instance = this; 
        ClassFileLoader classLoader = new ClassFileLoader();
        methodArea = new MethodArea(classLoader);
        heap = new ObjectHeap(methodArea, heapSize); // one heap per instance
    }
    
    public List<JVMThread> getThreads() {
        return threads;
    }
    
    public void loadApplication(String classPath, String libraryPath, String mainClass, String[] arguments) 
            throws IOException, ParsingException{
        methodArea.initialize(classPath, libraryPath, mainClass, arguments);
    }
    
    public void start() throws Exception{
        JVMThread thread = new JVMThread(methodArea, heap);
        threads.add(thread);
        thread.run();
    }
    
    public MethodArea getMethodArea() {
        return methodArea;
    }
    
    public ObjectHeap getObjectHeap() {
        return heap;
    }
}