package compactjvm.cpentities;

import compactjvm.definitions.ConstantPoolType;

/**
 * Constant pool double entity
 * @author Adam Vesecky
 */
public class CPDouble extends CPEntity{
    
    public double doubleVal;
    
    public CPDouble(){
        super(ConstantPoolType.CPT_Double);
    }
    
    @Override
    public int getSize() {
        return 2;
    }
}
