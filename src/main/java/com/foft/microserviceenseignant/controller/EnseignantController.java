package com.foft.microserviceenseignant.controller;

import com.foft.microserviceenseignant.modele.Enseignant;
import com.foft.microserviceenseignant.service.EnseignantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("MicroEnseignant")
public class EnseignantController {

    @Autowired
    private EnseignantService enseignantService;
    @GetMapping("/ListEnseignant")
    public List<Enseignant> allEnseigant()
    {
        return enseignantService.findAll();
    }
}
