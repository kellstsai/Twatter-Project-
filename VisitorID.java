package Patterns;

import javax.swing.JOptionPane;

import fake.Twater.Group;
import fake.Twater.User;

public class VisitorID implements Visitor {

    @Override
    public void visit(User user) {
        // TODO Auto-generated method stub
        JOptionPane.showMessageDialog(null, "All usernames are unique: " + user.checkUserID(user));
        
    }

    @Override
    public void print(String testMessage) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visit(Group group) {
        // TODO Auto-generated method stub
        
    }
    
    
}
