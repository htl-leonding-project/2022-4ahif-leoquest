package at.htl.leosurvey.boundary;

import at.htl.leosurvey.control.InterviewerRepository;
import at.htl.leosurvey.entity.Interviewer;
import at.htl.leosurvey.entity.Questionnaire;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

/*@Path("interviewer")
@Produces(MediaType.APPLICATION_JSON)
public class InterviewerResource {

    @Inject
    InterviewerRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllInterviewers() {
        return Response.ok(repository.findAll().list()).build();
    }

    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInterviewersById(@PathParam("id") Long id) {
        Interviewer interviewer = repository.findById(id);
        if (interviewer != null) {
            return Response
                    .ok(interviewer)
                    .build();
        } else {
            return Response.noContent().build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createInterviewer(Interviewer interviewer, @Context UriInfo info) {
        interviewer = repository.save(interviewer);
        UriBuilder uriBuilder = info.getAbsolutePathBuilder();
        uriBuilder.path(interviewer.id.toString());
        return Response.created(uriBuilder.build()).entity(interviewer).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateInterviewer(@PathParam("id")long id, Interviewer interviewer) {
        Interviewer old = repository.findById(id);

        if (old != null){
            old.name = interviewer.name;
            repository.save(old);
            return Response
                    .ok()
                    .entity(old)
                    .build();
        } else {
            return Response
                    .status(400)
                    .header("Reason", "Interviewer with id " + id + " does not exist")
                    .build();
        }
    }

    @DELETE
    @Transactional
    @Path("delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteInterviewer(@PathParam("id")long id){
        Interviewer interviewer = repository.findById(id);
        System.out.println(interviewer);
        repository.remove(interviewer);
        return Response
                .ok()
                .build();
    }

    /*

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Payment payment, @Context UriInfo info) {
        System.out.println(payment);
        payment = paymentRepository.save(payment);

        URI uri = info.getAbsolutePathBuilder().path("/" + payment.getId()).build();
        return Response.created(uri).build();
    }

}*/
