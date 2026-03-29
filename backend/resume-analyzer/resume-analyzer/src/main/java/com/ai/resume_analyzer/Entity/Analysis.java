package com.ai.resume_analyzer.Entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Analysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String resumeName;

    @Column(length = 5000)
    private String resumeText;

    @Column(length = 5000)
    private String jdText;

    @Column(length = 2000)
    private String result;

    private Double matchScore;

    private LocalDateTime createdAt;

    @ManyToOne
    private User user;
}