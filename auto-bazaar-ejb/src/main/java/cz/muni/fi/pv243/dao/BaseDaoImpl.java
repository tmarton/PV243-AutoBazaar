package cz.muni.fi.pv243.dao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by tmarton.
 */
@Named(value = "baseDao")
@Stateless
@Transactional
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
        entityManager.createQuery("DELETE FROM " + persistentClass.getName()).executeUpdate();
    }

    @Override
    public List<T> getAll(){
       return entityManager.createQuery("FROM " + persistentClass.getName()).<T>getResultList();
    }

    @Override
    public List<T> getAllOrdered(String column, boolean ascending){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteria = cb.createQuery(persistentClass);
        Root<T> root = criteria.from(persistentClass);
        criteria.orderBy(ascending ?  cb.asc(root.get(column)) : cb.desc(root.get(column)) );
        return entityManager.createQuery(criteria).getResultList();
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
