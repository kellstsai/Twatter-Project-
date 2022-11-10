package Patterns;

import java.util.*;

public abstract class ManagerUser
{
    protected String id;
    
    public abstract void add(ManagerUser manage);
    
    public String getUserID()
    {
        return id;
    }
    @Override
    public String toString() {
        return id;
    }
    public abstract ArrayList<ManagerUser> getMembers();
    public abstract void accept(Visitor visit);

    
}
