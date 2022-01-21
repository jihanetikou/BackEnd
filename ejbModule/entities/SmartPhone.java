package entities;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;


import javax.persistence.*;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entity implementation class for Entity: SmartPhone
 *
 */
@Entity
@SqlResultSetMapping(name="updateResult", columns = { @ColumnResult(name = "count")})
@NamedNativeQueries({
    @NamedNativeQuery(
            name    =   "update",
            query   =   "UPDATE SmartPhone SET imei = ?, idUser = ? WHERE idSP = ?"
            ,resultSetMapping = "updateResult"
    )
})
public class SmartPhone implements Serializable {
	
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSP;
	@Column(length = 30)
	private String imei;
	@ManyToOne
	@JoinColumn(name="idUser")
	private User user;
	
	public SmartPhone() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SmartPhone(String imei) {
		super();
		this.imei = imei;
		
	}
	public SmartPhone(Long id,String imei) {
		super();
		this.imei = imei;
		this.idSP=id;
	}
	
	
	public Long getIdSP() {
		return idSP;
	}
	public void setIdSP(Long idSP) {
		this.idSP = idSP;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	

	
   
}
