package at.htl.leosurvey.boundary;

import at.htl.leosurvey.control.ChosenOptionRepository;
import at.htl.leosurvey.entity.AnswerOption;
import at.htl.leosurvey.entity.ChosenOption;
import at.htl.leosurvey.entity.Interviewer;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("chosenOption")
@Produces(MediaType.APPLICATION_JSON)
public class ChosenOptionResource {

    @Inject
    ChosenOptionRepository repository;

    @GET
    public List<ChosenOption> findAllChosenOptions() {
        return repository.listAll();
    }

    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getChosenOptionsById(@PathParam("id") Long id) {
        ChosenOption chosenOption = repository.findById(id);
        if (chosenOption != null) {
            return Response
                    .ok(chosenOption)
                    .build();
        } else {
            return Response.noContent().build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createChosenOption(ChosenOption chosenOption, @Context UriInfo info) {
        chosenOption = repository.save(chosenOption);
        UriBuilder uriBuilder = info.getAbsolutePathBuilder();
        uriBuilder.path(chosenOption.id.toString());
        return Response.created(uriBuilder.build()).entity(chosenOption).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateChosenOption(@PathParam("id") long id, ChosenOption chosenOption) {
        ChosenOption old = repository.findById(id);

        if (old != null) {
            old.answer = chosenOption.answer;
            old.answerOption = chosenOption.answerOption;
            old.question = chosenOption.question;

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
    public Response deleteChosenOption(@PathParam("id") long id) {
        ChosenOption chosenOption = repository.findById(id);
        repository.remove(chosenOption);
        return Response
                .ok()
                .build();

    }
}
