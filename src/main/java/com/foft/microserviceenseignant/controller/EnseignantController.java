package com.foft.microserviceenseignant.controller;



import java.util.Optional;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.foft.microserviceenseignant.repository.EnseignantRepository;

import com.foft.microserviceenseignant.exception.EnseignantNotFoundException;
import com.foft.microserviceenseignant.modele.Enseignant;
import com.foft.microserviceenseignant.service.EnseignantService;

@RestController
public class EnseignantController {


    @Autowired
    private EnseignantRepository enseignantRepository;
    @Autowired
    private JavaMailSender javaMailSender;


    @Autowired
    private EnseignantService enseignantService;

    @GetMapping(value="/delete_enseignant/{id}")
    public String deleteEnseignant(@PathVariable("id") int id)
    {

        enseignantService.deleteEnseignant(id);
        return "redirect:/ListEnseignant";
    }

    

    @PostMapping(value="/create_enseignant")
    public String  CreatEnseignant(@RequestParam("file") MultipartFile file,
                                @RequestParam("nom") String nom,
                                @RequestParam("mail") String mail,
                                @RequestParam("password") String password) {
        enseignantService.saveEnseignantToDB(file, nom, mail, password);
                                            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                                    simpleMailMessage.setTo(mail);
                                    simpleMailMessage.setSubject("Registration in Foft Application");
                                    simpleMailMessage.setText("Ceci est un email envoyé par l'application Foft_Application. Veuillez trouver ci-dessous les informations de connexion pour votre compte :\n - Nom d'utilisateur :"+nom+ "\n - Mot de passe : " +password+" \n  N'hésitez pas à nous contacter si vous avez des questions ou des préoccupations. Nous sommes là pour vous aider. \n Cordialement, \n L'équipe Foft_Application");
                                    javaMailSender.send(simpleMailMessage);
        // la page en question return "redirect:/listProducts.html";
        //  return "yoyo";
        return "redirect:/Enseignant";
    }



    @PostMapping(value="/ChangeEnseignant/{id}")
    public String changeDelegueAll(@PathVariable("id") int id,
                            @RequestParam("file") MultipartFile file,
                            @RequestParam("nom") String nom,
                            @RequestParam("mail") String mail,
                            @RequestParam("matricule") String matricule,
                            @RequestParam("id_classe") int id_classe,
                            @RequestParam("password") String password) {
        enseignantService.updateinformation(id,file, nom, mail, matricule, password,id_classe);
        // la page en question return "redirect:/listProducts.html";
        return "redirect:/Enseignant";
    }

    @GetMapping(value="/Enseignant")
    public Iterable<Enseignant> getEnseignants() {
        return enseignantService.getEnseignants();
    }




    @GetMapping(value="/Enseignant/{id}")
    public Optional<Enseignant> getEnseignant(@PathVariable("id")  Integer id ){
        Optional<Enseignant> enseignant = enseignantRepository.findById(id);

    if(!enseignant.isPresent())  throw new EnseignantNotFoundException("L'enseignant correspondant à l'id " + id + " n'existe pas");

    return enseignant;




}
}
