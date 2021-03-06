package compactjvm.cpentities;

import compactjvm.definitions.ConstantPoolType;

/**
 * Constant pool float entity
 * @author Adam Vesecky
 */
public class CPFloat extends CPEntity{
    
    public float floatVal;
    
    public CPFloat(){
        super(ConstantPoolType.CPT_Float);
    }
    
    @Override
    public int getSize() {
        return 1;
    }
}
