package at.htl.control;


import at.htl.entities.Questionnaire;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class QuestionnaireRepository
        implements PanacheRepository<Questionnaire> {

    @Transactional
    public Questionnaire save(Questionnaire questionnaire){
        return getEntityManager().merge(questionnaire);
    }

    @Transactional
    public void delete(long id){
        delete(findById(id));
    }

    public Questionnaire findById(long id){
        return find("id",id).singleResult();
    }

    public List<Questionnaire> findAllQuestionnaires(){
        return listAll();
    }
}
