package com.example.clothshop.service;

import com.example.clothshop.model.Status;
import com.example.clothshop.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusService {
    @Autowired
    private StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public List<Status> getAllStatus() {
        return statusRepository.findAll();
    }

    public void saveStatus(Status status) {
        this.statusRepository.save(status);
    }

    public Status getStatusById(int id) {
        Optional<Status> optionalStatus = statusRepository.findById(id);
        Status status = null;
        if (optionalStatus.isPresent()) {
            status = optionalStatus.get();
        } else {
            throw new RuntimeException("User not found for id : " + id);
        }
        return status;
    }

    public void deleteStatusById(int id) {
        this.statusRepository.deleteById(id);
    }
}
