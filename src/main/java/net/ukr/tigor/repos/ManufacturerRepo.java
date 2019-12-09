package net.ukr.tigor.repos;

import net.ukr.tigor.domain.Manufacturer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ManufacturerRepo extends PagingAndSortingRepository<Manufacturer, Long> {

}
