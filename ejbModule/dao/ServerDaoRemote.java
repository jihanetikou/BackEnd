package dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import entities.User;
import entities.Position;
import entities.SmartPhone;

@Remote
public interface ServerDaoRemote {
	
	//Crud SmartPhone
	boolean create(SmartPhone s);
	
	SmartPhone addSmartPhone(SmartPhone s, Long idUser);

	boolean update(SmartPhone s,Long id);

	boolean delete(SmartPhone s);

	SmartPhone findSmartPhoneById(Long id);
	

	List<SmartPhone> findAllSmartPhones();
	
	
	
	//Crud User
	
    boolean create(User b);

    boolean update(User b, String n,String p,String e,String t,Date d);

    boolean delete(User b);

    User findUserById(Long id);
  		
	List<User> findAllUsers();

	List<SmartPhone> findSmartPhoneByUser(Long code);

	List<Position> findPositionByDate(Date date1, Date date2);


	List<Object[]> nbpositions();
	List<Object[]> nbpositionsBymois();


}
