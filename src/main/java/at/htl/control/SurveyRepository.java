package at.htl.control;


import at.htl.entities.Survey;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class SurveyRepository implements PanacheRepository<Survey> {
}
