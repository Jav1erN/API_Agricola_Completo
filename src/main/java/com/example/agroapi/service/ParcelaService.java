package com.example.agroapi.service;

import com.example.agroapi.entity.Parcela;
import com.example.agroapi.repository.ParcelaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParcelaService {

    @Autowired
    private ParcelaRepository parcelaRepository;

    public List<Parcela> listar() {
        return parcelaRepository.findAll();
    }

    public Parcela guardar(Parcela parcela) {
        return parcelaRepository.save(parcela);
    }

    public ResponseEntity<Parcela> obtener(Long id) {
        Optional<Parcela> parcela = parcelaRepository.findById(id);
        return parcela.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Parcela> actualizar(Long id, Parcela datos) {
        Optional<Parcela> opcional = parcelaRepository.findById(id);
        if (opcional.isPresent()) {
            Parcela parcela = opcional.get();
            parcela.setNombre(datos.getNombre());
            parcela.setUbicacion(datos.getUbicacion());
            parcela.setArea(datos.getArea());
            parcela.setCultivo(datos.getCultivo());
            return ResponseEntity.ok(parcelaRepository.save(parcela));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public void eliminar(Long id) {
        parcelaRepository.deleteById(id);
    }

    public void eliminarTodas() {
        parcelaRepository.deleteAll();
    }
}
