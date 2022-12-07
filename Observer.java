package Patterns;

public interface Observer 
{
    public void print(String print); 
    public void update(String message, Subject subject);
    public void makeMessageInNewsFeed(); 
    public void updateUser(int user); 
}
