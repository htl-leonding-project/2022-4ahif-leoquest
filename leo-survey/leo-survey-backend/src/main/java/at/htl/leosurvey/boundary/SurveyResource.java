package at.htl.leosurvey.boundary;

import at.htl.leosurvey.control.InterviewerRepository;
import at.htl.leosurvey.control.SurveyRepository;
import at.htl.leosurvey.entity.Answer;
import at.htl.leosurvey.entity.Questionnaire;
import at.htl.leosurvey.entity.Survey;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("survey")
@Produces(MediaType.APPLICATION_JSON)
public class SurveyResource {

    @Inject
    SurveyRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllSurveys() {
        return Response.ok(repository.findAll()).build();
    }

    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSurveyById(@PathParam("id") Long id) {
        Survey survey = repository.findById(id);
        if (survey != null) {
            return Response
                    .ok(survey)
                    .build();
        } else {
            return Response.noContent().build();
        }
    }

    @GET
    @Path("{interviewerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findSurveysByInterviewer(@PathParam("interviewerId") String id){
        return Response.ok(repository.findByInterviewer(id)).build();
    }

    @GET
    @Path("questionnaire/{surveyId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQuestionnaireWithSurveyId(@PathParam("surveyId") Long id){
        Questionnaire questionnaire = repository.getQuestionnaireWithSurveyId(id);
        return Response.ok(questionnaire).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSurvey(Survey survey, @Context UriInfo info) {
        survey = repository.save(survey);
        UriBuilder uriBuilder = info.getAbsolutePathBuilder();
        uriBuilder.path(survey.id.toString());
        return Response.created(uriBuilder.build()).entity(survey).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSurvey(@PathParam("id") long id, Survey survey) {
        Survey old = repository.findById(id);

        if (old != null) {
            old.date = survey.date;
            old.questionnaire = survey.questionnaire;
            old.interviewer = survey.interviewer;
            old.title = survey.title;
            old.description = survey.description;

            repository.save(old);
            return Response
                    .ok()
                    .entity(old)
                    .build();
        } else {
            return Response
                    .status(400)
                    .header("Reason", "Survey with id " + id + " does not exist")
                    .build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteSurvey(@PathParam("id") long id) {
        repository.remove(repository.findById(id));
        return Response
                .ok()
                .build();

    }

    @GET
    @Path("/evaluation/{surveyId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response auswertung(@PathParam("surveyId") long id) {
        return Response.ok(repository.evaluation(id)).build();
    }

    @GET
    @Path("/evaluation2/{surveyId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response auswertung2(@PathParam("surveyId") long id) {
        return Response.ok(repository.evaluation2(id)).build();
    }
}
