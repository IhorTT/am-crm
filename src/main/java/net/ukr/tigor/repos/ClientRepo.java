package net.ukr.tigor.repos;

import net.ukr.tigor.domain.Client;
import net.ukr.tigor.domain.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ClientRepo extends PagingAndSortingRepository<Client, Long> {

    List<Client> findByNameContainsOrEdpnouContainsOrItnContainsOrderByName(String name, String ednpou, String itn);

    List<Client> findByNameContainsOrEdpnouContainsOrItnContainsAndManagerNullAndManagerEqualsOrderByName(String name, String ednpou, String itn, User user);

    List<Client> findByManagerNullOrManagerEquals(User user, Sort sort);

    Integer countAllByManager(User user);
}
