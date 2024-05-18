/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package top.dribles.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import java.util.List;
import top.dribles.model.Pessoa;

/**
 *
 * @author crist
 */
public class PessoaDAOImpl implements PessoaDAO {
    private EntityManager entityManager;
    
    public PessoaDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Override
    public List<Pessoa> getAll() {
        return entityManager.createQuery("SELECT p FROM Pessoa p",
                Pessoa.class).getResultList();
    }
    
    @Override
    public int insert(Pessoa pessoa) {
        EntityTransaction transaction = null;
        int pessoaId = -1;
        
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(pessoa);
            transaction.commit();
            
            Query query = entityManager.createQuery("SELECT p.id FROM Pessoa p WHERE p = :pessoa");
            query.setParameter("pessoa", pessoa);
            pessoaId = (int) query.getSingleResult();
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
        
        return pessoaId;
    }
}
