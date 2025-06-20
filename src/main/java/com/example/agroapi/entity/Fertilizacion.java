package com.example.agroapi.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Fertilizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;
    private String tipoFertilizante;
    private Double dosis;

    @ManyToOne
    @JoinColumn(name = "parcela_id")
    private Parcela parcela;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public String getTipoFertilizante() { return tipoFertilizante; }
    public void setTipoFertilizante(String tipoFertilizante) { this.tipoFertilizante = tipoFertilizante; }

    public Double getDosis() { return dosis; }
    public void setDosis(Double dosis) { this.dosis = dosis; }

    public Parcela getParcela() { return parcela; }
    public void setParcela(Parcela parcela) { this.parcela = parcela; }
}
