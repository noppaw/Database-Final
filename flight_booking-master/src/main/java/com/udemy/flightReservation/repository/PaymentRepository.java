package com.udemy.flightReservation.repository;

import com.udemy.flightReservation.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Long> {

    @Query(value = "from Payment where cardNumber=:cardNumber and cardExpire=:cardExpire and cvv=:cvv  ORDER BY id ASC")
    List<Payment> findPayment(@Param("cardNumber") String cardNumber, @Param("cardExpire") String cardExpire, @Param("cvv") String cvv);

}
