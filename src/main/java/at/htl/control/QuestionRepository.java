package at.htl.control;

import at.htl.entities.Question;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class QuestionRepository implements PanacheRepository<Question> {

    @Transactional
    public Question save(Question question){
        return getEntityManager().merge(question);
    }

    @Transactional
    public void delete(long id){
        delete(find("id",id).singleResult());
    }

    public List<Question> findAllQuestions(){
        Query q = getEntityManager().createQuery("select q from Question q order by q.id");
        List<Question> questions = q.getResultList();
        return questions;
    }

    public List<Question> findQuestionsByQuestionnaire(long id){
        Query q = getEntityManager().createQuery("select q from " +
                "Question q where q.questionnaire.id = :id order by q.id");
        q.setParameter("id", id);
        List<Question> questions = q.getResultList();
        return questions;
    }
}
