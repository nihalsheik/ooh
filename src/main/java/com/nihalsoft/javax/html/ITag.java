package com.nihalsoft.javax.html;

public interface ITag {

    Object append(ITag tag);

    Object append(int index, ITag tag);

    String toString(int indent);
    
    ITag parent();

    Object setParent(ITag tag);
    
    Object remove();
    
    Object removeChild(ITag tag);
    
    // Todo : Need to remove
    void setIndex(int index);
    
    int index();
    
    Object addClass(String className);
    
    Object removeClass(String className);
    
    boolean hasClass(String className);
}
