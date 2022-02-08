package at.htl.control;

import at.htl.entity.Answer;
import at.htl.entity.AnswerOption;
import at.htl.entity.Teacher;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class AnswerOptionRepository {

    @Inject
    EntityManager em;

    @Transactional
    public void delete(AnswerOption answerOption) {
        em.remove(answerOption);
    }

    @Transactional
    public AnswerOption save(AnswerOption answerOption){
        return em.merge(answerOption);
    }

    public List<AnswerOption> findAll() {
        return em
                .createNamedQuery("AnswerOption.findAll", AnswerOption.class)
                .getResultList();
    }


}
