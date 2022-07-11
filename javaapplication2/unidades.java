/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication2;

import java.util.Date;

/**
 *
 * @author esdra
 */
public class unidades {

    int idbus, modelo, activo, tipo;
    String marca, placa;
    Date fecha_ingreso;

    public unidades() {
    }

    public unidades(int idbus, int modelo, int activo, int tipo, String marca, String placa, Date fecha_ingreso) {
        this.idbus = idbus;
        this.modelo = modelo;
        this.activo = activo;
        this.tipo = tipo;
        this.marca = marca;
        this.placa = placa;
        this.fecha_ingreso = fecha_ingreso;
    }

    public int getIdbus() {
        return idbus;
    }

    public void setIdbus(int idbus) {
        this.idbus = idbus;
    }

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

}
