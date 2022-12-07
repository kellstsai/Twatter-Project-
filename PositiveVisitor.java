package fake.Twater;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import Patterns.Visitor;

public class PositiveVisitor implements Visitor
{

    @Override
    public void visit(Group group) {
        
    }

    @Override
    public void print(String testMessageString) {
        // TODO Auto-generated method stub
        System.out.println(testMessageString); 
        
    }
    
    @Override
    public void visit(User user) {

        
        ArrayList<String> words = new ArrayList<String>();
        File pos = new File("C:\\Users\\Kelly\\Desktop\\Practice codes\\test\\CS356-Mini-Twitter\\src\\fake\\Twater\\Positive");
        //private Logger logger = Logger.getLogger(); 
        double count = 0;
        double countTotal = 0;
        
        try {
            Scanner s = new Scanner(pos);
            while(s.hasNextLine())
            {
                words.add(s.nextLine());
            }
        } catch (FileNotFoundException except) {
            Logger.getLogger(PositiveVisitor.class.getName()).log(Level.SEVERE, null, except);

        }
        
        for(Object object : user.getUsers())
        {
            User use = (User) object;
            for(String str : use.getTweets())
            {
                for(String word : words)
                {
                    if(str.toLowerCase().contains(word)){
                        countTotal++;
                    }
                }
                count += str.split(" ").length;
            }
        }
        JOptionPane.showMessageDialog(null, "Percentage of Positive Words: " + countTotal/count + "%");
    }

}
