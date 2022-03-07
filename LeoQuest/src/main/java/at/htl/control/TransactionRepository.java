package at.htl.control;

import at.htl.entity.Teacher;
import at.htl.entity.Transaction;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class TransactionRepository {

    @Inject
    EntityManager em;

    @Transactional
    public void delete(Transaction transaction) {
        em.remove(transaction);
    }

    @Transactional
    public void save(Transaction transaction){
        em.merge(transaction);
    }

    public List<Transaction> findAll() {
        return em
                .createNamedQuery("Transaction.findAll", Transaction.class)
                .getResultList();
    }

    public Transaction findById(Long id) {

        Query query = em.createNamedQuery("Transaction.findById",
                Transaction.class);
        query.setParameter("id", id);

        return (Transaction)query.getSingleResult();

    }
}
