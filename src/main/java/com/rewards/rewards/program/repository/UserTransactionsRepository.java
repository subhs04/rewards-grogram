package com.rewards.rewards.program.repository;

import com.rewards.rewards.program.model.UserTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserTransactionsRepository extends JpaRepository<UserTransaction, Integer> {
    /**
     *  query to fetch total amount for a month for a customer
     * @param custId
     * @param month
     * @return
     */
    @Query(value = "SELECT SUM(purchase_amount) FROM user_transaction " +
                    "WHERE cust_id = :custId AND MONTH(purchase_date) = :month",nativeQuery = true
    )
    Double getTotalAmountByMonth(@Param("custId") String custId, @Param("month") int month);

    /**
     * Query to find total purchase amount for the customer over the entire period
     * @param custId
     * @return
     */
    @Query(value = "SELECT SUM(purchase_amount) FROM user_transaction WHERE cust_id = :custId",nativeQuery = true)
    Double getTotalAmountByUser(@Param("custId") String custId);

}
