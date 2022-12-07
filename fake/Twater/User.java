package fake.Twater;

import java.util.*;
import java.util.HashMap;

import javax.swing.JButton;

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
    private String uniqueID; 
    private long creationTime; 
    private long lastUpdateTime; 
    
    
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

    public boolean checkUserID(User use) {
        for (int i = 0; i < users.size(); i++) {
        }
        return true; 
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


    @Override
    public void notifyUser()
    {
        for(Observer observe : followers)
        {
            observe.update(tweets.get(tweets.size()-1), this);
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
   

    public long getLastUpdateTime() {

        return lastUpdateTime; 

    }

    public void setLastUpdateTime(long updateTime) {

        this.lastUpdateTime = updateTime; 
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
            this.uniqueID = generateID(10); 
            System.out.println(uniqueID); 
            this.creationTime = System.currentTimeMillis();
            System.out.println(creationTime);
        }
        else
        {
            System.out.println("There is already an existing ID."); 
        }
    }

    @Override
    public void accept(Visitor visit) {
        // TODO Auto-generated method stub
        visit.visit(this); 
    }
}
