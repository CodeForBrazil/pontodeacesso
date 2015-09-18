package demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import demo.model.Todo;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {

}
