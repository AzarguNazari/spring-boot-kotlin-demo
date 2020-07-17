package hello

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
@RestController
class CustomerController(private val repository: CustomerRepository) {

	@GetMapping("/customers")
	fun findAll(@RequestParam(required = false, defaultValue = "-1") age: Int){
		if(age == -1) repository.findAll()
		else repository.findByAge(age)
	}


	@GetMapping("/customers/{lastName}")
	fun findByLastName(@PathVariable lastName:String)
			= repository.findByLastName(lastName)
}

