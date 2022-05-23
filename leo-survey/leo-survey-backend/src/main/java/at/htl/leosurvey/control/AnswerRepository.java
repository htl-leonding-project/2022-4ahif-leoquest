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
public class AnswerRepository implements PanacheRepository<Answer> {

    @Inject
    EntityManager em;

    @Inject
    ChosenOptionRepository chosenOptionRepository;

    public Answer save(Answer answer) {
        return em.merge(answer);
    }

    public void remove(Answer answer){
        List<ChosenOption> chosenOptions = chosenOptionRepository.findByAnswer(answer);
        for (ChosenOption c : chosenOptions) {
            chosenOptionRepository.remove(c);
        }

        em.remove(em.contains(answer) ? answer : em.merge(answer));
    }

    public List<Answer> findByQuestion(Question question){
        return em
                .createQuery("select a from Answer a where a.question.id = :id", Answer.class)
                .setParameter("id", question.id)
                .getResultList();
    }

    public List<Answer> findByTransaction(Transaction transaction){
        return em
                .createQuery("select a from Answer a where a.transaction.id = :id", Answer.class)
                .setParameter("id", transaction.id)
                .getResultList();
    }
}
