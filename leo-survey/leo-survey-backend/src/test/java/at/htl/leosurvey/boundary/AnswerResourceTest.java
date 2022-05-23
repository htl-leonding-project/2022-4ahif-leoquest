package at.htl.leosurvey.boundary;

import com.intuit.karate.junit5.Karate;
import io.quarkus.test.junit.QuarkusTest;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class AnswerResourceTest {

    @Karate.Test
    Karate testAnswer() {
        return Karate.run("answer").relativeTo(getClass());
    }

}
