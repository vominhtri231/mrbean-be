package school.finalproject.mrbbe.service.homework;

import com.fasterxml.jackson.databind.ObjectMapper;
import school.finalproject.mrbbe.dao.homework.Homework;
import school.finalproject.mrbbe.dao.homework.HomeworkStudent;
import school.finalproject.mrbbe.dao.homework.Question;
import school.finalproject.mrbbe.dao.lesson.Lesson;
import school.finalproject.mrbbe.dao.mistake.Mistake;
import school.finalproject.mrbbe.dao.mistake.MistakeRule;
import school.finalproject.mrbbe.dao.mistake.MistakeType;
import school.finalproject.mrbbe.dao.user.Student;
import school.finalproject.mrbbe.support.Constant.MistakeStandards;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class HomeworkResult {
    private int accuracyRate;
    private int completeRate;
    private Lesson lesson;
    private Student student;

    public HomeworkResult(Homework homework, HomeworkStudent homeworkStudent) {
        lesson = homework.getLesson();
        student = homeworkStudent.getStudent();
        List<Question> questions = homework.getQuestions();
        List<Integer> answers = homeworkStudent.getChoices();
        accuracyRate = calculateAccuracy(answers, questions);
        completeRate = calculateCompletion(answers, questions);
    }

    public HomeworkResult(Lesson lesson, Student student) {
        this.lesson = lesson;
        this.student = student;
        accuracyRate = 0;
        completeRate = 0;
    }

    public List<Mistake> getMistakes(List<MistakeRule> mistakeRules) {
        return mistakeRules.stream()
                .map(this::checkRule)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private Optional<Mistake> checkRule(MistakeRule mistakeRule) {
        if (mistakeRule.getMistakeStandard().equals(MistakeStandards.ACCURACY_RATE)) {
            if (mistakeRule.getThreshold() > accuracyRate) {
                return getMistake(mistakeRule.getMistakeType());
            }
            return Optional.empty();
        }
        if (mistakeRule.getMistakeStandard().equals(MistakeStandards.COMPLETE_RATE)) {
            if (mistakeRule.getThreshold() > completeRate) {
                return getMistake(mistakeRule.getMistakeType());
            }
            return Optional.empty();
        }
        return Optional.empty();
    }

    private Optional<Mistake> getMistake(MistakeType mistakeType) {
        return Optional.of(Mistake.builder()
                .lesson(lesson)
                .mistakeType(mistakeType)
                .student(student)
                .build());
    }

    private int calculateCompletion(List<Integer> answers, List<Question> questions) {
        long doneAnswer = answers.stream().filter(Objects::nonNull).count();
        return Math.round(doneAnswer * 100f / questions.size());
    }

    private int calculateAccuracy(List<Integer> answers, List<Question> questions) {
        if (questions.size() == 0) return 0;
        int rightAnswer = 0;
        for (int i = 0; i < answers.size(); i++) {
            Question question = convertToQuestion(questions.get(i));
            Integer answer = answers.get(i);
            if (question.getCorrectAnswer().equals(answer)) {
                rightAnswer++;
            }
        }
        return Math.round(rightAnswer * 100f / questions.size());
    }

    private Question convertToQuestion(Object json) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(json, Question.class);
    }
}
