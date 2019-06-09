package co.edu.udea.diploma.microservicios.rest1.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
public final class Multiplication {

	@Id
	@GeneratedValue
	@Column(name = "MULTIPLICATION_ID")
	private long id;
	private final int factorA;
	private final int factorB;

	// Empty constructor
	Multiplication() {
		this(0, 0);
	}
}
