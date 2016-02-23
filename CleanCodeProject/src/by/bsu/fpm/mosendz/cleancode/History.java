package by.bsu.fpm.mosendz.cleancode;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

class History {
    private final SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
    private final Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateTypeAdapter()).create();
    private HistoryList list;
    private PrintWriter logWriter;
    private Scanner in;

    public History(Scanner input) {
        list = new HistoryList();
        in = input;
        try {
            logWriter = new PrintWriter("log.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public PrintWriter getLogWriter() {
        return logWriter;
    }

    public void load() {
        try {
            JsonReader reader = new JsonReader(new FileReader("history.json"));
            list = gson.fromJson(reader, HistoryList.class);
            System.out.println("История сообщений загружена");
            logWriter.println(new Date() + " command:load File loaded");
            reader.close();
        } catch (IOException e) {
            logWriter.println(new Date() + " command:load Error while loading file");
        }
    }

    public void save() {
        try {
            JsonWriter writer = new JsonWriter(new FileWriter("history.json"));
            gson.toJson(list, HistoryList.class, writer);
            System.out.println("История сообщений сохранена");
            writer.close();
            logWriter.println(new Date() + " command:save File saved");
        } catch (IOException e) {
            logWriter.println(new Date() + " command:save Error while saving file");
        }
    }

    public void add() {
        try {
            System.out.print("Введите ID ");
            String id = in.nextLine();
            System.out.print("Введите автора ");
            String author = in.nextLine();
            System.out.println("Введите дату и время в формате HH:mm:ss dd.MM.yyyy");
            String date = in.nextLine();
            System.out.print("Введите сообщение ");
            String message = in.nextLine();
            int maxMessageLength = 140;
            if (message.length() > maxMessageLength) {
                logWriter.println(new Date() + " command:add Entered message is longer than 140 symbols");
            }
            list.add(new Message(id, author, message, format.parse(date)));
            System.out.println("Сообщение добавлено");
            logWriter.println(new Date() + " command:add Message added");
        } catch (ParseException e) {
            logWriter.println(new Date() + " command:add Error while parsing date");
        }
    }

    public void viewAll() {
        Collections.sort(list);
        list.showAll();
        logWriter.println(new Date() + " command:viewAll History viewed");
    }

    public void delete() {
        System.out.print("Введите id ");
        String id = in.nextLine();
        if (list.delete(id)) {
            System.out.println("Удаление успешно");
            logWriter.println(new Date() + " command:delete Message with ID " + id + " has been successfully deleted");
        } else {
            System.out.println("Сообщение с указанным ID не найдено");
            logWriter.println(new Date() + " command:delete Message with ID " + id + " was not found");
        }
    }

    public void search() {
        System.out.println("1 - Поиск по автору" +
                "\n2 - Поиск по ключевому слову" +
                "\n3 - Поиск по регулярному выражению");
        int i = in.nextInt();
        in.nextLine();
        int found = 0;
        switch (i) {
            case 1:
                System.out.print("Введите автора ");
                found = list.searchAuthor(in.nextLine());
                break;
            case 2:
                System.out.print("Введите ключевое слово ");
                found = list.searchKeyword(in.nextLine());
                break;
            case 3:
                System.out.print("Введите регулярное выражение ");
                found = list.searchExpression(in.nextLine());
                break;
            default:
                System.out.println("Нет команды");
        }
        if (found == 0) {
            System.out.println("Сообщения не найдены");
        }
        logWriter.println(new Date() + " command:search Messages found: " + found);
    }

    public void viewTime() {
        Collections.sort(list);
        int found;
        try {
            System.out.println("Для ввода используется HH.mm.ss dd.MM.yyyy");
            System.out.println("Введите начальное время ");
            Date start = format.parse(in.nextLine());
            System.out.println("Введите конечное время ");
            Date finish = format.parse(in.nextLine());
            if ((found = list.show(start, finish)) == 0) {
                System.out.println("Сообщения не найдены");
            }
            logWriter.println(new Date() + " Found messages between " + start + " and " + finish + " : " + found);
        } catch (ParseException e) {
            logWriter.println(new Date() + " command:viewTime Error while parsing date");
        }
    }

}
