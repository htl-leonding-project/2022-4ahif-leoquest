package at.htl.leosurvey.boundary;

import com.intuit.karate.junit5.Karate;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class InterviewerResourceTest {

    @Karate.Test
    Karate testCreateInterviewer() {
        return Karate.run("interviewer-create").relativeTo(getClass());
    }

}
