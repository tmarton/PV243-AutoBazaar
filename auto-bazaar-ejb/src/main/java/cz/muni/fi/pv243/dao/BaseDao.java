package cz.muni.fi.pv243.dao;

import javax.ejb.Local;
import java.io.Serializable;
import java.util.List;

/**
 * Created by tmarton.
 */
@Local
public interface BaseDao<T, ID extends Serializable> {

    public T getByID(ID id);

    public void save(T entity);

    public void update(T entity);

    public void remove(T entity);

    public List<T> getAll();

    public List<T> getAllOrdered(String column, boolean ascending);

    public Class<T> getPersistentClass();

    public void setPersistentClass(Class<T> persistentClass);

}
