package at.htl.control;


import at.htl.entities.*;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class InitBean {
    @PersistenceContext
    EntityManager em;

    @Inject
    AnswerOptionRepository answerOptionRepository;
    @Inject
    QuestionnaireRepository questionnaireRepository;
    @Inject
    QuestionRepository questionRepository;
    @Inject
    TransactionRepository transactionRepository;
    @Inject
    SurveyRepository surveyRepository;
    @Inject
    TeacherRepository teacherRepository;

    //Comment if you want to run the Tests

    void onStartUp(@Observes StartupEvent event){
        init();
    }

    public void init(){
        List<Question> questions = em.createQuery("select q from Question q").getResultList();
        TypedQuery<Question> query = em.createQuery("select q from Question q where q.id = ?1", Question.class);

        //Questionaire
        Questionnaire q = new Questionnaire(1L,"Lehrer-Fragebogen", "Fragebogen über einen Lehrer");
        questionnaireRepository.persist(q);

        //Survey
        LocalDate d = LocalDate.now();
        Survey s = new Survey(d, (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 1").getSingleResult());
        surveyRepository.persist(s);

        //TransactionCodes
        Transaction s_t1 = new Transaction("abc",
                false, (Survey) em.createQuery("select s from Survey s where s.id = 1").getSingleResult());
        Transaction s_t2 = new Transaction("abc",
                false, (Survey) em.createQuery("select s from Survey s where s.id = 1").getSingleResult());
        Transaction s_t3 = new Transaction("abc",
                false, (Survey) em.createQuery("select s from Survey s where s.id = 1").getSingleResult());
        transactionRepository.persist(s_t1);
        transactionRepository.persist(s_t2);
        transactionRepository.persist(s_t3);

        //Teacher
        Teacher t = new Teacher("Lehrer",
                (Survey) em.createQuery("select s from Survey s where s.id = 1").getSingleResult());
        teacherRepository.persist(t);

        //Question a - i
        Question qa = new Question("Der Lehrer ist fair - gerecht?", 1,
                QuestionType.SingleChoice.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 1").getSingleResult());
        Question qb = new Question("Der Lehrer ist humorvoll - fröhlich?", 2,
                QuestionType.SingleChoice.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 1").getSingleResult());
        Question qc = new Question("Der Lehrer ist hilfsbereit - unterstützend?", 3,
                QuestionType.SingleChoice.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 1").getSingleResult());
        Question qd = new Question("Der Lehrer ist freundlich?", 4,
                QuestionType.SingleChoice.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 1").getSingleResult());
        Question qe = new Question("Der Lehrer ist engagiert - aktiv?", 5,
                QuestionType.SingleChoice.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 1").getSingleResult());
        Question qf = new Question("Der Lehrer ist verständnisvoll - rücksichtsvoll?", 6,
                QuestionType.SingleChoice.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 1").getSingleResult());
        Question qg = new Question("Der Lehrer ist ehrlich - aufrichtig?", 7,
                QuestionType.SingleChoice.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 1").getSingleResult());
        Question qh = new Question("Der Lehrer ist kooperativ - kontaktfreudig?", 8,
                QuestionType.SingleChoice.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 1").getSingleResult());
        Question qi = new Question("Der Lehrer ist tolerant - lässt Kritik zu?", 9,
                QuestionType.SingleChoice.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 1").getSingleResult());

        questionRepository.persist(qa);
        questionRepository.persist(qb);
        questionRepository.persist(qc);
        questionRepository.persist(qd);
        questionRepository.persist(qe);
        questionRepository.persist(qf);
        questionRepository.persist(qg);
        questionRepository.persist(qh);
        questionRepository.persist(qi);

        //Question 1 - 30
        Question q1 = new Question("Er kann auch schwierige Fragen beantworten, weil er sich in seinem " +
                "Fachgebiet gut auskennt", 10, QuestionType.SingleChoice.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 1").getSingleResult());
        Question q2 = new Question("Für eine gute Note ist es am wichtigsten, wie sympathisch man dem " +
                "Lehrer ist - die Leistung ist Nebensache", 11, QuestionType.SingleChoice.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 1").getSingleResult());
        Question q3 = new Question("Ich denke, dieser Lehrer kennt mich persönlich schon sehr gut, sowohl " +
                "in meinen Stärken wie in meinen Schwächen", 12, QuestionType.SingleChoice.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 1").getSingleResult());
        Question q4 = new Question("Er bemüht sich, uns der Leistung und dem Benehmen entsprechend " +
                "zu loben oder zu ermahnen und niemanden zu bevorzugen", 13, QuestionType.SingleChoice.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 1").getSingleResult());
        Question q5 = new Question("Bei diesem Lehrer ist mir oft nicht klar, worauf es bei seinen Prüfungen " +
                "ankommt oder wie meine Noten zustandekommen", 14, QuestionType.SingleChoice.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 1").getSingleResult());
        Question q6 = new Question("Ich glaube, dass ich durch seinen Unterricht schon viel Neues gelernt " +
                "habe", 15, QuestionType.SingleChoice.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 1").getSingleResult());
        Question q7 = new Question("Sein Unterricht ist interessant und attraktiv, weil er sich um Abwechslung und " +
                "Praxisnähe bemüht", 16, QuestionType.SingleChoice.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 1").getSingleResult());
        Question q8 = new Question("Er verlangt häufig zuviel von uns, und deswegen fühle ich mich beim " +
                "Lernen oft überfordert und gestresst", 17, QuestionType.SingleChoice.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 1").getSingleResult());
        Question q9 = new Question("Der Lehrer erklärt im Unterricht so verständlich und klar, dass ich " +
                "alles Wichtige gut verstehe und begreife", 18, QuestionType.SingleChoice.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 1").getSingleResult());
        Question q10 = new Question("Wenn ich einen Fehler mache oder mich schlecht benehme, werde ich " +
                "von diesem Lehrer vor der ganzen Klasse beschimpft oder lächerlich " +
                "gemacht", 19, QuestionType.SingleChoice.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 1").getSingleResult());
        Question q11 = new Question("Ich freue mich, wenn dieser Lehrer zum Unterricht in unsere Klasse " +
                "kommt", 20, QuestionType.SingleChoice.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 1").getSingleResult());
        Question q12 = new Question("Er erklärt uns oft, wie der Lehrstoff mit anderen Fächern oder mit " +
                "praktischen Problemen zusammenhängt", 21, QuestionType.SingleChoice.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 1").getSingleResult());
        Question q13 = new Question("Wenn ich bei ihm etwas richtig oder gut gemacht habe, so werde " +
                "ich dafür auch entsprechend gelobt", 22, QuestionType.SingleChoice.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 1").getSingleResult());
        Question q14 = new Question("Bevor er Hausübungen oder etwas zu lernen aufgibt, erkundigt er " +
                "sich, ob wir schon andere Hausübungen erhalten haben oder Prüfungen bevorstehen",
                23, QuestionType.SingleChoice.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 1").getSingleResult());
        Question q15 = new Question("Er freut sich, wenn es ihm gelungen ist, uns etwas Neues oder " +
                "Schwieriges beizubringen", 24, QuestionType.SingleChoice.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 1").getSingleResult());
        Question q16 = new Question("Dieser Lehrer schafft es oft nicht, die zum Arbeiten nötige Ruhe und " +
                "Ordnung in unserer Klasse herzustellen", 25, QuestionType.SingleChoice.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 1").getSingleResult());
        Question q17 = new Question("Wenn ich Schwierigkeiten beim Lernen habe, kann ich auf die Hilfe " +
                "und das Verständnis des Lehrers zählen", 26, QuestionType.SingleChoice.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 1").getSingleResult());
        Question q18 = new Question("Er bemüht sich sehr, uns in seinem Fach auch aktuelle, moderne " +
                "und lebensnahe Informationen und Beispiele zu geben", 27, QuestionType.SingleChoice.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 1").getSingleResult());
        Question q19 = new Question("Wenn ich einen guten Einfall habe und etwas sagen will, dann " +
                "beachtet mich der Lehrer oft gar nicht", 28, QuestionType.SingleChoice.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 1").getSingleResult());
        Question q20 = new Question("Durch sein Engagement und seinen Einsatz gelingt es dem Lehrer, " +
                "uns für sein Fach besonders zu interessieren und zu aktivieren", 29, QuestionType.SingleChoice.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 1").getSingleResult());
        Question q21 = new Question("Der Lehrer achtet darauf, dass wir Schüler im Unterricht auch unsere " +
                "eigenen Ideen verwirklichen können", 30, QuestionType.SingleChoice.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 1").getSingleResult());
        Question q22 = new Question("Wenn mich bei diesem Lehrer etwas stört, so kann ich das offen " +
                "sagen, ohne dass ich Nachteile befürchten muss", 31, QuestionType.SingleChoice.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 1").getSingleResult());
        Question q23 = new Question("Dieser Lehrer beschäftigt sich hauptsächlich mit den " +
                "guten Schüler/innen in der Klasse", 32, QuestionType.SingleChoice.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 1").getSingleResult());
        Question q24 = new Question("Wenn er Hausübungen gibt, dann kann ich diese normalerweise " +
                "selbständig und ohne Hilfe anderer Personen bewältigen", 33, QuestionType.SingleChoice.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 1").getSingleResult());
        Question q25 = new Question("Wenn ich etwas falsch mache - zum Beispiel bei der Hausübung -, " +
                "dann erklärt er mir genau, wie man es richtig macht", 34, QuestionType.SingleChoice.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 1").getSingleResult());
        Question q26 = new Question("Er bringt mir in diesem Fach etwas bei, was für mich im späteren " +
                "Leben oder im Beruf wichtig sein wird", 35, QuestionType.SingleChoice.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 1").getSingleResult());
        Question q27 = new Question("Durch die hohen Lernanforderungen des Lehrers habe ich oft große " +
                "Angst, bei seinen Prüfungen zu versagend", 36, QuestionType.SingleChoice.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 1").getSingleResult());
        Question q28 = new Question("Die Noten, die ich bei diesem Lehrer bekomme, entsprechen meinen " +
                "tatsächlichen Leistungen", 37, QuestionType.SingleChoice.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 1").getSingleResult());
        Question q29 = new Question("Ich würde mir wünschen, dass mich dieser Lehrer auch im nächsten " +
                "Jahr wieder unterrichtet", 38, QuestionType.SingleChoice.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 1").getSingleResult());
        Question q30 = new Question("Dieser Lehrer ist mir sympathisch", 39,
                QuestionType.SingleChoice.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 1").getSingleResult());
        Question q31 = new Question("Wie zufrieden bist du mit deinem LEHRER insgesamt?", 40,
                QuestionType.SingleChoice.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 1").getSingleResult());
        Question q32 = new Question("An diesem Lehrer gefaellt mir,", 41,
                QuestionType.Text.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 1").getSingleResult());
        Question q33 = new Question("An diesem Lehrer stoert mich, ", 42,
                QuestionType.Text.name(),
                (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 1").getSingleResult());

        questionRepository.persist(q1);
        questionRepository.persist(q2);
        questionRepository.persist(q3);
        questionRepository.persist(q4);
        questionRepository.persist(q5);
        questionRepository.persist(q6);
        questionRepository.persist(q7);
        questionRepository.persist(q8);
        questionRepository.persist(q9);
        questionRepository.persist(q10);
        questionRepository.persist(q11);
        questionRepository.persist(q12);
        questionRepository.persist(q13);
        questionRepository.persist(q14);
        questionRepository.persist(q15);
        questionRepository.persist(q16);
        questionRepository.persist(q17);
        questionRepository.persist(q18);
        questionRepository.persist(q19);
        questionRepository.persist(q20);
        questionRepository.persist(q21);
        questionRepository.persist(q22);
        questionRepository.persist(q23);
        questionRepository.persist(q24);
        questionRepository.persist(q25);
        questionRepository.persist(q26);
        questionRepository.persist(q27);
        questionRepository.persist(q28);
        questionRepository.persist(q29);
        questionRepository.persist(q30);
        questionRepository.persist(q31);
        questionRepository.persist(q32);
        questionRepository.persist(q33);


        //Answer Options for Questions a - i
        for(int i = 1; i <= 9; i++){
            Question question = query.setParameter(1, Long.valueOf(i)).getSingleResult();
            AnswerOption a = new AnswerOption("Diese Eigenschaft trifft auf meinen Lehrer" +
                    " völlig zu", 1, i, question, 0);
            answerOptionRepository.persist(a);
        }
        for(int i = 1; i <= 9; i++){
            Question question = query.setParameter(1, Long.valueOf(i)).getSingleResult();
            AnswerOption a = new AnswerOption("Diese Eigenschaft trifft auf meinen Lehrer" +
                    " eher zu", 2, i, question, 0);
            answerOptionRepository.persist(a);
        }
        for(int i = 1; i <= 9; i++){
            Question question = query.setParameter(1, Long.valueOf(i)).getSingleResult();
            AnswerOption a = new AnswerOption("Diese Eigenschaft trifft auf meinen Lehrer" +
                    " eher nicht zu", 3, i, question, 0);
            answerOptionRepository.persist(a);
        }
        for(int i = 1; i <= 9; i++){
            Question question = query.setParameter(1, Long.valueOf(i)).getSingleResult();
            AnswerOption a = new AnswerOption("Diese Eigenschaft trifft auf meinen Lehrer" +
                    " überhaupt nicht zu", 4, i, question, 0);
            answerOptionRepository.persist(a);
        }

        //ANswer Options for Questions 1 - 30
        for(int i = 1; i <= 30; i++){
            Question question = query.setParameter(1, Long.valueOf(i + 9)).getSingleResult();
            AnswerOption a = new AnswerOption("Diese Beschreibung ist völlig richtig",
                    1, i, question, 0);
            answerOptionRepository.persist(a);
        }
        for(int i = 1; i <= 30; i++){
            Question question = query.setParameter(1, Long.valueOf(i + 9)).getSingleResult();
            AnswerOption a = new AnswerOption("Diese Beschreibung ist eher richtig",
                    2, i, question, 0);
            answerOptionRepository.persist(a);
        }
        for(int i = 1; i <= 30; i++){
            Question question = query.setParameter(1, Long.valueOf(i + 9)).getSingleResult();
            AnswerOption a = new AnswerOption("Diese Beschreibung ist eher falsch",
                    3, i, question, 0);
            answerOptionRepository.persist(a);
        }
        for(int i = 1; i <= 30; i++){
            Question question = query.setParameter(1, Long.valueOf(i + 9)).getSingleResult();
            AnswerOption a = new AnswerOption("Diese Beschreibung ist völlig falsch",
                    4, i, question, 0);
            answerOptionRepository.persist(a);
        }

        //Answer Options for Question 31
        Question question = query.setParameter(1, Long.valueOf(40)).getSingleResult();
        AnswerOption a1 = new AnswerOption("sehr zufrieden", 1, 1, question, 0);
        AnswerOption a2 = new AnswerOption("eher zufrieden", 2, 2, question, 0);
        AnswerOption a3 = new AnswerOption("mittelmäßig zufrieden", 3, 3, question, 0);
        AnswerOption a4 = new AnswerOption("eher unzufrieden", 4, 4, question, 0);
        AnswerOption a5 = new AnswerOption("sehr unzufrieden", 5, 5, question, 0);
        answerOptionRepository.persist(a1);
        answerOptionRepository.persist(a2);
        answerOptionRepository.persist(a3);
        answerOptionRepository.persist(a4);
        answerOptionRepository.persist(a5);

    }
}
