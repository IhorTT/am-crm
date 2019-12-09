package net.ukr.tigor.repos;

import net.ukr.tigor.domain.ContactPerson;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactPersonRepo extends PagingAndSortingRepository<ContactPerson, Long> {
    List<ContactPerson> findByPositionId(@Param("positionId") Long id);

    List<ContactPerson> findByEmailContainsOrPhoneContainsOrFullNameContainsOrderByFullName(String email, String phone, String fulName);
}
