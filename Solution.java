package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException {
        String firstFileName;
        String secondFileName;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            firstFileName = br.readLine();
            secondFileName = br.readLine();
        }

        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        try (BufferedReader firstFileReader = new BufferedReader(new FileReader(firstFileName));
             BufferedReader secondFileReader = new BufferedReader(new FileReader(secondFileName))) {

            String inputLine1;
            String inputLine2;

            while ((inputLine1 = firstFileReader.readLine()) != null)
                list1.add(inputLine1);

            while ((inputLine2 = secondFileReader.readLine()) != null)
                list2.add(inputLine2);
        }

        int count1 = 0;
        int count2 = 0;

        while (count1 < list1.size() || count2 < list2.size()) {
            if (count1 == list1.size()) {
                lines.add(new LineItem(Type.ADDED, list2.get(count2)));
                count2++;
            } else if (count2 == list2.size()) {
                lines.add(new LineItem(Type.REMOVED, list1.get(count1)));
                count1++;
            } else if (list1.get(count1).equals(list2.get(count2))) {
                lines.add(new LineItem(Type.SAME, list1.get(count1)));
                count1++;
                count2++;
            } else if (list1.get(count1).equals(list2.get(count2 + 1))) {
                lines.add(new LineItem(Type.ADDED, list2.get(count2)));
                count2++;
            } else {
                lines.add(new LineItem(Type.REMOVED, list1.get(count1)));
                count1++;
            }
        }
    }

    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }

        @Override
        public String toString() {
            return type.name() + " " + line;
        }
    }
}
