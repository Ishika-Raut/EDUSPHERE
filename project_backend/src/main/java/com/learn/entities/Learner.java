package com.learn.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@Table(name = "learners")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Learner 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull(message = "User is required for a Learner")
    private User user;

    @OneToOne(mappedBy = "learner", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Valid
    private Cart cart;

    @OneToMany(mappedBy = "learner", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Valid
    private List<Payment> payments = new ArrayList<>();

    @OneToMany(mappedBy = "learner", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Valid
    private List<VideoProgress> videoProgress = new ArrayList<>();

    @OneToMany(mappedBy = "learner", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Valid
    private List<Certificate> certificates = new ArrayList<>();

    @OneToMany(mappedBy = "learner", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Valid
    private List<Review> reviews = new ArrayList<>();
}
