package com.example.ooo.backend.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "record_journal")
public class RecordJournal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "user_id")
    private User user;

    @NotBlank(message = "Обязательное поле")
    @Column(length = 64)
    private String name;

    @NotBlank(message = "Обязательное поле")
    @Column(length = 64)
    private String description;

    @NotNull(message = "Обязательное поле")
    @Column
    private Date timeNow;

    @NotNull(message = "Обязательное поле")
    @Column
    private Date time;

    private boolean status;
}
