package at.htl.control;

import at.htl.entity.Question;
import at.htl.entity.Teacher;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class QuestionRepository {

    @Inject
    EntityManager em;

    @Transactional
    public void delete(Question question) {
        em.remove(question);
    }

    @Transactional
    public void save(Question question){
        em.merge(question);
    }

    public List<Question> findAll() {
        return em
                .createNamedQuery("Question.findAll", Question.class)
                .getResultList();
    }

    public Question findById(Long id) {

        Query query = em.createNamedQuery("Question.findById",
                Question.class);
        query.setParameter("id", id);

        return (Question) query.getSingleResult();

    }
}
