package com.design_shinbi.quiz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Question {
    private String question;
    private String correctItem;
    private List<String> wrongItems;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectItem() {
        return correctItem;
    }

    public void setCorrectItem(String correctItem) {
        this.correctItem = correctItem;
    }

    public List<String> getWrongItems() {
        return wrongItems;
    }

    public void setWrongItems(List<String> wrongItems) {
        this.wrongItems = wrongItems;
    }

    public List<String> getShuffledItems() {
        List<String> items = new ArrayList<String>();
        items.add(this.correctItem);
        items.addAll(this.wrongItems);

        Collections.shuffle(items);
        return items;
    }

    public List<String> display() {
        List<String> items = this.getShuffledItems();
        System.out.println("問題： " + this.getQuestion());
        for (int i = 0; i < items.size(); i++) {
            System.out.println(
                    String.format(" %d: %s", i, items.get(i)));
        }
        return items;
    }

    public void readLine(String line) {
        StringTokenizer tokenizer = new StringTokenizer(line, ",");

        this.wrongItems = new ArrayList<String>();

        int counter = 0;
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (counter == 0) {
                this.question = token;
            } else if (counter == 1) {
                this.correctItem = token;
            } else {
                this.wrongItems.add(token);
            }
            counter++;
        }
    }

    public static List<Question> getQuestions() throws IOException {
        List<Question> questions = new ArrayList<Question>();

        InputStream stream = Question.class.getResourceAsStream("questions.csv");
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        String line;
        while ((line = reader.readLine()) != null) {
            Question question = new Question();
            question.readLine(line);
            questions.add(question);
        }
        reader.close();

        return questions;
    }
}
