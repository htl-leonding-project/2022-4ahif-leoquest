package at.htl.leosurvey.boundary;

import at.htl.leosurvey.control.AnswerOptionRepository;
import at.htl.leosurvey.control.QuestionRepository;
import at.htl.leosurvey.control.QuestionnaireRepository;
import at.htl.leosurvey.entity.*;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("questionnaire")
@Produces(MediaType.APPLICATION_JSON)
public class QuestionnaireResource {

    @Inject
    QuestionnaireRepository repository;

    @Inject
    QuestionRepository questionRepository;

    @Inject
    AnswerOptionRepository answerOptionRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllQuestionnaires() {
        return Response.ok(repository.listAll()).build();
    }

    @GET
    @Path("public")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findPublicQuestionnaires(){
        return Response.ok(repository.findPublicQuestionnaires()).build();
    }

    @GET
    @Path("public/{interviewerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findPublicAndOwnedQuestionnaires(@PathParam("interviewerId") String id){
        return Response.ok(repository.findPublicAndOwnedQuestionnaires(id)).build();
    }

    @GET
    @Path("interviewer/{interviewer}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findQuestionnairesByInterviewer(@PathParam("interviewer") String id){
        return Response.ok(repository.findByInterviewer(id)).build();
    }

    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQuestionnaireById(@PathParam("id") Long id) {
        Questionnaire questionnaire = repository.findById(id);
        System.out.println(questionnaire.name);
        if (questionnaire != null) {
            return Response
                    .ok(questionnaire)
                    .build();
        } else {
            return Response.noContent().build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createQuestionnaire(Questionnaire questionnaire, @Context UriInfo info) {
        questionnaire = repository.save(questionnaire);
        UriBuilder uriBuilder = info.getAbsolutePathBuilder();
        uriBuilder.path(questionnaire.id.toString());
        return Response.created(uriBuilder.build()).entity(questionnaire).build();
    }

    @POST
    @Path("/duplicateQuestionnaire/{interviewer}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response duplicateQuestionnaire(Questionnaire questionnaire, @PathParam("interviewer") String interviewer){
        Questionnaire duplicateQuestionnaire = repository.duplicateQuestionnaire(questionnaire, interviewer);
        return Response.ok(duplicateQuestionnaire).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateQuestionnaire(@PathParam("id") long id, Questionnaire questionnaire) {
        Questionnaire old = repository.findById(id);

        if (old != null) {
            old.name = questionnaire.name;
            old.description = questionnaire.description;
            old.isPublic = questionnaire.isPublic;

            repository.save(old);
            return Response
                    .ok()
                    .entity(old)
                    .build();
        } else {
            return Response
                    .status(400)
                    .header("Reason", "Questionnaire with id " + id + " does not exist")
                    .build();
        }
    }

    @DELETE
    @Path("delete/{id}")
    public Response deleteQuestionnaire(@PathParam("id") long id) {
        Questionnaire questionnaire = repository.findById(id);
        repository.remove(questionnaire);
        return Response
                .ok()
                .build();

    }

}
