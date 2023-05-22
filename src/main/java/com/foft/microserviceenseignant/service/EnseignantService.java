package com.foft.microserviceenseignant.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

import com.foft.microserviceenseignant.modele.Enseignant;
import com.foft.microserviceenseignant.repository.EnseignantRepository;

@Data
@Service
public class EnseignantService {


    @Autowired
    private EnseignantRepository enseignantRepository;


    public Optional<Enseignant> getEnseignant(Integer id){

        Optional<Enseignant> enseignant = enseignantRepository.findById(id);
        if(enseignant.isPresent()){
            return enseignant;
        }
        else return null;
    }
    public Iterable<Enseignant> getEnseignants(){
        return enseignantRepository.findAll();
    }

    public Enseignant saveEnseignantToDB(MultipartFile photo, String name, String email,String password)
    {
        Enseignant enseignant = new Enseignant();
        String fileName = StringUtils.cleanPath(photo.getOriginalFilename());
        if(fileName.contains(".."))
        {
            System.out.println("not a a valid file");
        }
        try {
            enseignant.setPhoto(Base64.getEncoder().encodeToString(photo.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        enseignant.setEmail(email);
        enseignant.setPassword(password);

        enseignant.setNom(name);


        enseignantRepository.save(enseignant);
        return enseignant;
    }




    public void deleteEnseignant (int id){
        try {
            enseignantRepository.deleteById(id);
        } catch (Exception e){

        }
    }

public void updateinformation(int id,MultipartFile photo, String name, String email, String matricule,String password,int id_classe)
{
    Enseignant del=new Enseignant();
    del=enseignantRepository.findById(id).get();
    del.setPhoto(String.valueOf(photo));
    del.setNom(name);
    del.setEmail(email);


    enseignantRepository.save(del);


}

}
