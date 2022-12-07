package Patterns;

/*Visitor pattern with two methods
 * One for the User and one of the Group 
 */
import fake.Twater.Group;
import fake.Twater.User;

public interface Visitor {

    public void visit(User user);
    public void print(String testMessage); 
    public void visit(Group group);
}
