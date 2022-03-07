package at.htl.api;

import at.htl.control.AnswerRepository;
import at.htl.entity.Answer;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.Map;

@Produces("application/json")
@Consumes("application/json")
@Path("answers/")
public class AnswerRessource {
    @Inject
    AnswerRepository answerRepository;

    /*
    @POST
    @Path("add")
    public Response addAnswer(Answer answer, @Context UriInfo info){
        final Answer savedAnswer = answerRepository.save(answer);
        URI uri = info.getAbsolutePathBuilder().path("/answer/add/" + savedAnswer.getA_id()).build();
        return Response.created(uri).build();
    }*/

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public Response findAllAnswers(){
        final List<Answer> answers = answerRepository.findAll();
        return Response.ok(answers).build();
    }
}

