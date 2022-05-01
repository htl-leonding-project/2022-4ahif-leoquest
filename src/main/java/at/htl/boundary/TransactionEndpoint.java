package at.htl.boundary;

import at.htl.control.TransactionRepository;
import at.htl.entities.Transaction;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("leoquest")
public class TransactionEndpoint {
    @Inject
    TransactionRepository transactionRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/transactioncode")
    public Response findAllCodes(){
        final List<Transaction> codes = transactionRepository.listAll();
        return Response.ok(codes).build();
    }
}
