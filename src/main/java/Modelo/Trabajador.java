/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author MI PC
 */
public class Trabajador extends Usuario {
    //Atributos
    private double salario;
    private int añosExpe;
    
    //Constructor
    public Trabajador(double salario, int añosExpe, String nombre, String apellidos, int edad, String correo, int id, String contraseña) {
        super(nombre, apellidos, edad, correo, id, contraseña);
        this.salario = salario;
        this.añosExpe = añosExpe;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getAñosExpe() {
        return añosExpe;
    }

    public void setAñosExpe(int añosExpe) {
        this.añosExpe = añosExpe;
    }

    @Override
    public String toString() {
        return "Trabajador{" +
                "salario=" + salario +
                ", añosExpe=" + añosExpe +
                '}';
    }
}
