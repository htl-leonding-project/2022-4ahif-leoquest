package at.htl.boundary;
import at.htl.control.QuestionnaireRepository;
import at.htl.entities.Questionnaire;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("leoquest")
public class QuestionnaireEndpoint {
    @Inject
    QuestionnaireRepository questionnaireRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/questionnaire")
    public Response findAllQuestionnaires(){
        final List<Questionnaire> questionnaires = questionnaireRepository.findAllQuestionnaires();
        return Response.ok(questionnaires).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/questionnaire/{id}")
    public Response findQuestionnaireById(@PathParam("id") long id) {
        final Questionnaire questionnaire = questionnaireRepository.findById(id);
        return Response.ok(questionnaire).build();
    }
}
