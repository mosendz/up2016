package by.bsu.fpm.mosendz.cleancode;


import java.util.Date;
import java.util.regex.Pattern;

class Message implements Comparable<Message> {
    private String id;
    private String message;
    private String author;
    private Date timestamp;

    public String toString() {
        return "id: " + id + "\nauthor: " + author + "\ntime: " + timestamp + "\nmessage: " + message;
    }

    public Message(String i, String a, String m, Date d) {
        id = i;
        message = m;
        author = a;
        timestamp = d;
    }

    @Override
    public int compareTo(Message o) {
        return timestamp.compareTo(timestamp);
    }

    public boolean contains(Pattern p) {
        return p.matcher(message).matches();
    }

    public boolean contains(String s) {
        return message.contains(s);
    }

    public boolean fromAuthor(String s) {
        return author.equals(s);
    }

    public boolean hasID(String s) {
        return id.equals(s);
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
