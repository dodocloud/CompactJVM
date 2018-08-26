package compactjvm.parsing;

import compactjvm.classfile.ClassFile;
import compactjvm.cpentities.CPUtf8;
import compactjvm.attributes.Attribute;
import compactjvm.classfile.FLEntity;
import compactjvm.exceptions.ParsingException;
import java.io.DataInputStream;
import java.io.IOException;
import compactjvm.jvm.JVMLogger;

/**
 * Parser for fieldinfo
 *
 * @author Adam Vesecky
 */
public class FieldInfoParser {

    public int nextFieldOffset = 0;
    
    public FLEntity parseFieldEntity(ClassFile cls, DataInputStream dis) throws IOException, ParsingException {
        FLEntity ent = new FLEntity();
        ent.accessFlags = dis.readShort();
        ent.nameIndex = dis.readShort();
        ent.descriptorIndex = dis.readShort();
        ent.attributesCount = dis.readShort();

        // nameindex and descriptorindex must point into constant pool UTF8 entity
        String name = ((CPUtf8) cls.cpEntities[ent.nameIndex]).value;
        String descriptor = ((CPUtf8) cls.cpEntities[ent.descriptorIndex]).value;

        ent.name = name;
        ent.descriptor = descriptor;
        
        ent.dataFieldOffset = getFieldOffset(descriptor);
        
        JVMLogger.log(JVMLogger.TAG_PARSING, "Parsed field entity; access flags: " + ent.accessFlags
                + " ;name: " + name + " ;descriptor:" + descriptor
                + " ;attributesCount: " + ent.attributesCount,4);
        
        if (ent.attributesCount != 0) {
            JVMLogger.log(JVMLogger.TAG_PARSING, "Parsing attributes",4);
            
            ent.attrs = new Attribute[ent.attributesCount];

            AttributeParser parser = new AttributeParser();
            
            for (int i = 0; i < ent.attributesCount; i++) {
                Attribute attr = parser.parseAttributeEntity(cls,dis);
                ent.attrs[i] = attr;
            }
        }
        return ent;
    }

    // calculates an offset in data part of each object in the heap
    private int getFieldOffset(String descriptor) {
        int currentFieldOffset = nextFieldOffset;
        nextFieldOffset += 1;//("J".equals(descriptor) || "D".equals(descriptor)) ? 2 : 1;
        return currentFieldOffset;
    }

    public int getRecursiveFieldCount() {
        return nextFieldOffset;
    }
    
}
