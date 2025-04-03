
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


    public Trabajador(String nombre, String apellidos, int edad, String correo, String contraseña, double salario, int añosExpe) {
        super(nombre, apellidos, edad, correo, contraseña);
        this.salario = salario;
        this.añosExpe = añosExpe;
    }

    public Trabajador(double salario, int añosExpe) {
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
