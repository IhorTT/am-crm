package net.ukr.tigor.repos;

import net.ukr.tigor.domain.Position;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PositionRepo extends PagingAndSortingRepository<Position, Long> {

}
