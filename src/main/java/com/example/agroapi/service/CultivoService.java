package com.example.agroapi.service;

import com.example.agroapi.entity.Cultivo;
import com.example.agroapi.repository.CultivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CultivoService {

    @Autowired
    private CultivoRepository cultivoRepository;

    public List<Cultivo> listarTodos() {
        return cultivoRepository.findAll();
    }

    public Cultivo buscarPorId(Long id) {
        return cultivoRepository.findById(id).orElse(null);
    }

    public Cultivo guardar(Cultivo cultivo) {
        return cultivoRepository.save(cultivo);
    }

    public ResponseEntity<Cultivo> actualizar(Long id, Cultivo cultivoNuevo) {
        Optional<Cultivo> opcional = cultivoRepository.findById(id);
        if (opcional.isPresent()) {
            Cultivo cultivo = opcional.get();
            cultivo.setNombre(cultivoNuevo.getNombre());
            cultivo.setTipo(cultivoNuevo.getTipo());
            cultivo.setFechaSiembra(cultivoNuevo.getFechaSiembra());
            return ResponseEntity.ok(cultivoRepository.save(cultivo));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public void eliminar(Long id) {
        cultivoRepository.deleteById(id);
    }

    public void eliminarTodos() {
        cultivoRepository.deleteAll();
    }
}
