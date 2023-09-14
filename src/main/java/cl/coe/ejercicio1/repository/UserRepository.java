package cl.coe.ejercicio1.repository;

import org.springframework.data.repository.CrudRepository;

import cl.coe.ejercicio1.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
