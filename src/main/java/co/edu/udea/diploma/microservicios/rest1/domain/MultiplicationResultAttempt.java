package co.edu.udea.diploma.microservicios.rest1.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
public class MultiplicationResultAttempt {

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "USER_ID")
	private final User user;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "MULTIPLICATION_ID")
	private final Multiplication multiplication;

	private final int resultAttempt;
	private final boolean correct;

	public MultiplicationResultAttempt() {
		this.user = null;
		this.multiplication = null;
		this.resultAttempt = -1;
		this.correct = false;
	}

}
