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
public class ChosenOptionRepository implements PanacheRepository<ChosenOption> {
    @Inject
    EntityManager em;

    public ChosenOption save(ChosenOption chosenOption) {
        return em.merge(chosenOption);
    }

    public void remove(ChosenOption chosenOption){
        em.remove(em.contains(chosenOption) ? chosenOption : em.merge(chosenOption));
    }

    public List<ChosenOption> findByQuestion(Question question){
        return em.createQuery(
                "select co from ChosenOption co where co.question.id = :id", ChosenOption.class)
                .setParameter("id", question.id)
                .getResultList();
    }

    public List<ChosenOption> findByAnswer(Answer answer){
        return em.createQuery(
                "select co from ChosenOption co where co.answer.id = :id", ChosenOption.class)
                .setParameter("id", answer.id)
                .getResultList();
    }

    public List<ChosenOption> findByAnswerOption(AnswerOption answerOption){
        return em.createQuery(
                "select co from ChosenOption co where co.answerOption.id = :id", ChosenOption.class)
                .setParameter("id", answerOption.id)
                .getResultList();
    }
}
