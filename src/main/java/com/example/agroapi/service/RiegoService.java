package com.example.agroapi.service;

import com.example.agroapi.entity.Riego;
import com.example.agroapi.repository.RiegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiegoService {

    @Autowired
    private RiegoRepository riegoRepository;

    public List<Riego> listarTodos() {
        return riegoRepository.findAll();
    }

    public Riego guardar(Riego riego) {
        return riegoRepository.save(riego);
    }

    public void eliminar(Long id) {
        riegoRepository.deleteById(id);
    }
}
