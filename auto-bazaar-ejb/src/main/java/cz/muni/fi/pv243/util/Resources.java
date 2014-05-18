package cz.muni.fi.pv243.util;

import cz.muni.fi.pv243.dao.BaseDao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This class uses CDI to alias Java EE resources, such as the persistence context, to CDI beans
 *
 * <p>
 * Example injection on a managed bean field:
 * </p>
 *
 * <pre>
 * &#064;Inject
 * private EntityManager em;
 * </pre>
 */
public class Resources {
    @SuppressWarnings("unused")
    @Produces
    @PersistenceContext
    private EntityManager em;

    @Produces
    public Logger produceLog(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

    @Produces
    @Dependent
    @Dao
    public <T, ID extends Serializable> BaseDao<T, ID> produceDao(InjectionPoint ip, BeanManager bm) {
        if(ip.getAnnotated().isAnnotationPresent(Dao.class)) {
            BaseDao<T, ID> baseDao = (BaseDao<T, ID>) this.getBeanByType(ip.getType(), bm);
            ParameterizedType type = (ParameterizedType) ip.getType();
            Type[] typeArgs = type.getActualTypeArguments();
            Class<T> entityClass = (Class<T>) typeArgs[0];
            baseDao.setPersistentClass(entityClass);
            return baseDao;
        }
        throw new IllegalArgumentException("Annotation @Dao is required when injecting BaseDao");
    }

    public static Object getBeanByType(Type t, BeanManager bm){
        Bean bean = bm.getBeans(t).iterator().next();
        CreationalContext ctx = bm.createCreationalContext(bean);
        return bm.getReference(bean, bean.getBeanClass(), ctx);
    }
}
