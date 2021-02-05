package com.company;

import java.io.*;
import java.lang.StringBuilder;
import java.util.*;

public class WordCounter {
    private final HashMap<String, Integer> words;
    private Integer wordsCount = 0;


    public WordCounter() {
        words = new HashMap<>();
    }

    public void readFile(String fileName) {
        Reader reader = null;
        try {
            int c;
            StringBuilder buffer = new StringBuilder();
            reader = new InputStreamReader(new FileInputStream(fileName));
            int flag = 0;
            while ((c = reader.read()) != -1 || flag == 0) {
                if (c != -1 && Character.isLetterOrDigit((char)c)) {
                    buffer.append((char)c);
                } else {
                    if (words.containsKey(buffer.toString())) {
                        Integer lastCount = words.get(buffer.toString());
                        words.put(buffer.toString(), lastCount + 1);
                    } else {
                        words.put(buffer.toString(), 1);
                    }
                    wordsCount++;
                    buffer.delete(0, buffer.length());
                }
                if (c == -1) {
                    flag = 1;
                }
            }
        }
        catch (IOException e) {
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                }
                catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    public void writeInCSVFile(String fileName) {
        Writer writer = null;
        try {
            List<Map.Entry<String, Integer>> list = new ArrayList<>(words.entrySet());
            list.sort(Map.Entry.comparingByValue());
            Collections.reverse(list);
            writer = new OutputStreamWriter(new FileOutputStream(fileName));
            writer.write("Word,Frequency,FrequencyPerCent\n");
            for (Map.Entry<String, Integer> item : list) {
                String outputString = item.getKey() +
                        "," +
                        item.getValue() +
                        "," +
                        (double)item.getValue() / wordsCount * 100 +
                        "%\n";
                writer.write(outputString);
            }
        }
        catch(IOException e) {
            System.err.println("Error while writing file: " + e.getLocalizedMessage());
        }
        finally {
            if (writer != null) {
                try {
                    writer.close();
                }
                catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }
}
