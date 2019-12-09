package net.ukr.tigor.repos;

import net.ukr.tigor.domain.EquipmentPark;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface EquipmentParkRepo extends PagingAndSortingRepository<EquipmentPark,Long> {

    Integer countAllByManufacturerId(@Param("id") Long id);
    Integer countAllByTypeOfAgrMachId(@Param("id") Long id);
}
