package com.foft.microserviceenseignant.service;

import com.foft.microserviceenseignant.modele.Enseignant;
import com.foft.microserviceenseignant.repository.EnseignantRepository;
import lombok.Data;
import org.bouncycastle.crypto.ec.ECNewRandomnessTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class EnseignantService {
    @Autowired
    EnseignantRepository enseignantRepository;
    public List<Enseignant> findAll(){
        return enseignantRepository.findAll();    }
}
