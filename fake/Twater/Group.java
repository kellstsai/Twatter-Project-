package fake.Twater;

import java.util.ArrayList;
import java.util.HashMap;

import Patterns.ManagerUser;
import Patterns.Visitor;

public class Group extends ManagerUser
{
    private ArrayList<ManagerUser> members;
    public static HashMap<String, Group> groups = new HashMap<String, Group>();
    private String groupID; 
    private long groupCreationTime; 
    
    public Group(String id)
    {
        members = new ArrayList<ManagerUser>();
        this.id = id;
        groups.put(id, this);
        this.groupID = generateID(10); 
        System.out.println(groupID); 

        this.groupCreationTime = System.currentTimeMillis(); 
        System.out.println(groupCreationTime); 
    }

    @Override
    public void add(ManagerUser um) 
    {
        if(um.getMembers() == null)
            members.add((User)um);
        else
            members.add((Group)um);
    }
    public ArrayList<ManagerUser> getMembers()
    {
        return members;
    }
    
    public static boolean exists(String uid)
    {
        return groups.containsKey(uid);
    }
    
    public static Group findGroup(String s)
    {
        return groups.get(s);
    }

    @Override
    public void accept(Visitor v) 
    {
        v.visit(this);
    }

    private String generateID(int length) {
        String weewoo = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder str = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int tinkle = (int) (weewoo.length() * Math.random()); 
            str.append(weewoo.charAt(tinkle)); 
        }
        return str.toString();
    }
}
