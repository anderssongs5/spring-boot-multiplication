package co.edu.udea.diploma.microservicios.rest1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.udea.diploma.microservicios.rest1.domain.Multiplication;
import co.edu.udea.diploma.microservicios.rest1.service.MultiplicacionService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/multiplications")
public final class MultiplicationController {

	private MultiplicacionService multiplicacionService;

	private final int serverPort;

	@Autowired
	public MultiplicationController(final MultiplicacionService multiplicacionService,
			@Value("${server.port}") int serverPort) {
		this.multiplicacionService = multiplicacionService;
		this.serverPort = serverPort;
	}

	@GetMapping(value = "/random")
	public Multiplication getRandomMultiplication() {
		log.info("Generating a random multiplication from server @ {}", serverPort);

		return multiplicacionService.createRandomMultiplication();
	}

}
