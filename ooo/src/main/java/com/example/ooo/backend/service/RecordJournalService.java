package com.example.ooo.backend.service;

import com.example.ooo.backend.DTO.RecordJournalDTO;
import com.example.ooo.backend.model.RecordJournal;
import com.example.ooo.backend.model.User;
import com.example.ooo.backend.repository.RecordJournalRepo;
import com.example.ooo.backend.repository.UserRepo;
import com.example.ooo.frontend.forms.RecordJournalForm;
import com.example.ooo.frontend.forms.UserLoginForm;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class RecordJournalService {
    private final RecordJournalRepo recordJournalRepo;
    private final UserRepo userRepo;
    private final PasswordEncoder encoder;

    public Page<RecordJournalDTO> getRecordJournal(Pageable pageable, Principal principal){
        return recordJournalRepo.findAllByUserOrderByTimeAsc(pageable, userRepo.findByLogin(principal.getName()).get()).map(RecordJournalDTO :: from);
    }

    public void deleteById(Long id){
        recordJournalRepo.deleteAllById(id);
    }

    public void successById(Long id){
        RecordJournal recordJournal = recordJournalRepo.findById(id).get();
        recordJournal.setStatus(true);
        recordJournalRepo.save(recordJournal);
    }

    public void createRecord(RecordJournalForm recordJournalForm){
        Date date = new Date();
        RecordJournal recordJournal = RecordJournal.builder()
                .user(userRepo.findByLogin(recordJournalForm.getUser()).get())
                .name(recordJournalForm.getName())
                .description(recordJournalForm.getDescription())
                .time(recordJournalForm.getTime())
                .timeNow(date)
                .status(false)
                .build();
        recordJournalRepo.save(recordJournal);
    }

    public void editRecord(RecordJournalForm recordJournalForm, Long id){
        RecordJournal recordJournal = recordJournalRepo.findById(id).get();
        recordJournal.setName(recordJournalForm.getName());
        recordJournal.setDescription(recordJournalForm.getDescription());
        recordJournal.setTime(recordJournalForm.getTime());

        recordJournalRepo.save(recordJournal);
    }

    public RecordJournalDTO getRecord(Long id){
        if (recordJournalRepo.findById(id).isPresent()){
            return RecordJournalDTO.from(recordJournalRepo.findById(id).get());
        }
        else {
            return null;
        }
    }

}
