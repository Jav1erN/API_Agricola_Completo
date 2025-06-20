package com.example.agroapi.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Riego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;
    private Double cantidadAgua;

    @ManyToOne
    @JoinColumn(name = "parcela_id")
    private Parcela parcela;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public Double getCantidadAgua() { return cantidadAgua; }
    public void setCantidadAgua(Double cantidadAgua) { this.cantidadAgua = cantidadAgua; }

    public Parcela getParcela() { return parcela; }
    public void setParcela(Parcela parcela) { this.parcela = parcela; }
}
