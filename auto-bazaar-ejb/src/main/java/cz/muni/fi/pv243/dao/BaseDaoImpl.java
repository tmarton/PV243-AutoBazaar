package cz.muni.fi.pv243.dao;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by tmarton.
 * @param <T> Entity type
 * @param <ID> Entity id type
 */
@Named(value = "baseDao")
public class BaseDaoImpl<T, ID extends Serializable> implements BaseDao<T,ID> {

    @Inject
    protected EntityManager entityManager;

    protected Class<T> persistentClass;

    @Override
    public T getByID(ID id) {
        return entityManager.find(getPersistentClass(), id);
    }

    @Override
    public void save(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(T entity) {
        entityManager.merge(entity);
    }

    @Override
    public void remove(T entity){
        T managedEntity = entityManager.merge(entity);
        entityManager.remove(managedEntity);
    }

    @Override
    public void removeAll() {
        entityManager.createQuery("DELETE FROM " + getPersistentClass().getName()).executeUpdate();
    }

    @Override
    public List<T> getAll(){
       return entityManager.createQuery("FROM " + getPersistentClass().getName()).<T>getResultList();
    }

    @Override
    public List<T> getAllOrdered(String column, boolean ascending){
        Order order;
        if (ascending) {
            order = Order.asc(column);
        } else {
            order = Order.desc(column);
        }
        order = order.ignoreCase();

        DetachedCriteria d = DetachedCriteria.forClass(getPersistentClass()).addOrder(order);
        return d.getExecutableCriteria(entityManager.unwrap(Session.class)).list();
    }

    @Override
    public Class<T> getPersistentClass() {
        if (persistentClass == null) {
            persistentClass = (Class<T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return persistentClass;
    }

    @Override
    @Deprecated
    public void setPersistentClass(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }
}
