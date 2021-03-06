package satsolver;

import compactjvm.proxy.JVMFunctions;
import compactjvm.proxy.TextReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Adam Vesecky
 */
public class FileLoader {
 
    Clauses clauses = null;
    int variablesCount;
    
    public FileLoader(String filename) throws Exception {
        
        TextReader reader = new TextReader(filename);
        int clausesCount;
        int clauseIndex = 0;
        String instanceString;
        while ((instanceString = reader.nextLine()) != null) {
            String[] tokens = instanceString.split(" ");
            // String.equals somehow doesn't work ...
            if(tokens[0].charAt(0) == 'c') { 
                JVMFunctions.println(instanceString.substring(2));
            } else if(tokens[0].charAt(0) == 'p') {
                variablesCount = JVMFunctions.parseInt(tokens[1]);
                clausesCount = JVMFunctions.parseInt(tokens[2]);
                clauses = new Clauses(clausesCount);
            } else {
                int[] clauseLiterals = new int[tokens.length - 1];
                for(int i = 0; i < tokens.length; ++i) {
                    int value = JVMFunctions.parseInt(tokens[i]);
                    if(value == 0) break;
                    clauseLiterals[i] = value;
                }
                Clause clause = new Clause(clauseLiterals);
                clauses.set(clauseIndex, clause);
                ++clauseIndex;
            }
        }
    }
    
    public Clauses getClauses() {
        return clauses;
    }
    
    public int getVariablesCount() {
        return variablesCount;
    }
    
}
