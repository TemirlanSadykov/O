package com.example.ooo.frontend.forms;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class RecordJournalForm {

    @NotBlank(message = "Обязательное поле")
    private String name;

    @NotBlank(message = "Обязательное поле")
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Обязательное поле")
    @Future(message = "Нельзя создать запись в прошлое)")
    private Date time;

    private String user;
}
