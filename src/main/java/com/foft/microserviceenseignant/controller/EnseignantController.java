package com.foft.microserviceenseignant.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.foft.microserviceenseignant.modele.Enseignant;
import com.foft.microserviceenseignant.repository.EnseignantRepository;
import com.foft.microserviceenseignant.service.EnseignantService;



import org.springframework.web.bind.annotation.RestController;






@RestController
public class EnseignantController {


    @Autowired
    private EnseignantRepository enseignantRepository;


    @Autowired
    private EnseignantService enseignantService;




    @GetMapping("/ListEnseignant")
    public List<Enseignant>  allEnseigant(Model model )
    {
        List<Enseignant> ens =enseignantRepository.findAll();
        long ensCount=enseignantRepository.count();
        model.addAttribute("ensCount", ensCount);
        model.addAttribute("ens", ens);
        return ens;
    }



    @GetMapping("/delete_enseignant/{id}")
    public String deleteEnseignant(@PathVariable("id") int id)
    {

        enseignantService.deleteEnseignant(id);
        return "redirect:/ListEnseignant";
    }

    

    @PostMapping("/create_enseignant")
    public String  CreatEnseignant(@RequestParam("file") MultipartFile file,
                                @RequestParam("nom") String nom,
                                @RequestParam("mail") String mail,
                                @RequestParam("password") String password) {
        enseignantService.saveEnseignantToDB(file, nom, mail, password);
        // la page en question return "redirect:/listProducts.html";
        //  return "yoyo";
        return "redirect:/ListEnseignant";
    }



    @PostMapping("/ChangeEnseignant/{id}")
    public String changeDelegueAll(@PathVariable("id") int id,
                            @RequestParam("file") MultipartFile file,
                            @RequestParam("nom") String nom,
                            @RequestParam("mail") String mail,
                            @RequestParam("matricule") String matricule,
                            @RequestParam("id_classe") int id_classe,
                            @RequestParam("password") String password) {
        enseignantService.updateinformation(id,file, nom, mail, matricule, password,id_classe);
        // la page en question return "redirect:/listProducts.html";
        return "redirect:/ListDelegue";
    }

    @GetMapping("/Enseignant")
    public Iterable<Enseignant> getEnseignants() {
        return enseignantService.getEnseignants();
    }
    @GetMapping("/Enseignant/{id}")
    public Enseignant getEnseignant(@PathVariable("id") final Integer id ){
        Optional<Enseignant> enseignant = enseignantService.getEnseignant(id);
        if(enseignant.isPresent()) {
            return enseignant.get();
        } else {
            return null;
        }
    }
}
