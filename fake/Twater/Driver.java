package fake.Twater;

/*Driver class to run and ensure all methods are working
 Twatter == Twats
 */
public class Driver 
{

    //newsfeed doesn't display automatically for me
    //you have to drag the bottom of the user view down 
    //sorry 
    public static void main(String[] args)
    {
        AdminControlPanel panel = AdminControlPanel.getInstance();
    
        //allow the gui to be visible to the user
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                panel.setVisible(true);
            }
        });
    }
    
}

