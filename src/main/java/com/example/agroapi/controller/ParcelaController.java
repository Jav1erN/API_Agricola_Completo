package com.example.agroapi.controller;

import com.example.agroapi.entity.Parcela;
import com.example.agroapi.service.ParcelaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parcelas")
public class ParcelaController {

    @Autowired
    private ParcelaService parcelaService;

    @GetMapping
    public List<Parcela> listar() {
        return parcelaService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parcela> obtener(@PathVariable Long id) {
        return parcelaService.obtener(id);
    }

    @PostMapping
    public ResponseEntity<Parcela> crear(@Valid @RequestBody Parcela parcela) {
        return ResponseEntity.ok(parcelaService.guardar(parcela));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Parcela> actualizar(@PathVariable Long id, @Valid @RequestBody Parcela parcela) {
        return parcelaService.actualizar(id, parcela);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        parcelaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminarTodas() {
        parcelaService.eliminarTodas();
        return ResponseEntity.noContent().build();
    }
}
