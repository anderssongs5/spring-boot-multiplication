package co.edu.udea.diploma.microservicios.rest1.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import co.edu.udea.diploma.microservicios.rest1.domain.Multiplication;
import co.edu.udea.diploma.microservicios.rest1.domain.MultiplicationResultAttempt;
import co.edu.udea.diploma.microservicios.rest1.domain.User;
import co.edu.udea.diploma.microservicios.rest1.event.EventDispatcher;
import co.edu.udea.diploma.microservicios.rest1.event.MultiplicationSolvedEvent;
import co.edu.udea.diploma.microservicios.rest1.repository.MultiplicationResultAttemptRepository;
import co.edu.udea.diploma.microservicios.rest1.repository.UserRepository;

@Service
public class MultiplicationServiceImpl implements MultiplicacionService {

	private RandomGeneratorService randomGeneratorService;
	private MultiplicationResultAttemptRepository attemptRepository;
	private UserRepository userRepository;
	private EventDispatcher eventDispatcher;

	@Autowired
	public MultiplicationServiceImpl(final RandomGeneratorService randomGeneratorService,
			final UserRepository userRepository, final MultiplicationResultAttemptRepository attemptRepository,
			final EventDispatcher eventDispatcher) {
		this.randomGeneratorService = randomGeneratorService;
		this.userRepository = userRepository;
		this.attemptRepository = attemptRepository;
		this.eventDispatcher = eventDispatcher;
	}

	@Transactional
	@Override
	public boolean checkAttempt(MultiplicationResultAttempt attempt) {
		Optional<User> user = this.userRepository.findByAlias(attempt.getUser().getAlias());

		Assert.isTrue(!attempt.isCorrect(), "You can't send an attempt marked as correct!");

		boolean isCorrect = attempt.getResultAttempt() == attempt.getMultiplication().getFactorA()
				* attempt.getMultiplication().getFactorB();

		MultiplicationResultAttempt checkedAttempt = new MultiplicationResultAttempt(user.orElse(attempt.getUser()),
				attempt.getMultiplication(), attempt.getResultAttempt(), isCorrect);

		this.attemptRepository.save(checkedAttempt);

		// Communicates the result via Event
		eventDispatcher.send(new MultiplicationSolvedEvent(checkedAttempt.getId(), 
				checkedAttempt.getUser().getId(),
				checkedAttempt.isCorrect()));

		return isCorrect;
	}

	@Override
	public Multiplication createRandomMultiplication() {
		int factorA = randomGeneratorService.generateRandomFactor();
		int factorB = randomGeneratorService.generateRandomFactor();

		return new Multiplication(factorA, factorB);
	}

	@Override
	public List<MultiplicationResultAttempt> getStatsForUser(String userAlias) {
		return this.attemptRepository.findTop5ByUserAliasOrderByIdDesc(userAlias);
	}

	@Override
	public MultiplicationResultAttempt getResultById(Long resultId) {
		return attemptRepository.findById(resultId).orElseThrow(
				() -> new IllegalArgumentException(
						"The requested resultId [" + resultId + "] does not exist."));
	}

}
