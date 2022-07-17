/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author esdra
 */
public class tipoUnidades {
    
    int idtipo, puertas;
    String descripcion;

    public tipoUnidades(int idtipo, int puertas, String descripcion) {
        this.idtipo = idtipo;
        this.puertas = puertas;
        this.descripcion = descripcion;
    }
    public tipoUnidades() {
    }

    
    public int getIdtipo() {
        return idtipo;
    }

    public void setIdtipo(int idtipo) {
        this.idtipo = idtipo;
    }

    public int getPuertas() {
        return puertas;
    }

    public void setPuertas(int puertas) {
        this.puertas = puertas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
