package JPARepository;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public class BaseJPARepository<Entity,ID extends Serializable> implements Repository<Entity, ID> {
    protected EntityManager em;
    protected Class<Entity> entityClass;
    protected Class<ID> idClass;

    protected BaseJPARepository(Class<Entity> entityClass, Class<ID> idClass, EntityManager em){
        this.entityClass=entityClass;
        this.idClass=idClass;
        this.em=em;
    }

    @Override
    public Entity findById(ID id) {
        return this.em.find(entityClass,id);
    }

    @Override
    public Entity persist(Entity e) {
        this.em.getTransaction().begin();
        this.em.persist(e);
        this.em.getTransaction().commit();

        return e;
    }

    @Override
    public Entity delete(ID id) {
        this.em.getTransaction().begin();
        Entity e = this.em.find(entityClass,id);
        this.em.remove(e);
        this.em.getTransaction().commit();
        return e;
    }

    @Override
    public Entity update(Entity e) {
        this.em.getTransaction().begin();
        this.em.merge(e);
        this.em.getTransaction().commit();
        return e;
    }

    @Override
    public List<Entity> findAll() {

        String jpqlQuery = "SELECT e FROM " + entityClass.getSimpleName() + " e";

        return this.em.createQuery(jpqlQuery, entityClass).getResultList();
    }
}
