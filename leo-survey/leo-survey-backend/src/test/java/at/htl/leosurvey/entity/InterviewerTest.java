package at.htl.leosurvey.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class InterviewerTest {

    @Test
    void t010_addInterviewer() {
        Interviewer interviewer =
                new Interviewer("Seppi");


        assertThat(interviewer.toString())
                .isEqualTo("Interviewer{id=null, name='Seppi'}");
    }

}
