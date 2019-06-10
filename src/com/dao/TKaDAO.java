package com.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.TKa;

/**
 * A data access object (DAO) providing persistence and search support for TKa
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.model.TKa
 * @author MyEclipse Persistence Tools
 */

public class TKaDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(TKaDAO.class);
	// property constants
	public static final String KAHAO = "kahao";
	public static final String YINHANG = "yinhang";
	public static final String LEIXING = "leixing";
	public static final String USER_ID = "userId";

	protected void initDao() {
		// do nothing
	}

	public void save(TKa transientInstance) {
		log.debug("saving TKa instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TKa persistentInstance) {
		log.debug("deleting TKa instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TKa findById(java.lang.Integer id) {
		log.debug("getting TKa instance with id: " + id);
		try {
			TKa instance = (TKa) getHibernateTemplate()
					.get("com.model.TKa", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TKa instance) {
		log.debug("finding TKa instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TKa instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TKa as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKahao(Object kahao) {
		return findByProperty(KAHAO, kahao);
	}

	public List findByYinhang(Object yinhang) {
		return findByProperty(YINHANG, yinhang);
	}

	public List findByLeixing(Object leixing) {
		return findByProperty(LEIXING, leixing);
	}

	public List findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List findAll() {
		log.debug("finding all TKa instances");
		try {
			String queryString = "from TKa";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TKa merge(TKa detachedInstance) {
		log.debug("merging TKa instance");
		try {
			TKa result = (TKa) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TKa instance) {
		log.debug("attaching dirty TKa instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TKa instance) {
		log.debug("attaching clean TKa instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TKaDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TKaDAO) ctx.getBean("TKaDAO");
	}
}