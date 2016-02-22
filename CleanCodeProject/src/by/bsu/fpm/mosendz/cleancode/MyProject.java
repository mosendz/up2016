package by.bsu.fpm.mosendz.cleancode;

import java.io.*;
import java.text.ParseException;
import java.util.Scanner;

class MyProject {


    public static void main(String[] args) throws IOException, ParseException {
        Scanner in = new Scanner(System.in);
        History h = new History();
        System.out.println("1 - загрузка из файла" +
                "\n2 - сохранение в файл" +
                "\n3 - добавление сообщения" +
                "\n4 - просмотр истории в хронологическом порядке" +
                "\n5 - удаление сообщения" +
                "\n6 - поиск" +
                "\n7 - просмотр истории сообщений за определённый период" +
                "\n0 - выход");
        do {
            int i = in.nextInt();
            in.nextLine();
            switch (i) {
                case 1:
                    h.load();
                    break;
                case 2:
                    h.save();
                    break;
                case 3:
                    h.add();
                    break;
                case 4:
                    h.viewAll();
                    break;
                case 5:
                    h.delete();
                    break;
                case 6:
                    h.search();
                    break;
                case 7:
                    h.viewTime();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Нет такой команды");
            }
            h.getLogWriter().flush();
        } while (true);
    }


}
