package Patterns;

import fake.Twater.Group;
import fake.Twater.User;
import javax.swing.JOptionPane;

public class lastUpdateTime implements Visitor {

    @Override
    public void visit(User user) {
        // TODO Auto-generated method stub
        JOptionPane.showMessageDialog(null, "Last update time " + user.getLastUpdateTime()); 
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
