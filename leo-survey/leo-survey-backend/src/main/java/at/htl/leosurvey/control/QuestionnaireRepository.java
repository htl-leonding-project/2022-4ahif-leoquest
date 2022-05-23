package at.htl.leosurvey.control;

import at.htl.leosurvey.entity.*;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional
public class QuestionnaireRepository implements PanacheRepository<Questionnaire> {

    @Inject
    EntityManager em;

    @Inject
    QuestionRepository questionRepository;

    @Inject
    SurveyRepository surveyRepository;

    @Inject
    AnswerOptionRepository answerOptionRepository;

    @Inject
    ChosenOptionRepository chosenOptionRepository;

    public Questionnaire save(Questionnaire questionnaire) {
        return em.merge(questionnaire);
    }

    public void remove(Questionnaire questionnaire) {
        List<Question> questions = questionRepository.getQuestionsByQuestionaireId(questionnaire.id);
        System.out.println(questions.size());
        for (Question q : questions) {
            questionRepository.remove(q);
        }


        List<Survey> surveys = surveyRepository.findByQuestionnaire(questionnaire);
        System.out.println(surveys.size());
        for (Survey s : surveys) {
            surveyRepository.remove(s);
            System.out.println(s);
        }

        //em.remove(questionnaire);
        em.remove(em.contains(questionnaire) ? questionnaire : em.merge(questionnaire));
    }

    public List<Questionnaire> findByInterviewer(String interviewer) {
        return em.createQuery(
                "select qn from Questionnaire qn where qn.interviewer = :id", Questionnaire.class)
                .setParameter("id", interviewer)
                .getResultList();
    }

    public List<Questionnaire> findPublicQuestionnaires() {
        return em.createQuery(
                "select qn from Questionnaire qn where qn.isPublic = true", Questionnaire.class)
                .getResultList();
    }

    public List<Questionnaire> findPublicAndOwnedQuestionnaires(String interviewer) {
        return em.createQuery(
                "select qn from Questionnaire qn where qn.isPublic = true or qn.interviewer = :id", Questionnaire.class)
                .setParameter("id", interviewer)
                .getResultList();
    }

    public Questionnaire findQuestionnaire(Questionnaire questionnaire) {
        Questionnaire questionnaire1 = em.createQuery(
                "select qn from Questionnaire qn " +
                        "where qn.id =:id " +
                        "and qn.name = :name " +
                        "and qn.description = :desc",
                        Questionnaire.class)
                .setParameter("id", questionnaire.id)
                .setParameter("name", questionnaire.name)
                .setParameter("desc", questionnaire.description)
                .getSingleResult();
        System.out.println(questionnaire1);
        return questionnaire1;
    }

    public Questionnaire duplicateQuestionnaire(Questionnaire questionnaire, String interviewer) {
        System.out.println(questionnaire);
        Questionnaire originalQuestionnaire = findQuestionnaire(questionnaire);
        System.out.println(originalQuestionnaire);
        Questionnaire duplicateQuestionnaire;

        duplicateQuestionnaire = new Questionnaire(
                originalQuestionnaire.name,
                originalQuestionnaire.description,
                false,
                interviewer);

        duplicateQuestionnaire = save(duplicateQuestionnaire);

        Question duplicateQuestion;
        List<Question> questions = questionRepository.findByQuestionnaire(originalQuestionnaire);

        AnswerOption duplicateAnswerOption;
        List<AnswerOption> answerOptions;

        for (Question originalQuestion : questions) {
            duplicateQuestion = new Question(
                    originalQuestion.text,
                    originalQuestion.sequenceNumber,
                    originalQuestion.questiontype,
                    duplicateQuestionnaire);

            duplicateQuestion = questionRepository.save(duplicateQuestion);

            answerOptions = answerOptionRepository.findByQuestionId(originalQuestion.id);

            for (AnswerOption originalAnswerOption : answerOptions) {
                duplicateAnswerOption = new AnswerOption(
                        originalAnswerOption.text,
                        originalAnswerOption.value,
                        originalAnswerOption.sequenceNumber,
                        originalAnswerOption.isCorrectAnswer,
                        duplicateQuestion);

                duplicateAnswerOption = answerOptionRepository.save(duplicateAnswerOption);
            }
        }

        return duplicateQuestionnaire;
    }
}
