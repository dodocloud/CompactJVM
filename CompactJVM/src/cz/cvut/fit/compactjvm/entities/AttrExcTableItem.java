package cz.cvut.fit.compactjvm.entities;

/**
 * Exception table item
 * 
 * @author Adam Vesecky
 */
public class AttrExcTableItem {
    public int startPc;
    public int endPc;
    public int handlerPc;
    public int catchType;
}
