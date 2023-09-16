package cl.coe.ejercicio1.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class Phone.
 */
@Entity
@Table(name = "\"phone\"")
public class Phone {
	
	/** The id phone. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPhone;
	
	/** The number. */
	private String number;

	/** The citycode. */
	private String citycode;
	
	/** The contrycode. */
	private String contrycode;
	
	/** The user. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUser")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private User user;
	
	

	/**
	 * Gets the id phone.
	 *
	 * @return the id phone
	 */
	public Integer getIdPhone() {
		return idPhone;
	}

	/**
	 * Sets the id phone.
	 *
	 * @param idPhone the new id phone
	 */
	public void setIdPhone(Integer idPhone) {
		this.idPhone = idPhone;
	}

	/**
	 * Gets the number.
	 *
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * Sets the number.
	 *
	 * @param number the new number
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * Gets the citycode.
	 *
	 * @return the citycode
	 */
	public String getCitycode() {
		return citycode;
	}

	/**
	 * Sets the citycode.
	 *
	 * @param citycode the new citycode
	 */
	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	/**
	 * Gets the contrycode.
	 *
	 * @return the contrycode
	 */
	public String getContrycode() {
		return contrycode;
	}

	/**
	 * Sets the contrycode.
	 *
	 * @param contrycode the new contrycode
	 */
	public void setContrycode(String contrycode) {
		this.contrycode = contrycode;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
		
}
