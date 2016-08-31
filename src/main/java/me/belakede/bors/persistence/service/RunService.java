package me.belakede.bors.persistence.service;

import me.belakede.bors.persistence.repository.RunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RunService {

    @Autowired
    private RunRepository repository;



}
