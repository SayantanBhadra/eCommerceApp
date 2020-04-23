package org.sayantan.AccountService.dao;

import org.sayantan.AccountService.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserDAO extends JpaRepository<User, Long> {

}
