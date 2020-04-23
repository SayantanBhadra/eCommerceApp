package org.sayantan.AccountService.dao;

import org.sayantan.AccountService.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PaymentDAO extends JpaRepository<Payment, Long> {

}
