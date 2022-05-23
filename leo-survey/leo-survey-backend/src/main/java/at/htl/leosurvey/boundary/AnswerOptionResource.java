package at.htl.leosurvey.boundary;

import at.htl.leosurvey.control.AnswerOptionRepository;
import at.htl.leosurvey.entity.AnswerOption;
import at.htl.leosurvey.entity.Question;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("answerOption")
@Produces(MediaType.APPLICATION_JSON)
public class AnswerOptionResource {

    @Inject
    AnswerOptionRepository repository;

    @GET
    public List<AnswerOption> findAllAnswerOptions() {
        return repository.listAll();
    }

    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAnswerOptionById(@PathParam("id") Long id) {
        AnswerOption answerOption = repository.findById(id);
        if (answerOption != null) {
            return Response
                    .ok(answerOption)
                    .build();
        } else {
            return Response.noContent().build();
        }
    }

    // GET AnswerOption by QuestionID


    @GET
    @Path("questionnaire/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAnswerOptionWithQuestionnaireId(@PathParam("id") Long id) {
        List<AnswerOption> answerOptions = repository.findByQuestionnaireId(id);
        if (answerOptions != null) {
            return Response
                    .ok(answerOptions)
                    .build();
        } else {
            return Response.noContent().build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAnswerOption(AnswerOption answerOption, @Context UriInfo info) {
        answerOption = repository.save(answerOption);
        UriBuilder uriBuilder = info.getAbsolutePathBuilder();
        uriBuilder.path(answerOption.id.toString());
        return Response.created(uriBuilder.build()).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAnswerOption(@PathParam("id") long id, AnswerOption answerOption) {
        AnswerOption old = repository.findById(id);

        if (old != null) {
            old.text = answerOption.text;
            old.sequenceNumber = answerOption.sequenceNumber;
            old.value = answerOption.value;
            old.question = answerOption.question;

            repository.save(old);
            return Response
                    .ok()
                    .entity(old)
                    .build();
        } else {
            return Response
                    .status(400)
                    .header("Reason", "AnswerOption with id " + id + " does not exist")
                    .build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteAnswerOption(@PathParam("id") long id) {
        AnswerOption answerOption = repository.findById(id);
        repository.remove(answerOption);
        return Response
                .ok()
                .build();

    }
}
