package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dao.ServerDaoLocal;
import dao.ServerDaoRemote;
import entities.User;
import entities.Position;
import entities.SmartPhone;


/**
 * Session Bean implementation class SalleDao
 */
@Stateless
public class ServiceDao implements ServerDaoRemote, ServerDaoLocal {
	@PersistenceContext
	private EntityManager em;

	//******Crud SmartPhones*********
	@Override
	public boolean create(SmartPhone s) {
		em.persist(s);
		return true;
	}

	@Override
	public SmartPhone addSmartPhone(SmartPhone s, Long idUser) {
		User b=em.find(User.class, idUser);
		if(b==null)
			throw new RuntimeException("SmartPhone  introuvable !!!");
		s.setUser(b);
		em.persist(s);
		return s;
	}

	@Override
	public boolean update(SmartPhone s,Long id) {
		User b=em.find(User.class, id);
		if(b==null)
			throw new RuntimeException("SmartPhone  introuvable !!!");
		s.setUser(b);
		
		try
	    {
			em.createNamedQuery("update",SmartPhone.class)
	        .setParameter(1, s.getImei())
	        .setParameter(2, s.getUser())
	        .setParameter(3, s.getIdSP())
	        .executeUpdate();
			return true;
	       
	    }
	    catch (Exception e)
	    {
	        return false;
	    }
	}

	@Override
	public boolean delete(SmartPhone s) {
		em.remove(em.contains(s) ? s : em.merge(s));
		return true;
	}

	@Override
	public SmartPhone findSmartPhoneById(Long id) {
		return em.find(SmartPhone.class, id);
	}

	@Override
	public List<SmartPhone> findAllSmartPhones() {
		Query query = em.createQuery("select s from SmartPhone s");
		return query.getResultList();
	}
	
	//******Crud User*********
		@Override
		public boolean create(User b) {
			em.persist(b);
			return true;
		}

		@Override
		public boolean update(User b,String n,String p,String e,String t,Date d) {
			em.createQuery("update User b set b.nom = :x, b.prenom = :x1, b.email = :x2, b.tel = :x3, b.date = :x4 where b.idUser = :y")
			.setParameter("x", n).setParameter("x1", p).setParameter("x2", e).setParameter("x3", t).setParameter("x4", d).setParameter("y",b.getIdUser()).executeUpdate();
			
			return true;
		}

		@Override
		public boolean delete(User b) {
			em.remove(em.contains(b) ? b : em.merge(b));
			return true;
		}

		@Override
		public User findUserById(Long id) {
			User b=em.find(User.class, id);
			if(b==null)
				throw new RuntimeException("User introuvable");
			return b;
		}

		
		
		@Override
		public List<User> findAllUsers() {
			Query query = em.createQuery("select u from User u");
			
			return query.getResultList();
		}

		
		
		@Override
		public List<SmartPhone> findSmartPhoneByUser(Long code) {
			Query query = em.createQuery("select u from SmartPhone u where u.user.id=:idUser", SmartPhone.class)
			.setParameter("idUser", code);
			return query.getResultList();
		}

		
		
	    @Override
	    public List<Object[]> nbpositions(){
	    	Query query = em.createQuery("select count(m), m.smartphone.imei from Position m group by m.smartphone.imei");
	    	return  query.getResultList();
	    }
	    
	    @Override
	    public List<Object[]> nbpositionsBymois(){
	    	Query query = em.createQuery("select count(m), m.smartphone.imei from Position m group by MONTH(m.date),m.smartphone.imei");
	    	return  query.getResultList();
	    }
	  
	  
	    @Override
		public List<Position> findPositionByDate(Date date1,Date date2) {
			Query query = em.createQuery("select u from Position u where u.date>=:da and u.date<=:de")
			.setParameter("da", date1)
			.setParameter("de", date2);
			return query.getResultList();
		}

	
    
}