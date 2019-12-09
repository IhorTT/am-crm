package net.ukr.tigor.repos;

import net.ukr.tigor.domain.Event;
import net.ukr.tigor.domain.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface EventRepo extends PagingAndSortingRepository<Event, Long>, JpaSpecificationExecutor<Event> {
    @Query("SELECT max (e.number) from Event e")
    Integer getMuxNumber();

    Integer countAllByManager(@Param("user") User user);

}
