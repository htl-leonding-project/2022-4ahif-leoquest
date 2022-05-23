package at.htl.leosurvey.boundary;

import com.intuit.karate.junit5.Karate;
import io.quarkus.test.junit.QuarkusTest;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class TransactionResourceTest {

    @Karate.Test
    Karate testTransaction() {
        return Karate.run("transaction").relativeTo(getClass());
    }

}
