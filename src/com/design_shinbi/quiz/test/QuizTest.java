package com.design_shinbi.quiz.test;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.design_shinbi.quiz.Question;

class QuizTest {

    @Test
    void testLine() {
        String line = "コンパスの和名の正式名称は？,両脚器,規（ぶんまわし）,円規（えんき）,根発子（こんはっし）";

        Question question = new Question();
        question.readLine(line);
        question.display();
    }

    @Test
    void testResource() throws IOException {
        List<Question> questions = Question.getQuestions();
        for (Question question : questions) {
            question.display();
        }
    }
}
