package at.htl.control;

import at.htl.entity.Teacher;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class TeacherRepository {

    @Inject
    EntityManager em;

    @Transactional
    public void delete(Teacher teacher) {
        em.remove(teacher);
    }

    @Transactional
    public void save(Teacher teacher){
        em.persist(teacher);
    }

    public List<Teacher> findAll() {
        return em
                .createNamedQuery("Teacher.findAll", Teacher.class)
                .getResultList();
    }

    public Teacher findById(Long id) {

        Query query = em.createNamedQuery("Teacher.findById",
                Teacher.class);
        query.setParameter("id", id);

        return (Teacher)query.getSingleResult();

    }
}
