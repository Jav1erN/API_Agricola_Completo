package com.example.agroapi.service;

import com.example.agroapi.entity.Fertilizacion;
import com.example.agroapi.repository.FertilizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FertilizacionService {

    @Autowired
    private FertilizacionRepository fertilizacionRepository;

    public List<Fertilizacion> listarTodos() {
        return fertilizacionRepository.findAll();
    }

    public Fertilizacion guardar(Fertilizacion fertilizacion) {
        // Validación opcional si parcela es null
        if (fertilizacion.getParcela() == null || fertilizacion.getParcela().getId() == null) {
            throw new IllegalArgumentException("Parcela no puede ser nula");
        }
        return fertilizacionRepository.save(fertilizacion);
    }

    public void eliminar(Long id) {
        fertilizacionRepository.deleteById(id);
    }
}
