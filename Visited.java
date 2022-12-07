package Patterns;

import javax.swing.JOptionPane;

import fake.Twater.Group;
import fake.Twater.User;

public class Visited implements Visitor
{


    @Override
    public void visit(User user) 
    {
        JOptionPane.showMessageDialog(null, "Total # of Users: " + user.getUsers().size());
    }

    @Override
    public void print(String testMessageString) {
        // TODO Auto-generated method stub
        System.out.println(testMessageString); 
        
    }

    @Override
    public void visit(Group group) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
