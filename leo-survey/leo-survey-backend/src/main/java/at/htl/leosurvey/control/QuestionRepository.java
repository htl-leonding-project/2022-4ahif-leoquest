package at.htl.leosurvey.control;

import at.htl.leosurvey.entity.*;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@ApplicationScoped
@Transactional
public class QuestionRepository implements PanacheRepository<Question> {

    @Inject
    EntityManager em;

    @Inject
    AnswerRepository answerRepository;

    @Inject
    AnswerOptionRepository answerOptionRepository;

    @Inject
    ChosenOptionRepository chosenOptionRepository;

    public Question save(Question question) {
        return em.merge(question);
    }

    public void remove(Question question) {
        List<Answer> answers = answerRepository.findByQuestion(question);
        for (Answer a : answers) {
            answerRepository.remove(a);
        }

        List<AnswerOption> answerOptions = answerOptionRepository.findByQuestionId(question.id);
        for (AnswerOption ao : answerOptions) {
            answerOptionRepository.remove(ao);
        }

        List<ChosenOption> chosenOptions = chosenOptionRepository.findByQuestion(question);
        for (ChosenOption co : chosenOptions) {
            chosenOptionRepository.remove(co);
        }

        em.remove(em.contains(question) ? question : em.merge(question));
    }

    public List<Question> findByQuestionnaire(Questionnaire questionnaire) {
        return em.createQuery(
                        "select q from Question q where q.questionnaire.id = :id", Question.class)
                .setParameter("id", questionnaire.id)
                .getResultList();
    }

    public List<Question> getQuestionsByQuestionaireId(Long id) {
        return em.createQuery(
                        "select q from Question q " +
                                "where q.questionnaire.id = :id " +
                                "order by q.sequenceNumber",
                        Question.class)
                .setParameter("id", id)
                .getResultList();

    }

    /*public void saveImage(File imageFile){
       BufferedImage bImage = null;
        try {
            //bImage = ImageIO.read(new File("src/main/java/at/htl/leosurvey/strawberry.jpg"));
            bImage = ImageIO.read(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        System.out.println(bImage);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bImage, "jpg", bos );
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte [] data = bos.toByteArray();
        Question question = new Question("hallo", data, 100, QuestionType.FREETEXT,null);
        save(question);
       // Question question = new Question("hallo", file, 100, QuestionType.FREETEXT,null);
    }

    public File returnImage(byte[] data){
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        BufferedImage bImage2 = null;
        try {
            bImage2 = ImageIO.read(bis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ImageIO.write(bImage2, "jpg", new File("output.jpg") );
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("image created");
        File imageFile = new File("output.png");
        return imageFile;
        //return null;
    }*/
}
