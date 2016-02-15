package by.bsu.fpm.mosendz.cleancode;


import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by User on 13.02.2016.
 */
public class Message implements Comparable<Message>{
    private String id;
    private String message;
    private String author;
    private long timestamp;
    public String toString()
    {
        return "id: "+id+"\nauthor: "+author+"\ntime: "+(new Date(timestamp)+"\nmessage: "+message);
    }
    public Message(String i,String a,String m,Date d){
        id = i; message = m; author=a; timestamp=d.getTime();
    }
    @Override
    public int compareTo(Message o) {
        return Long.valueOf(timestamp).compareTo(timestamp);
    }
    public boolean contains(Pattern p){
        return p.matcher(message).matches();
    }
    public boolean contains(String s){
        return message.contains(s);
    }
    public boolean fromAuthor(String s){
        return author.equals(s);
    }
    public boolean hasID(String s){
        return id.equals(s);
    }
    public long getTimestamp(){
        return timestamp;
    }
}
