package at.htl.leosurvey.control;

import at.htl.leosurvey.entity.Interviewer;
import at.htl.leosurvey.entity.Question;
import at.htl.leosurvey.entity.Questionnaire;
import at.htl.leosurvey.entity.Survey;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional
public class InterviewerRepository implements PanacheRepository<Interviewer> {
    @Inject
    EntityManager em;
    
    @Inject
    QuestionnaireRepository questionnaireRepository;

    @Inject
    SurveyRepository surveyRepository;

    public Interviewer save(Interviewer interviewer) {
        return em.merge(interviewer);
    }

    public void remove(String interviewer){
        List<Questionnaire> questionnaires = questionnaireRepository.findByInterviewer(interviewer);
        for (Questionnaire q : questionnaires) {
            questionnaireRepository.remove(q);
        }

        List<Survey> surveys = surveyRepository.findByInterviewer(interviewer);
        for (Survey s : surveys) {
            surveyRepository.remove(s);
        }

        em.remove(interviewer);
    }

    public Interviewer findByInterviewer(Interviewer interviewer){
        return em.createQuery("select i from Interviewer i where i = :interviewer", Interviewer.class)
                .setParameter("interviewer", interviewer)
                .getSingleResult();
    }

}
