package Integrador2.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

public class BaseJPARepository<Entity, ID extends Serializable> implements Repository<Entity, ID> {

    EntityManager em;
    Class<Entity> entityClass;
    Class<ID> idClass;

    public BaseJPARepository(EntityManager em, Class<Entity> entityClass, Class<ID> idClass) {
        this.em = em;
        this.idClass = idClass;
        this.entityClass = entityClass;
        this.em = em;
    }

    @Override
    public Entity findById(ID id) {
        return em.find(entityClass, id);
    }

    @Override
    public void persist(Entity entity) {
        if (em.contains(entity)) {
            this.update(entity);
        } else {
            em.persist(entity);
        }
    }

    @Override
    public Entity update(Entity entity) {
        em.merge(entity);
        return entity;
    }

    @Override
    public void delete(ID id) {
        Entity entity = em.find(entityClass, id);
        em.remove(entity);
    }

    @Override
    public List<Entity> findAll() {
        String q = "SELECT e FROM " + entityClass.getSimpleName() + " e";
        TypedQuery<Entity> query = em.createQuery(q, entityClass);
        return query.getResultList();
    }
}
