package person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller    
@RequestMapping(path="/people") 
public class MainController {
	
	@Autowired
	private PersonRepository personRepository;
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public @ResponseBody Person getPerson(@PathVariable Long id) {
		Person person = personRepository.findOne(id);
		if (person == null)
			throw new PersonNotFoundException(id);
		
		return person;
	}
}
@ResponseStatus(HttpStatus.NOT_FOUND)
class PersonNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;

	public PersonNotFoundException(long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}
	public String getMessage() {
		return "Person [" + id + "] not found";
	}	
}
