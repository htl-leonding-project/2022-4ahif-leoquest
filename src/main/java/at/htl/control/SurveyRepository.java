package at.htl.control;


import at.htl.entities.Survey;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class SurveyRepository implements PanacheRepository<Survey> {

    @Transactional
    public Survey save(Survey survey){
        return getEntityManager().merge(survey);
    }

    public List<Survey> findAllSurveys() {
        return listAll();
    }
}
