package com.springboot.expenseapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "expense_name", nullable = false)
    @NotBlank(message = "Expense name must not be empty")
    @Size(min = 3, message = "Expense name must be at least 3 characters")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "Expense description must not be empty")
    @Size(min = 3, message = "Expense description must be at least 3 characters")
    private String description;

    @Column(name = "expense_amount", nullable = false)
    @NotNull(message = "Expense amount must not be null")
    private BigDecimal amount;

    @Column(nullable = false)
    @NotBlank(message = "Expense category must not be empty")
    @Size(min = 3, message = "Expense category must be at least 3 characters")
    private String category;

    @Column(nullable = false)
    private Date date;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;
}
