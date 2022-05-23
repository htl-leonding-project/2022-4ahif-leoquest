package at.htl.leosurvey.control;

import at.htl.leosurvey.entity.*;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Transactional
public class SurveyRepository implements PanacheRepository<Survey> {
    @Inject
    EntityManager em;

    @Inject
    TransactionRepository transactionRepository;

    @Inject
    QuestionnaireRepository questionnaireRepository;

    @Inject
    QuestionRepository questionRepository;

    @Inject
    AnswerOptionRepository answerOptionRepository;

    @Inject
    ChosenOptionRepository chosenOptionRepository;

    @Inject
    InterviewerRepository interviewerRepository;

    @Inject
    AnswerRepository answerRepository;

    public Survey save(Survey survey){

        return em.merge(survey);
    }

    public void remove(Survey survey){
        List<Transaction> transactions = transactionRepository.findBySurvey(survey);
        for (Transaction t : transactions) {
            transactionRepository.remove(t);
        }

        em.remove(em.contains(survey) ? survey : em.merge(survey));
    }

    public List<Survey> findByQuestionnaire(Questionnaire questionnaire){
        return em.createQuery(
                "select s from Survey s where s.questionnaire.id = :id", Survey.class)
                .setParameter("id", questionnaire.id)
                .getResultList();
    }

    public List<Survey> findByInterviewer(String interviewer){
        return em.createQuery(
                "select s from Survey s where s.interviewer = :id", Survey.class)
                .setParameter("id", interviewer)
                .getResultList();
    }

    public Questionnaire getQuestionnaireWithSurveyId(Long id){

        System.out.println(id);
        System.out.println(findAll());

        return em.createQuery(
                "select s.questionnaire from Survey s where s.id = :id", Questionnaire.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public File evaluation(Long surveyId){
        Survey survey;
        Questionnaire questionnaire;
        List<Question> questions;
        List<Transaction> transactions;
        List<AnswerOption> answerOptions;
        List<ChosenOption> chosenOptions;
        List<Answer> answers;
        int usedTransactions = 0;

        survey = findById(surveyId);
        questionnaire = questionnaireRepository.findById(survey.questionnaire.id);
        questions = questionRepository.getQuestionsByQuestionaireId(questionnaire.id);
        transactions = transactionRepository.findBySurvey(survey);


        for (Transaction transaction : transactions) {
            if (transaction.isUsed){
                usedTransactions ++;
            }
        }

        File file = new File("evaluation.csv");


        try (PrintWriter writer = new PrintWriter("evaluation.csv")) {

            StringBuilder sb = new StringBuilder();
            sb.append(questionnaire.name);
            sb.append("\n");
            sb.append(questionnaire.description);
            sb.append("\n");
            sb.append("\n");
            sb.append(usedTransactions);
            sb.append(" Personen haben den Fragebogen ausgefüllt");
            sb.append("\n");
            sb.append("\n");


            for (Question question : questions) {
                sb.append("* ");
                sb.append(question.text);
                sb.append("\n");

                answers = answerRepository.findByQuestion(question);

                if (question.questiontype.equals(QuestionType.FREETEXT)){
                    for (Answer answer : answers) {
                        sb.append("    - ");
                        sb.append(answer.answerText);
                        sb.append("\n");
                    }
                }else{
                    answerOptions = answerOptionRepository.findByQuestionId(question.id);

                    for (AnswerOption answerOption : answerOptions) {
                        chosenOptions = chosenOptionRepository.findByAnswerOption(answerOption);

                        sb.append("    - ");
                        sb.append(answerOption.text);
                        sb.append(": ");
                        sb.append(chosenOptions.size());
                        sb.append("\n");
                    }
                }

            }
            writer.write(sb.toString());

            System.out.println("done!");

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return file;

    }

    public File evaluation2(Long surveyId){
        File file = new File("evaluation2.csv");

        Survey survey = findById(surveyId);
        Questionnaire questionnaire = questionnaireRepository.findById(survey.questionnaire.id);
        List<Transaction> transactions = transactionRepository.findBySurvey(survey);
        int usedTransactions = 0;
        int count = 1;
        List<Answer> freetextAnswers = new ArrayList<>();
        List<Answer> answers;
        List<ChosenOption> chosenOptions;
        AnswerOption answerOption;

        for (Transaction transaction : transactions) {
            if (transaction.isUsed){
                usedTransactions ++;
            }
        }

        try (PrintWriter writer = new PrintWriter("evaluation2.csv")) {
            StringBuilder sb = new StringBuilder();
            sb.append(questionnaire.name);
            sb.append("\n");
            sb.append(questionnaire.description);
            sb.append("\n");
            sb.append("\n");

            if(usedTransactions == 1){
                sb.append(usedTransactions);
                sb.append(" Person hat den Fragebogen ausgefüllt");
            }else {
                sb.append(usedTransactions);
                sb.append(" Personen haben den Fragebogen ausgefüllt");
            }
            sb.append("\n");

            if(usedTransactions > 1){
                for (Transaction t : transactions) {
                    if(t.isUsed = true){
                        sb.append("\n");
                        sb.append("Person ");
                        sb.append(count++);
                        sb.append(";");

                        answers = answerRepository.findByTransaction(t);

                        for (Answer a : answers) {
                            chosenOptions = chosenOptionRepository.findByAnswer(a);

                            for (ChosenOption co : chosenOptions) {
                                answerOption = co.answerOption;

                                if (co.question.questiontype == QuestionType.FREETEXT){
                                    sb.append("\n");
                                    freetextAnswers.add(co.answer);
                                }else {
                                    sb.append(answerOption.value);
                                }

                            }

                        }
                    }


                }
                if (freetextAnswers.size() != 0){
                    for (Answer answer : freetextAnswers) {
                        sb.append("\n");
                        sb.append("  -");
                        sb.append(answer.answerText);
                    }
                }
            }

            writer.write(sb.toString());

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        /*Survey survey;
        Questionnaire questionnaire;
        List<Question> questions;
        List<Transaction> transactions;
        List<AnswerOption> answerOptions;
        List<ChosenOption> chosenOptions;
        List<Answer> answers;
        int usedTransactions = 0;
        int count = 0;
        List<Answer> freetextAnswers = new ArrayList<>();
        AnswerOption answerOpt;

        survey = findById(surveyId);
        questionnaire = questionnaireRepository.findById(survey.questionnaire.id);
        questions = questionRepository.getQuestionsByQuestionaireId(questionnaire.id);
        transactions = transactionRepository.findBySurvey(survey);


       try (PrintWriter writer = new PrintWriter("evaluation2.csv")) {


            for (Transaction transaction : transactions) {
                answers = answerRepository.findByTransaction(transaction);
                count ++;
                sb.append("Person ");
                sb.append(count);
                sb.append(";");

                for (Answer answer : answers) {
                    chosenOptions = chosenOptionRepository.findByAnswer(answer);

                    for (ChosenOption chosenOption : chosenOptions) {

                        answerOpt = chosenOption.answerOption;

                        if (chosenOption.question.questiontype == QuestionType.FREETEXT){
                            sb.append("\n");
                            freetextAnswers.add(chosenOption.answer);
                        }else {
                            sb.append(" ");
                            sb.append(answerOpt.value);
                            sb.append(";");
                        }
                    }
                }
            }

            if (freetextAnswers.size() != 0){
                for (Answer answer : freetextAnswers) {
                    sb.append("\n");
                    sb.append("  -");
                    sb.append(answer.answerText);
                }
            }

            writer.write(sb.toString());

            System.out.println("done!");

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }*/
        return file;

    }
}
