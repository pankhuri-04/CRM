package com.example.crm.controller;

import com.example.crm.model.Lead;
import com.example.crm.repository.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leads")
public class LeadController {

    @Autowired
    private LeadRepository repo;

    @GetMapping
    public List<Lead> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Lead create(@RequestBody Lead lead) {
        return repo.save(lead);
    }
}
