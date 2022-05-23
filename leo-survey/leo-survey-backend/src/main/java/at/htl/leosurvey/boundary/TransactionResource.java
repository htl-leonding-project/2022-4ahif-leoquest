package at.htl.leosurvey.boundary;

import at.htl.leosurvey.control.SurveyRepository;
import at.htl.leosurvey.control.TransactionRepository;
import at.htl.leosurvey.entity.Survey;
import at.htl.leosurvey.entity.Transaction;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.File;
import java.util.List;

@Path("transaction")
@Produces(MediaType.APPLICATION_JSON)
public class TransactionResource {

    @Inject
    TransactionRepository repository;

    @Inject
    SurveyRepository surveyRepository;

    @GET
    public List<Transaction> findAllTransactions() {
        return repository.listAll();
    }


    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransactionById(@PathParam("id") Long id) {
        Transaction transaction = repository.findById(id);
        if (transaction != null) {
            return Response
                    .ok(transaction)
                    .build();
        } else {
            return Response.noContent().build();
        }
    }

    @GET
    @Path("code/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransactionWithCode(@PathParam("code") String code) {
        Transaction transaction = repository.getTransactionByCode(code);
        if (transaction != null) {
            if (transaction.isUsed == true) {
                return Response.ok(null).build();
            }
        }
        return Response.ok(transaction).build();
    }

    @POST
    @Path("/getSurvey")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSurveyWithTransaction(Transaction transaction) {
        Survey survey = repository.findSurveyWithTransaction(transaction);
        return Response.ok(survey).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTransaction(Transaction transaction, @Context UriInfo info) {
        transaction = repository.save(transaction);
        UriBuilder uriBuilder = info.getAbsolutePathBuilder();
        uriBuilder.path(transaction.id.toString());
        return Response.created(uriBuilder.build()).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTransaction(@PathParam("id") long id, Transaction transaction) {
        Transaction old = repository.findById(id);

        if (old != null) {
            old.transactionCode = transaction.transactionCode;
            old.isUsed = transaction.isUsed;
            old.survey = transaction.survey;

            repository.save(old);
            return Response
                    .ok()
                    .entity(old)
                    .build();
        } else {
            return Response
                    .status(400)
                    .header("Reason", "Transaction with id " + id + " does not exist")
                    .build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteTransaction(@PathParam("id") long id) {
        Transaction transaction = repository.findById(id);
        repository.remove(transaction);
        return Response
                .ok()
                .build();

    }

    @GET
    @Path("generate/{number}/{surveyId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateTransactions(@PathParam("number") int number, @PathParam("surveyId") long surveyId) {
        Survey survey = surveyRepository.findById(surveyId);
        File file = repository.generateTransactions(number, survey);
        return Response.ok(file).build();
    }
}
