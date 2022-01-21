package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Position implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idP;
	@Column(length = 30)
	private double latitude;
	@Column(length = 30)
	private double longitude;
	@Temporal(TemporalType.DATE)
	private Date date;
	@ManyToOne
	@JoinColumn(name="idSP")
	private SmartPhone smartphone;
	
	
	public Position() {
		super();
	}
	
	
	
	public Position(Long idP, double latitude, double longitude, Date date) {
		super();
		this.idP = idP;
		this.latitude = latitude;
		this.longitude = longitude;
		this.date = date;
	}



	public Long getIdP() {
		return idP;
	}
	public void setIdP(Long idP) {
		this.idP = idP;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public SmartPhone getSmartphone() {
		return smartphone;
	}
	public void setSmartphone(SmartPhone smartphone) {
		this.smartphone = smartphone;
	}
		
	
	
	
	
}
