package at.htl.boundary;
import at.htl.control.QuestionRepository;
import at.htl.entities.Question;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("leoquest")
public class QuestionEndpoint {
    @Inject
    QuestionRepository questionRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/questions")
    public Response findAllQuestions(){
        final List<Question> questions = questionRepository.findAllQuestions();
        return Response.ok(questions).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/questions/{id}")
    public Response findQuestionsByQuestionnaire(@PathParam("id") long id){
        final List<Question> questions = questionRepository.findQuestionsByQuestionnaire(id);
        return Response.ok(questions).build();
    }
}
