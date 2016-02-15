package by.bsu.fpm.mosendz.cleancode;

import java.util.ArrayList;
import java.util.Date;
import java.util.regex.*;

/**
 * Created by User on 13.02.2016.
 */
public class History extends ArrayList<Message> {
    public void showAll(){
        for(Message local:this) System.out.println(local);
    }
    public boolean show(long t1, long t2){
        boolean found = false;
        long time;
        int count = 0;
        for(Message local:this){
            time = local.getTimestamp();
            if (time>=t1 && time<=t2){
                count++;
                found = true;
                System.out.println(local);
            }
        }
        System.out.println(new Date()+" Found messages between "+new Date(t1)+" and "+new Date(t2)+" : "+count);
        return found;
    }
    public boolean delete(String id) {
        for(Message local:this) {
            if(local.hasID(id)) {
                remove(local);
                return true;
            }
        }
            return false;
    }
    public boolean searchAuthor(String author) {
        boolean found = false;
        int count = 0;
        for(Message local:this){
            if(local.fromAuthor(author))
            {
                found = true;
                System.out.println(local);
                ++count;
            }
        }
        MyProject.logWriter.println(new Date()+" Found by author: "+count+" messages");
        return found;
    }
    public boolean searchKeyword(String keyword) {
        boolean found = false;
        int count = 0;
        for(Message local:this){
            if(local.contains(keyword)){
                found = true;
                ++count;
                System.out.println(local);
            }
        }
        MyProject.logWriter.println(new Date()+" Found by keyword: "+count+" messages");
        return found;
    }
    public boolean searchExpression(String exp){
        boolean found = false;
        int count = 0;
        Pattern p = Pattern.compile(exp);
        for(Message local:this){
            if (local.contains(p)){
                found = true;
                ++count;
                System.out.println(local);
            }
        }
        MyProject.logWriter.println(new Date()+" Found by regular expression: "+count+" messages");
        return found;
    }
}
