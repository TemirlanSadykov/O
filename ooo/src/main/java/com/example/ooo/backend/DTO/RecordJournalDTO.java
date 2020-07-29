package com.example.ooo.backend.DTO;

import com.example.ooo.backend.model.RecordJournal;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RecordJournalDTO {

    private Long id;
    private UserDTO user;
    private String name;
    private String description;
    private Date timeNow;
    private Date time;
    private boolean status;

    public static RecordJournalDTO from(RecordJournal recordJournal){
        return builder()
                .id(recordJournal.getId())
                .name(recordJournal.getName())
                .user(UserDTO.from(recordJournal.getUser()))
                .description(recordJournal.getDescription())
                .timeNow(recordJournal.getTimeNow())
                .time(recordJournal.getTime())
                .status(recordJournal.isStatus())
                .build();
    }
}
