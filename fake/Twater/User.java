package fake.Twater;

import java.util.*;
import java.util.HashMap;

import Patterns.ManagerUser;
import Patterns.Observer;
import Patterns.Subject;
import Patterns.Visitor;

public class User extends ManagerUser implements Subject
{

    private static HashMap<String, User> users = new HashMap<String, User>();
    private TwatFeed newsfeed;
    private String group;
    private ArrayList<String> following;
    private ArrayList<String> tweets;
    private ArrayList<Observer> followers;
    
    
    public TwatFeed getFeed()
    {
        return newsfeed;
    }

    public String getGroup()
    {
        return group;
    }

    public ArrayList<String> getTweets()
    {
        return tweets;
    }

    @Override
    public Object getUpdate(Observer obj) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public Collection getUsers()
    {
        return users.values();
    }

    
    public static boolean exists(String uid)
    {
        return users.containsKey(uid);
    }
    
    public void follow(String id)
    {
        users.get(id).register(newsfeed);
        following.add(id);
        for(String s : users.get(id).getTweets())
        {
            newsfeed.update(s, users.get(id));
        }
    }
    
    public void joinGroup(String id)
    {
        group = id;
    }
    
    public void tweet(String msg)
    {
        tweets.add(msg);
        notifyUser();
    }

    @Override
    public void register(Observer obj) 
    {
        if(obj == null)
        {
            System.out.println("Null observer");
        }
        if(!followers.contains(obj))
        {
            followers.add(obj);
        }
    }

    @Override
    public void notifyUser()
    {
        for(Observer obs : followers)
        {
            obs.update(tweets.get(tweets.size()-1), this);
        }
    }
    public boolean isFollowing(String id)
    {
        return following.contains(id);
    }
   
    @Override
    public void add(ManagerUser um) {
        System.out.println("Can't add to leaf.");
    }

    @Override
    public ArrayList<ManagerUser> getMembers() {
        return null;
    }
    
    public ArrayList<String> getFollowing()
    {
        return following;
    }

    public void setMembers() {
    
    }

    public void setFollowing() {

    }
    
    @Override
    public void accept(Visitor visit) 
    {
        visit.visit(this);
    }

    public User(String id, String group)
    {
        if(!users.containsKey(id))
        {
            this.id = id;
            followers = new ArrayList<Observer>();
            following = new ArrayList<String>();
            tweets = new ArrayList<String>();
            this.group = group;
            newsfeed = new TwatFeed();
            this.register(newsfeed);
            users.put(id, this);
        }
        else
        {
            System.out.println("There is already an existing ID."); 
        }
    }
}
