package at.htl.leosurvey.boundary;

import com.intuit.karate.junit5.Karate;
import io.quarkus.test.junit.QuarkusTest;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class AnswerOptionResourceTest {

    @Karate.Test
    Karate testAnswerOption() {
        return Karate.run("answerOption").relativeTo(getClass());
    }

}
