package at.htl.api;

import at.htl.control.QuestionRepository;
import at.htl.entity.Question;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/questions")
public class QuestionRessource {
    @Inject
    QuestionRepository questionRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public Response findAllQuestions(){
        final List<Question> questions = questionRepository.findAll();
        return Response.ok(questions).build();
    }

    /*
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response findQuestionsByQuestionnaire(@PathParam("id") long id){
        final List<Question> questions = questionRepository.findQuestionsByQuestionnaire(id);
        return Response.ok(questions).build();
    }
     */
}