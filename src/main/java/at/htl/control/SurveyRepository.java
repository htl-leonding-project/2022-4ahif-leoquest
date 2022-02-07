package at.htl.control;

import at.htl.entity.Survey;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class SurveyRepository {

    @Inject
    EntityManager em;

    @Inject
    QuestionnaireRepository qR;


    @Transactional
    public void delete(Survey survey) {
        em.remove(survey);
    }

    @Transactional

    public void save(Survey survey){
        //survey = em.merge(survey);
        em.persist(survey);
    }


    public List<Survey> findAll() {
        return em
                .createNamedQuery("Survey.findAll", Survey.class)
                .getResultList();
    }
}
