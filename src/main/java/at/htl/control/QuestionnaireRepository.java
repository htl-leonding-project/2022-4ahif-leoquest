package at.htl.control;

import at.htl.entity.Questionnaire;
import at.htl.entity.Teacher;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class QuestionnaireRepository {

    @Inject
    EntityManager em;

    @Transactional
    public void delete(Questionnaire questionnaire) {
        em.remove(questionnaire);
    }

    @Transactional
    public void save(Questionnaire questionnaire){
        em.persist(questionnaire);
    }

    public List<Questionnaire> findAll() {
        return em
                .createNamedQuery("Questionnaire.findAll", Questionnaire.class)
                .getResultList();
    }

    public Questionnaire findById(Long id) {

        Query query = em.createNamedQuery("Questionnaire.findById",
                Questionnaire.class);
        query.setParameter("id", id);

        return (Questionnaire) query.getSingleResult();

    }
}
