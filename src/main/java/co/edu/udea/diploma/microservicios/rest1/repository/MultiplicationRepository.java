package co.edu.udea.diploma.microservicios.rest1.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.udea.diploma.microservicios.rest1.domain.Multiplication;

public interface MultiplicationRepository extends CrudRepository<Multiplication, Long> {

}
