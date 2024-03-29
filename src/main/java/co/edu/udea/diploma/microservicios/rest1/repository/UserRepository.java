package co.edu.udea.diploma.microservicios.rest1.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.udea.diploma.microservicios.rest1.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByAlias(final String alias);
}
