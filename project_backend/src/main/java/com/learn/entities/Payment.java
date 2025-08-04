package com.learn.entities;

import java.time.LocalDateTime;

import com.learn.enums.PaymentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payment") 
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Payment 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    
    @Enumerated(EnumType.STRING)
    private PaymentStatus status; // PENDING, SUCCESS, FAILED
    
    private String transactionId;

    @ManyToOne
    @JoinColumn(name = "learner_id", nullable = false, unique = true)
    private Learner learner;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false, unique = true)
    private Course course;
    
    @Column(name = "time_stamp")
    private LocalDateTime timestamp;
}
