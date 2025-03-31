package com.rewards.rewards.program.repository;

import com.rewards.rewards.program.model.UserTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserTransactionsRepository extends JpaRepository<UserTransaction, Integer> {
    // Custom query to get total amount month-wise
    @Query(
            value = "SELECT SUM(purchase_amount) FROM user_transaction " +
                    "WHERE cust_id = :custId AND MONTH(purchase_date) = :month",
            nativeQuery = true
    )
    Double getTotalAmountByMonth(@Param("custId") String custId, @Param("month") int month);

    // Custom query to get the total amount for a user (without month)
    @Query("SELECT SUM(u.purchaseAmount) FROM UserTransaction u WHERE u.custId = :custId")
    Double getTotalAmountByUser(@Param("custId") String custId);

}
