package at.htl.leosurvey.boundary;

import at.htl.leosurvey.control.QuestionRepository;
import at.htl.leosurvey.entity.Question;
import at.htl.leosurvey.entity.Questionnaire;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.File;
import java.util.List;

@Path("question")
@Produces(MediaType.APPLICATION_JSON)
public class QuestionResource {

    @Inject
    QuestionRepository repository;

    @GET
    public List<Question> findAllQuestions() {
        return repository.listAll();
    }

    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQuestionById(@PathParam("id") Long id) {
        Question question = repository.findById(id);
        if (question != null) {
            return Response
                    .ok(question)
                    .build();
        } else {
            return Response.noContent().build();
        }
    }

    @GET
    @Path("questions/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQuestionsByQuestionnaireId(@PathParam("id") Long id){
        List<Question> questions = repository.getQuestionsByQuestionaireId(id);
        return Response.ok(questions).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createQuestion(Question question, @Context UriInfo info) {
        question = repository.save(question);
        UriBuilder uriBuilder = info.getAbsolutePathBuilder();
        uriBuilder.path(question.id.toString());
        return Response.created(uriBuilder.build()).entity(question).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateQuestion(@PathParam("id") long id, Question question) {
        Question old = repository.findById(id);

        if (old != null) {
            old.text = question.text;
            old.sequenceNumber = question.sequenceNumber;
            old.questiontype = question.questiontype;
            old.questionnaire = question.questionnaire;
            //old.image = question.image;
            repository.save(old);
            return Response
                    .ok()
                    .entity(old)
                    .build();
        } else {
            return Response
                    .status(400)
                    .header("Reason", "Question with id " + id + " does not exist")
                    .build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteQuestion(@PathParam("id") long id) {
        Question question = repository.findById(id);
        repository.remove(question);
        return Response
                .ok()
                .build();

    }

    /*@GET
    @Path("image")
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveIMG(){
        repository.saveImage();
        return Response.ok().build();
    }

    @GET
    @Path("imageReturn")
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrunImg() {
        repository.saveImage();
        Question question = repository.findById(97L);
        File file = repository.returnImage(question.image);
        return Response.ok().build();
    }*/
}
