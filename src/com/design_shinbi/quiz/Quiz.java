package com.design_shinbi.quiz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

public class Quiz {
    private static double PASS_PERCENTAGE = 0.8;

    public static void main(String[] args) throws IOException {
        boolean tf = true;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (tf) {

            start(reader);
            System.out.println("もう一度やりますか？");
            System.out.println("[Y] はい [N] いいえ");
            String input = reader.readLine();
            if (input.equalsIgnoreCase("Y")) {
                tf = true;
                System.out.println("さあやろう!");

            } else {
                System.out.println("また遊ぼうね!");
                reader.close();
                break;
            }

        }
    }

    public static void start(BufferedReader reader) throws IOException {
        List<Question> questions = Question.getQuestions();
        Collections.shuffle(questions);

        int correct = 0;
        for (Question question : questions) {
            List<String> itmes = question.display();

            String input = getInput(reader, itmes);
            if (input.equals(question.getCorrectItem())) {
                System.out.println("○ 正解です。");
                correct += 1;
            } else {
                System.out.println("× 間違いです。");
            }
        }
        System.out.println(
                String.format("%d 問中 %d 問正解です。", questions.size(), correct));
        double percentage = (double) correct / (double) questions.size();
        if (percentage >= PASS_PERCENTAGE) {
            System.out.println("合格です。");
        } else {
            System.out.println("不合格です。");
        }

    }

    public static String getInput(BufferedReader reader, List<String> items) {
        int index = -1;
        while (index < 0 || index >= items.size()) {
            System.out.println(
                    String.format("0 ～%d までの数字を入力してください。", items.size() - 1));
            try {
                String line = reader.readLine();
                index = Integer.parseInt(line);
            } catch (Exception e) {
            }
        }

        String input = items.get(index);
        return input;

    }
}