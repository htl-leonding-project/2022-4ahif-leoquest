package at.htl.control;

import at.htl.entity.Questionnaire;
import at.htl.entity.Teacher;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

public class QuestionnaireRepository {
    @Inject
    EntityManager em;

    @Transactional
    public void delete(Questionnaire questionnaire) {
        em.remove(questionnaire);
    }

    @Transactional
    public void save(Questionnaire questionnaire){
        em.merge(questionnaire);
    }

    public List<Questionnaire> findAll() {
        return em
                .createNamedQuery("Questionnaire.findAll", Questionnaire.class)
                .getResultList();
    }
}
