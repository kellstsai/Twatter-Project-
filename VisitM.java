package Patterns;

import javax.swing.JOptionPane;

import fake.Twater.Group;
import fake.Twater.User;


public class VisitM implements Visitor
{

    private int visits;
    private int number; 


    @Override
    public void visit(Group userGroup) {
        throw new UnsupportedOperationException("Invalid"); 
    }

    private void setVisit(int numberOfVisits) {
        this.visits = numberOfVisits; 
    }

    private int getNumberOfVisits() {
        return this.getNumberOfVisits(); 
    }

    @Override
    public void print(String testMessageString) {
        // TODO Auto-generated method stub
        System.out.println(testMessageString); 
        
    }


    @Override
    public void visit(User user) {
        int count = 0;
        for(Object object : user.getUsers())
        {
            User userGroup = (User) object;
            count += userGroup.getTweets().size();
        }
        JOptionPane.showMessageDialog(null, "Total # of Twats: " + count);
    }

   
}
