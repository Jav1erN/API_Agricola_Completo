package com.example.agroapi.controller;

import com.example.agroapi.entity.Cultivo;
import com.example.agroapi.service.CultivoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cultivos")
public class CultivoController {

    @Autowired
    private CultivoService cultivoService;

    @GetMapping
    public List<Cultivo> listarTodos() {
        return cultivoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cultivo> obtenerPorId(@PathVariable Long id) {
        Cultivo cultivo = cultivoService.buscarPorId(id);
        return cultivo != null ? ResponseEntity.ok(cultivo) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Cultivo> crear(@Valid @RequestBody Cultivo cultivo) {
        return ResponseEntity.ok(cultivoService.guardar(cultivo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cultivo> actualizar(@PathVariable Long id, @Valid @RequestBody Cultivo cultivo) {
        return cultivoService.actualizar(id, cultivo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        cultivoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminarTodos() {
        cultivoService.eliminarTodos();
        return ResponseEntity.noContent().build();
    }
}
