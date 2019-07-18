package parking.repository.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import parking.ParkingApplication;
import parking.domain.Proprietario;
import parking.repository.ProprietarioRepository;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 
 * @author gustavojotz
 *
 */
@RunWith(SpringRunner.class)
//@DataJpaTest
//@Sql("classpath:data-test.sql")
@SpringBootTest(classes = ParkingApplication.class)
public class ProprietarioRepositoryTest {

	@Autowired
	private ProprietarioRepository repository;

	@Test
	public void tt(){
		System.out.println("ss");
	}

	@Test
	public void findByUsuarioRetornaProprietario() {
		Proprietario p = this.repository.findByUsuarioIgnoreCase("gustavojotz");

		assertThat(p.getNome()).isEqualTo("Gustavo Jotz");
		assertThat(p.getUsuario()).isEqualTo("gustavojotz");
	}

}