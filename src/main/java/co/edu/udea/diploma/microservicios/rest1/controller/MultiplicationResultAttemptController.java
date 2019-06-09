package co.edu.udea.diploma.microservicios.rest1.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.udea.diploma.microservicios.rest1.domain.MultiplicationResultAttempt;
import co.edu.udea.diploma.microservicios.rest1.service.MultiplicacionService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/results")
public final class MultiplicationResultAttemptController {

	private MultiplicacionService multiplicacionService;

	public MultiplicationResultAttemptController(final MultiplicacionService multiplicacionService) {
		this.multiplicacionService = multiplicacionService;
	}

	@PostMapping
	public ResponseEntity<MultiplicationResultAttempt> postResult(@RequestBody MultiplicationResultAttempt attempt) {
		boolean isCorrect = this.multiplicacionService.checkAttempt(attempt);
		MultiplicationResultAttempt a = new MultiplicationResultAttempt(attempt.getUser(), attempt.getMultiplication(),
				attempt.getResultAttempt(), isCorrect);

		return ResponseEntity.ok(a);
	}

	@GetMapping
	ResponseEntity<List<MultiplicationResultAttempt>> getStatistics(@RequestParam("alias") String alias) {
		return ResponseEntity.ok(this.multiplicacionService.getStatsForUser(alias));
	}

	@GetMapping(value = "/{resultId}")
	ResponseEntity<MultiplicationResultAttempt> getById(@PathVariable("resultId") Long resultId) {
		return ResponseEntity.ok(this.multiplicacionService.getResultById(resultId));
	}

	@RequiredArgsConstructor
	@NoArgsConstructor(force = true)
	@Getter
	static final class ResultResponse {
		private final boolean correct;
	}
}
