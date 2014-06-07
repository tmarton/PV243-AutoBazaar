package cz.muni.fi.pv243.dao;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by tmarton.
 */
@Named(value = "baseDao")
@Stateless
public class BaseDaoImpl<T, ID extends Serializable> implements BaseDao<T,ID> {

    @Inject
    protected EntityManager entityManager;

    protected Class<T> persistentClass;

    @Override
    public T getByID(ID id) {
        return entityManager.find(persistentClass,id);
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
        if (persistentClass == null)
            throw new IllegalArgumentException("persistentClass must be set");
        
        entityManager.createQuery("DELETE FROM " + persistentClass.getName()).executeUpdate();
    }

    @Override
    public List<T> getAll(){
        if (persistentClass == null)
            throw new IllegalArgumentException("persistentClass must be set");
        
       return entityManager.createQuery("FROM " + persistentClass.getName()).<T>getResultList();
    }

    @Override
    public List<T> getAllOrdered(String column, boolean ascending){
        if (persistentClass == null)
            throw new IllegalArgumentException("persistentClass must be set");
        
        Order order;
        if (ascending) {
            order = Order.asc(column);
        } else {
            order = Order.desc(column);
        }
        order = order.ignoreCase();

        DetachedCriteria d = DetachedCriteria.forClass(persistentClass).addOrder(order);
        return d.getExecutableCriteria(entityManager.unwrap(Session.class)).list();
    }

    public Class<T> getPersistentClass() {
        if(persistentClass == null) {
            persistentClass = (Class<T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return persistentClass;
    }

    public void setPersistentClass(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }
}
