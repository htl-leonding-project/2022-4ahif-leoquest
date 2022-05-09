package at.htl.control;

import at.htl.entities.Answer;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class AnswerRepository implements PanacheRepository<Answer> {

}
