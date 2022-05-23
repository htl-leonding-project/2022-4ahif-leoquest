package at.htl.leosurvey.boundary;

import at.htl.leosurvey.control.AnswerRepository;
import at.htl.leosurvey.control.QuestionRepository;
import at.htl.leosurvey.entity.Answer;
import at.htl.leosurvey.entity.Question;
import at.htl.leosurvey.entity.QuestionType;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Path("evaluation")
@Produces(MediaType.APPLICATION_JSON)
public class EvaluationResource {

    @Inject
    QuestionRepository questionRepository;

    @Inject
    AnswerRepository answerRepository;

    @GET
    @Path("selected/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNumberOfSelectedAnswer(@PathParam("id") Long id) {
        HashMap<String, Integer> validation = new HashMap<String, Integer>();
        Question question = questionRepository.findById(id);
        List<Answer> answers = answerRepository.findByQuestion(question);

        switch (question.questiontype) {
            case SINGLECHOICE:
                for (Answer answer : answers) {

                }
                return Response.ok("SINGLECHOICE").build();
            case MULTIPLECHOICE:
                for (Answer answer : answers) {

                }
                return Response.ok("MULTIPLECHOICE").build();
            case FREETEXT:
                for (Answer answer : answers) {

                }
                return Response.ok("FREETEXT").build();
            case YESORNO:
                // Auswertung für yesorno
                // if -> yes dann count, if no andererCount
                int answersYES = 0;
                int answersNO = 0;

         /*       for (Answer answer : answers) {
                    if (answeroptiontext){
                        //hinzufügen zu Liste 1
                        answersYES++;
                    }else{
                        //hinzufügen zur Liste 2
                        answersNO++;
                    }
                }*/
                validation.put("YES", answersYES);
                validation.put("NO", answersNO);

                return Response.ok("YESORNO").build();
            default:
                System.out.println("switch failed");
                break;

        }
        // switch welcher Questiontype
        // je nach type
        // freetext -> message
        // Auswertung für Multiple choice
        // Schleife in Schleife, alle AnswerOption holen und überprüfen4
        // Auswertung für SingleChoice



        for (Answer answer : answers) {

        }
        return Response.ok(answers.size()).build();
    }
}
