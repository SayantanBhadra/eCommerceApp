package org.sayantan.AccountService.dao;

import org.sayantan.AccountService.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AddessDAO extends JpaRepository<Address, Long> {

}
