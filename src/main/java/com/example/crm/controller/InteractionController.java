package com.example.crm.controller;

import com.example.crm.model.Interaction;
import com.example.crm.repository.InteractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interactions")
public class InteractionController {

    @Autowired
    private InteractionRepository repo;

    @GetMapping
    public List<Interaction> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Interaction create(@RequestBody Interaction interaction) {
        return repo.save(interaction);
    }
}
