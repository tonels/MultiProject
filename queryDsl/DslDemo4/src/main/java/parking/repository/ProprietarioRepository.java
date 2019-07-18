package parking.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import parking.domain.Proprietario;

/**
 * 
 * @author gustavojotz
 *
 */
public interface ProprietarioRepository
		extends PagingAndSortingRepository<Proprietario, Long>, QuerydslPredicateExecutor<Proprietario> {

	Proprietario findByUsuarioIgnoreCase(final String usuario);

}
