package at.htl.control;

import at.htl.entity.Answer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class AnswerRepository {

    @Inject
    EntityManager em;

    @Transactional
    public void delete(Answer answer) {
        em.remove(answer);
    }

    @Transactional
    public Answer save(Answer answer){
        return em.merge(answer);
    }

    public List<Answer> findAll() {
        return em
                .createNamedQuery("Answer.findAll", Answer.class)
                .getResultList();
    }

    public Answer findById(Long id) {

        Query query = em.createNamedQuery("Answer.findById",
                Answer.class);
        query.setParameter("id", id);

        return (Answer) query.getSingleResult();

    }

}
