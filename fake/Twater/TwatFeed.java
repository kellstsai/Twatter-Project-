package fake.Twater;

import java.util.ArrayList;

import Patterns.Observer;
import Patterns.Subject;

public class TwatFeed implements Observer, Subject
{
    ArrayList<String> feed;
    ArrayList<Observer> followers;

    
    @Override
    public void print(String print) {
        // TODO Auto-generated method stub
        System.out.println(print); 
        
    }

    @Override
    public void updateUser(int user) {
        // TODO Auto-generated method stub
        return; 
        
    }

    public TwatFeed()
    {
        feed = new ArrayList<String>();
        followers = new ArrayList<Observer>();
    }

    @Override
    public void update(String message, Subject subject) 
    {
        User user = (User)subject;
        feed.add(user.getUserID()+ ": " + message);
        notifyUser();
    }
    
    public ArrayList<String> getFeed()
    {
        return feed;
    }

    @Override
    public void register(Observer object) {
        if(object == null)
        {
            System.out.println("Invalid");
        }
        if(!followers.contains(object))
        {
            followers.add(object);
        }
    }

    @Override
    public void notifyUser() {
        for(Observer observer : followers)
        {
            observer.update(null, this);
        }
    }



    @Override
    public Object getUpdate(Observer obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    @Override
    public void makeMessageInNewsFeed() {
        // TODO Auto-generated method stub
        return; 
        
    }



    
}
