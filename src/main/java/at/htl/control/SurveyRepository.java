package at.htl.control;

import at.htl.entity.Survey;
import at.htl.entity.Survey;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.jboss.logmanager.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Typed;
import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;


@ApplicationScoped
public class SurveyRepository implements PanacheRepository<Survey> {

    @Transactional
    public Survey save(Survey survey) {

        if (findByQuestion(survey.question) == null){
            survey = getEntityManager().merge(survey);
            return survey;
        }else{
            return null;
        }
    }

    public Survey findByQuestion(String qs){
        try{
            TypedQuery<Survey> query = getEntityManager().createNamedQuery("S.findByQuestion", Survey.class);
            query.setParameter("QUESTION", qs);
            return query.getSingleResult();

        }catch (Exception e){
            return null;
        }
    }

}
