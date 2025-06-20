package com.example.agroapi.controller;

import com.example.agroapi.entity.Riego;
import com.example.agroapi.service.RiegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/riegos")
public class RiegoController {

    @Autowired
    private RiegoService riegoService;

    @GetMapping
    public List<Riego> listar() {
        return riegoService.listarTodos();
    }

    @PostMapping
    public Riego crear(@RequestBody Riego riego) {
        return riegoService.guardar(riego);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        riegoService.eliminar(id);
    }
}
