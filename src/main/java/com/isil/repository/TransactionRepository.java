package com.isil.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.isil.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
    @Query("SELECT t FROM Transaction t WHERE (t.senderAccount IS NOT NULL AND t.senderAccount.id = :accountId) OR (t.receiverAccount IS NOT NULL AND t.receiverAccount.id = :accountId)")
    List<Transaction> findByAccountId(@Param("accountId") Long accountId);
}
