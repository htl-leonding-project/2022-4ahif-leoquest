package at.htl.api;

import at.htl.control.TeacherRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("teacher")
public class TeacherRessource {

    @Inject
    TeacherRepository teacherRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List findAll(){

        return teacherRepository.findAll();
    }

    @GET
    @Path("all/answers")
    public Response getAnswersCount(){

        return null;
    }
}
