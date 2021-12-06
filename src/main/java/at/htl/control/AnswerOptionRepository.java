package at.htl.control;

import at.htl.entity.AnswerOption;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
public class AnswerOptionRepository implements PanacheRepository<AnswerOption> {


    @Transactional
    public AnswerOption save(AnswerOption ao1) {

        AnswerOption as = new AnswerOption();
        as = getEntityManager().merge(ao1);

        return as;
    }
}
