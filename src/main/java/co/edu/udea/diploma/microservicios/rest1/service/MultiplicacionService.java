package co.edu.udea.diploma.microservicios.rest1.service;

import java.util.List;

import co.edu.udea.diploma.microservicios.rest1.domain.Multiplication;
import co.edu.udea.diploma.microservicios.rest1.domain.MultiplicationResultAttempt;

public interface MultiplicacionService {

	boolean checkAttempt(final MultiplicationResultAttempt attempt);

	Multiplication createRandomMultiplication();

	List<MultiplicationResultAttempt> getStatsForUser(String userAlias);

	MultiplicationResultAttempt getResultById(final Long resultId);
}
