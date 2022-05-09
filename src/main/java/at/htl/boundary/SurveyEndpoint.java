package at.htl.boundary;
import at.htl.control.QuestionnaireRepository;
import at.htl.control.SurveyRepository;
import at.htl.control.TransactionRepository;
import at.htl.entities.Questionnaire;
import at.htl.entities.Survey;
import at.htl.entities.Transaction;


import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;

@Path("leoquest")
public class SurveyEndpoint {
    @Inject
    SurveyRepository surveyRepository;

    @Inject
    TransactionRepository transactionRepository;

    @Inject
    QuestionnaireRepository questionnaireRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/survey")
    public Response findAllSurveys(){
        final List<Survey> surveys = (List<Survey>) surveyRepository.findAll();
        return Response.ok(surveys).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/createSurvey/{amount}")
    public Response createTransactions(@PathParam("amount")int amount){
        Questionnaire questionnaire = questionnaireRepository.findById(1L);
        Survey survey1 = new Survey(LocalDate.now(), questionnaire);
        System.out.println(survey1);
        surveyRepository.persist(survey1);
        final List<Transaction> transactions = this.transactionRepository.generateTransactionCode(survey1, amount);
        return Response
                .ok()
                .entity(transactions)
                .build();
    }

}
