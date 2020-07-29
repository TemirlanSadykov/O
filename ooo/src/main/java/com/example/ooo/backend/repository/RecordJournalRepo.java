package com.example.ooo.backend.repository;

import com.example.ooo.backend.model.RecordJournal;
import com.example.ooo.backend.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface RecordJournalRepo extends JpaRepository<RecordJournal, Long> {
    Page<RecordJournal> findAllByUserOrderByTimeAsc(Pageable pageable, User user);
    @Transactional
    void deleteAllById(Long id);
}
