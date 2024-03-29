package co.edu.udea.diploma.microservicios.rest1.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
public class User {

	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private long id;
	private final String alias;

	protected User() {
		alias = null;
	}

}
