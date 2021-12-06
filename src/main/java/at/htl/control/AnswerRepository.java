package at.htl.control;

import at.htl.entity.Answer;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
public class AnswerRepository implements PanacheRepository<Answer> {


    @Transactional
    public Answer save(Answer a) {
        Answer as = new Answer();
        as = getEntityManager().merge(a);

        return as;
    }

    public Answer findByQuestion(Answer a){

        return null;
    }

}
