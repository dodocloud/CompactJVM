/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.compactjvm.classfile;

import cz.cvut.fit.compactjvm.structures.SLong;
import cz.cvut.fit.compactjvm.structures.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Field definition entity, used when a field is needed by an instruction
 *
 * @author Adam Vesecky
 */
public final class FieldDefinition {

    private String fieldClass;
    private String fieldName;
    private String fieldDescriptor;
    private List<String> fieldParams;
    private String returnType;
    private SStruct value;

    public FieldDefinition(String fieldClass, String fieldName, String fieldDescriptor) {
        this.fieldClass = fieldClass;
        this.fieldName = fieldName;
        this.fieldDescriptor = fieldDescriptor;
        fieldParams = new ArrayList<>();
        parseFieldDescriptor();
    }

    public String getFieldClass() {
        return fieldClass;
    }

    public String getFieldName() {
        return fieldName;
    }

    public List<String> getFieldParams() {
        return fieldParams;
    }

    public String getReturnType() {
        return returnType;
    }

    public String getFieldDescriptor() {
        return fieldDescriptor;
    }

    public SStruct getValue(){
        return this.value;
    }
    
    private void parseFieldDescriptor() {
        String fieldDefS = fieldDescriptor.substring(fieldDescriptor.indexOf(")") + 1);
        fieldDefS = fieldDefS.substring(0, 1);

        switch (fieldDefS) {
            case "B":
                value = new SInt(0);
                break;
            case "C":
                value = new SFloat(0.0f);
                break;
            case "D":
                value = new SFloat(0.0f);
                break;
            case "F":
                value = new SFloat(0.0f);
                break;
            case "I":
                value = new SInt(0);
                break;
            case "J":
                value = new SLong(0l);
                break;
            case "L":
                value = new SObjectRef();
                break;
            case "S":
                value = new SInt(0);
                break;
            case "Z":
                value = new SInt(0);
                break;
            case "[":
                value = new SArrayRef(-1);
                break;
            default:
                break;
        }

    }

}
