package com.springboot.expenseapi.repository;

import com.springboot.expenseapi.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    // select * from tbl_expenses where category = ?
    Page<Expense> findByCategory(String category, Pageable pageable);

    // select * from tbl_expenses where user_id = ?
    Page<Expense> findByUserId(Long id, Pageable pageable);

    // select * from tbl_expenses where user_id = ? and id = ?
    Optional<Expense> findByUserIdAndId(Long userId, Long expenseId);
}
