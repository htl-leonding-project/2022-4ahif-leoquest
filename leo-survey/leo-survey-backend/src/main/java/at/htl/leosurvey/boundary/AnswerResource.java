package at.htl.leosurvey.boundary;

import at.htl.leosurvey.control.AnswerRepository;
import at.htl.leosurvey.entity.Answer;
import at.htl.leosurvey.entity.AnswerOption;
import at.htl.leosurvey.entity.Interviewer;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("answer")
@Produces(MediaType.APPLICATION_JSON)
public class AnswerResource {

    @Inject
    AnswerRepository repository;

    @GET
    public List<Answer> findAllAnswers() {
        return repository.listAll();
    }

    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAnswersById(@PathParam("id") Long id) {
        Answer answer = repository.findById(id);
        if (answer != null) {
            return Response
                    .ok(answer)
                    .build();
        } else {
            return Response.noContent().build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAnswer(Answer answer, @Context UriInfo info) {
        answer = repository.save(answer);
        UriBuilder uriBuilder = info.getAbsolutePathBuilder();
        uriBuilder.path(answer.id.toString());
        return Response.created(uriBuilder.build()).entity(answer).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAnswer(@PathParam("id") long id, Answer answer) {
        Answer old = repository.findById(id);

        if (old != null) {
            old.question = answer.question;
            old.answerText = answer.answerText;
            old.transaction = answer.transaction;

            repository.save(old);
            return Response
                    .ok()
                    .entity(old)
                    .build();
        } else {
            return Response
                    .status(400)
                    .header("Reason", "Answe with id " + id + " does not exist")
                    .build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteAnswer(@PathParam("id") long id) {
        Answer answer = repository.findById(id);
        repository.remove(answer);
        return Response
                .ok()
                .build();

    }
}
