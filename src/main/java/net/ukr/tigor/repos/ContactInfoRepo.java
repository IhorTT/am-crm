package net.ukr.tigor.repos;

import net.ukr.tigor.domain.ContactInformation;
import org.springframework.data.repository.CrudRepository;

public interface ContactInfoRepo extends CrudRepository<ContactInformation, Long> {

}
