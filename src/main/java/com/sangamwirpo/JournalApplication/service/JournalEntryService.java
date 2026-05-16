package com.sangamwirpo.JournalApplication.service;

import com.sangamwirpo.JournalApplication.Entity.JournalEntry;
import com.sangamwirpo.JournalApplication.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    private Logger log;

//    public JournalEntry saveEntry(JournalEntry journalEntry){
//        if (journalEntry.getDate() == null) {
////            journalEntry.setDate(new Date());
//            journalEntry.setDate(LocalDateTime.now());
//
//        }
//        return journalEntryRepository.save(journalEntry);
//    }

public void saveEntry(JournalEntry journalEntry){
    try{
            journalEntry.setDate(LocalDateTime.now());
            journalEntryRepository.save(journalEntry);
        }
    catch(Exception e) {
        log.error("Exception ", e);
    }    
    
}

    public List<JournalEntry> getAllEntries() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getEntryById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    public void deleteEntryById(ObjectId id) {
        journalEntryRepository.deleteById(id);
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }
//

//    public Optional<JournalEntry> findById(ObjectId id){
//        return journalEntryRepository.findById(id);
//
//    }


}












//controller --->  service -----> repository