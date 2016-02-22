package by.bsu.fpm.mosendz.cleancode;

import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

class HistoryList extends ArrayList<Message> {
    public void showAll() {
        this.forEach(System.out::println);
    }

    public int show(Date start, Date finish) {
        Date date;
        int count = 0;
        for (Message local : this) {
            date = local.getTimestamp();
            if (date.after(start) && date.before(finish)) {
                count++;
                System.out.println(local);
            }
        }
        return count;
    }

    public boolean delete(String id) {
        for (Message local : this) {
            if (local.hasID(id)) {
                remove(local);
                return true;
            }
        }
        return false;
    }

    public int searchAuthor(String author) {
        int count = 0;
        for (Message local : this) {
            if (local.fromAuthor(author)) {
                System.out.println(local);
                ++count;
            }
        }
        return count;
    }

    public int searchKeyword(String keyword) {
        int count = 0;
        for (Message local : this) {
            if (local.contains(keyword)) {
                ++count;
                System.out.println(local);
            }
        }
        return count;
    }

    public int searchExpression(String exp) {
        int count = 0;
        Pattern p = Pattern.compile(exp);
        for (Message local : this) {
            if (local.contains(p)) {
                ++count;
                System.out.println(local);
            }
        }
        return count;
    }
}
