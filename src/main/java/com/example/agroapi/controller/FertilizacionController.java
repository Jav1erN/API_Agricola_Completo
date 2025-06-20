package com.example.agroapi.controller;

import com.example.agroapi.entity.Fertilizacion;
import com.example.agroapi.service.FertilizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fertilizaciones")
public class FertilizacionController {

    @Autowired
    private FertilizacionService fertilizacionService;

    @GetMapping
    public List<Fertilizacion> listar() {
        return fertilizacionService.listarTodos();
    }

    @PostMapping
    public Fertilizacion crear(@RequestBody Fertilizacion fertilizacion) {
        return fertilizacionService.guardar(fertilizacion);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        fertilizacionService.eliminar(id);
    }
}
