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
public class AnswerOptionRepository implements PanacheRepository<AnswerOption> {
    @Inject
    EntityManager em;

    @Inject
    ChosenOptionRepository chosenOptionRepository;

    public AnswerOption save(AnswerOption answerOption) {
        return em.merge(answerOption);
    }

    public void remove(AnswerOption answerOption){
        List<ChosenOption> chosenOptions = chosenOptionRepository.findByAnswerOption(answerOption);
        for (ChosenOption c : chosenOptions) {
            chosenOptionRepository.remove(c);
        }

        em.remove(em.contains(answerOption) ? answerOption : em.merge(answerOption));
    }

    public List<AnswerOption> findByQuestionnaireId(Long id){
        return em.createQuery(
                "select ao from AnswerOption ao where ao.question.questionnaire.id = :id", AnswerOption.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<AnswerOption> findByQuestionId(Long id) {
        return em.createQuery(
                "select ao from AnswerOption ao where ao.question.id = :id", AnswerOption.class)
                .setParameter("id", id)
                .getResultList();
    }

}
