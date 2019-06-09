package co.edu.udea.diploma.microservicios.rest1.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.udea.diploma.microservicios.rest1.domain.MultiplicationResultAttempt;

public interface MultiplicationResultAttemptRepository extends CrudRepository<MultiplicationResultAttempt, Long> {

	List<MultiplicationResultAttempt> findTop5ByUserAliasOrderByIdDesc(String userAlias);
}
