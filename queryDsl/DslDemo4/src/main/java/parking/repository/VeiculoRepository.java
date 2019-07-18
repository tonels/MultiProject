package parking.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import parking.domain.Veiculo;

/**
 * 
 * @author gustavojotz
 *
 */
public interface VeiculoRepository
		extends PagingAndSortingRepository<Veiculo, Long>, QuerydslPredicateExecutor<Veiculo> {

}
