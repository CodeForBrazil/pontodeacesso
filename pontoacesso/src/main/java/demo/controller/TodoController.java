package demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import demo.model.Todo;
import demo.repository.TodoRepository;

@Controller
@RequestMapping("/todos")
public class TodoController {

	@Autowired
	private TodoRepository todoRepository;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<?> persons() {
		Iterable<Todo> todos = todoRepository.findAll();
		return new ResponseEntity<Iterable<Todo>>(todos, HttpStatus.OK);
	}

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody Todo todo) {
		Todo t = todoRepository.save(todo);
		return new ResponseEntity<Todo>(t, HttpStatus.OK);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity<?> delete(@RequestBody Long id) {
		todoRepository.delete(id);
		return new ResponseEntity<Todo>(HttpStatus.OK);
	}
}
