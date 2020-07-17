package hello

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Application {

	private val log = LoggerFactory.getLogger(Application::class.java)

	@Bean
	fun init(repository: CustomerRepository) = CommandLineRunner {
			// save a couple of customers
			repository.save(Customer(12,"Jack", "Bauer"))
			repository.save(Customer(13,"Chloe", "O'Brian"))
			repository.save(Customer(12,"Kim", "Bauer"))
			repository.save(Customer(15,"David", "Palmer"))
			repository.save(Customer(20,"Michelle", "Dessler"))

			// fetch all customers
			log.info("Customers found with findAll():")
			log.info("-------------------------------")
			repository.findAll().forEach { log.info(it.toString()) }
			log.info("")

			// fetch an individual customer by ID
			val customer = repository.findById(1L)
			customer.ifPresent {
				log.info("Customer found with findById(1L):")
				log.info("--------------------------------")
				log.info(it.toString())
				log.info("")
			}

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):")
			log.info("--------------------------------------------")
			repository.findByLastName("Bauer").forEach { log.info(it.toString()) }
			log.info("")
	}

}

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}
