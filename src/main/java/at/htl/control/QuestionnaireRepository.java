package at.htl.control;


import at.htl.entities.Questionnaire;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class QuestionnaireRepository implements PanacheRepository<Questionnaire> {
}
