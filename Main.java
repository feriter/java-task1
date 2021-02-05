package com.company;

public class Main {
    public static void main(String[] args) {
        WordCounter wordCounter = new WordCounter();
        wordCounter.readFile("input.txt");
        wordCounter.writeInCSVFile("output.csv");
    }
}
