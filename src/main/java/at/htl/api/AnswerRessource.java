package at.htl.api;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Map;

@Produces("application/json")
@Consumes("application/json")
@Path("answer")
public class AnswerRessource {
    @GET
    @Path("all/answers")
    public Response getAnswersCount(){

        return null;
    }

}

