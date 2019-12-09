package net.ukr.tigor.repos;

import net.ukr.tigor.domain.RequirementPark;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface RequirementParkRepo extends PagingAndSortingRepository<RequirementPark,Long> {
    Integer countAllByManufacturerId(@Param("id") Long id);
    Integer countAllByTypeOfAgrMachId(@Param("id") Long id);
}
