package hello

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.*
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForObject
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT)
class ApplicationTests(@Autowired private val restTemplate: TestRestTemplate,
					   @Autowired private val customerRepository: CustomerRepository) {

	@Test
	fun findAll() {
		val content = """[{"age":12,"firstName":"Jack","lastName":"Bauer","id":1},{"age":13,"firstName":"Chloe","lastName":"O'Brian","id":2},{"age":12,"firstName":"Kim","lastName":"Bauer","id":3},{"age":15,"firstName":"David","lastName":"Palmer","id":4},{"age":20,"firstName":"Michelle","lastName":"Dessler","id":5}]"""
		assertEquals(content, restTemplate.getForObject<String>("/customers"))
	}

	@Test
	fun findByAge12(){
		assertTrue(customerRepository.findByAge(12).toList().isNotEmpty(), "Custom exists")
	}

}
