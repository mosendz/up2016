package by.bsu.fpm.mosendz.cleancode;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class MyProject {
    static History h = new History();
    static Gson gson = new GsonBuilder().create();
    static SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
    static PrintWriter logWriter;
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) throws IOException,ParseException {
        logWriter = new PrintWriter("log.txt");
        System.out.println("1 - загрузка из файла"
                + "\n2 - сохранение в файл" +
                "\n3 - добавление сообщения" +
                "\n4 - просмотр истории в хронологическом порядке" +
                "\n5 - удаление сообщения" +
                "\n6 - поиск" +
                "\n7 - просмотр истории сообщений за определённый период" +
                "\n0 - выход");
        do {
            int i = in.nextInt();
            in.nextLine();
            switch(i){
                case 1:
                    load();
                    break;
                case 2:
                    save();
                    break;
                case 3:
                    add();
                    break;
                case 4:
                    view();
                    break;
                case 5:
                    delete();
                    break;
                case 6:
                    search();
                    break;
                case 7:
                    viewTime();
                    break;
                case 0:
                    logWriter.close();
                    return;
                default:
                    System.out.println("Нет такой команды");
            }
        }while(true);
    }
    public static void load() throws IOException {
        JsonReader reader = new JsonReader(new FileReader("history.json"));
        h.clear();
        h = gson.fromJson(reader, History.class);
        System.out.println("История сообщений загружена");
        logWriter.println(new Date()+" File loaded");
        reader.close();
    }
    public static void save() throws IOException {
        JsonWriter writer = new JsonWriter(new FileWriter("history.json"));
        gson.toJson(h, History.class, writer);
        System.out.println("История сообщений сохранена");
        logWriter.println(new Date()+" File saved");
        writer.close();
    }
    public static void add() throws ParseException {
        System.out.print("Введите ID ");
        String id = in.nextLine();
        System.out.print("Введите автора ");
        String author = in.nextLine();
        System.out.println("Введите дату и время в формате HH:mm:ss dd.MM.yyyy");
        String date = in.nextLine();
        System.out.print("Введите сообщение ");
        String message = in.nextLine();
        h.add(new Message(id,author,message,format.parse(date)));
        System.out.println("Сообщение добавлено");
        logWriter.println(new Date()+" Message added");
    }
    public static void view(){
        Collections.sort(h);
        h.showAll();
        logWriter.println(new Date()+" History viewed");
    }
    public static void delete(){
        System.out.print("Введите id ");
        String id = in.nextLine();
        if (h.delete(id)) {
            System.out.println("Удаление успешно");
            logWriter.println(new Date()+" Message with ID "+id+" has been successfully deleted");
        }
        else {
            System.out.println("Сообщение с указанным ID не найдено");
            logWriter.println(new Date()+" Message with ID "+id+" was not found");
        }
    }
    public static void search(){
        System.out.println("1 - Поиск по автору" +
                "\n2 - Поиск по ключевому слову" +
                "\n3 - Поиск по регулярному выражению");
        int i = in.nextInt();
        in.nextLine();
        switch(i) {
            case 1:
                System.out.print("Введите автора ");
                if(!h.searchAuthor(in.nextLine())){
                    System.out.println("Сообщения не найдены");
                }
                break;
            case 2:
                System.out.print("Введите ключевое слово ");
                if(!h.searchKeyword(in.nextLine())){
                    System.out.println("Сообщения не найдены");
                }
                break;
            case 3:
                System.out.print("Введите регулярное выражение ");
                if(!h.searchExpression(in.nextLine())){
                    System.out.println("Сообщения не найдены");
                }
                break;
        }
    }
    public static void viewTime() throws ParseException{
        Collections.sort(h);
        System.out.println("Для ввода используется HH.mm.ss dd.MM.yyyy");
        System.out.println("Введите начальное время ");
        String s1 = in.nextLine();
        long t1 = format.parse(s1).getTime();
        System.out.println("Введите конечное время ");
        String s2 = in.nextLine();
        long t2 = format.parse(s2).getTime();
        if (!h.show(t1,t2)) System.out.println("Сообщения не найдены");
    }
}
