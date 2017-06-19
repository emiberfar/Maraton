package com.company.Modelo;

import java.io.Serializable;
import java.util.*;

/**
 * Created by EBF10 on 14/06/2017.
 */

public class Atleta implements Comparable<Atleta>,Comparator<Atleta>,Serializable {


    private static final long serialVersionUID = -2196807447451158L;

    private String nombre;
    private String apellido;
    private String apellido2;
    private int dorsal;
    private String sexo;
    private String nacionalidad;
    private String club;
    private int edad;
    private String categoria;
    private int posicionGeneral;
    private int posicionCategoria;
    private Marca marca = new Marca();

    // constructor


    public Atleta() {
    }

    public Atleta(int dorsal) {
        this.dorsal = dorsal;
    }

    public Atleta(String nombre, String apellido, String apellido2, int dorsal, String sexo, String nacionalidad, String club, int edad) {
        setNombre(nombre);
        setApellido(apellido);
        setApellido2(apellido2);
        setDorsal(dorsal);
        setSexo(sexo);
        setNacionalidad(nacionalidad);
        setClub(club);
        setEdad(edad);
        this.marca.pedirTiempo();
    }

    public Atleta(String nombre, String apellido, String apellido2, int dorsal, String sexo, String nacionalidad, String club, int edad, int horas, int min, int seg) {
        setNombre(nombre);
        setApellido(apellido);
        setApellido2(apellido2);
        setDorsal(dorsal);
        setSexo(sexo);
        setNacionalidad(nacionalidad);
        setClub(club);
        setEdad(edad);
        this.marca = new Marca(horas, min, seg);
    }


    // accesores


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public int getDorsal() {
        return dorsal;
    }


    /**
     * controlo que el valor introducido en el dorsal no sea menor de cero
     *
     * @param dorsal
     */
    public void setDorsal(int dorsal) {
        if (dorsal < 0) {
            this.dorsal = 0;
        } else {
            this.dorsal = dorsal;
        }
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }


    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * comprobamos que no puedaponer mas de tres caracteres en la nacionalidad
     *
     * @param nacionalidad
     */
    public void setNacionalidad(String nacionalidad) {
        if (nacionalidad.length() > 4) {

            this.nacionalidad = " ";

        } else {
            this.nacionalidad = nacionalidad.toUpperCase();
        }
    }

    public String getClub() {
        return club;
    }

    /**
     * controlamos que si no tiene club que ponga que va po libre
     * @param club String
     */
    public void setClub(String club) {

        if (club.equals(" ")){
            this.club = "Corre por su cuenta";
        }else{
            this.club = club;
        }
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
            this.edad = edad;
    }

    public String getCategoria() {
        if (getEdad() < 18) {

            return this.categoria = "no tiene categoria";

        } else if (getEdad() == 18) {

            return this.categoria = "Junior";

        } else if (getEdad() >= 19 && getEdad() <= 21) {

            return this.categoria = "Promesa";

        } else if (getEdad() >= 22 && getEdad() < 35) {

            return this.categoria = "Senior";

        } else if (getEdad() >= 35 && getEdad() <= 45) {

            return this.categoria = "Veterano 1";

        } else if (getEdad() >= 46 && getEdad() <= 55) {

            return this.categoria = "Veterano 2";

        } else if (getEdad() >= 56 && getEdad() <= 65) {

            return this.categoria = "Veterano 3";

        } else {

            return this.categoria = "Super Veterano";

        }
    }


    public Marca getMarca() {
        return marca;
    }

    public int getPosicionGeneral() {
        return posicionGeneral;
    }

    public void setPosicionGeneral(int posicionGeneral) {
        this.posicionGeneral = posicionGeneral;
    }

    public int getPosicionCategoria() {
        return posicionCategoria;
    }

    public void setPosicionCategoria(int posicionCategoria) {
        this.posicionCategoria = posicionCategoria;
    }


    //metodos


    /**
     * nos va a amostrar el atleta
     *
     * @return String
     */
    @Override
    public String toString() {
        return "***********************" + "\n"  +
        "Atleta{" +
                "nombre= " + getNombre() + "\n"  +
                " apellido= " + getApellido() + " " + getApellido2() + " " + '\n' +
                " dorsal= " + getDorsal() + '\n' +
                " sexo= " + getSexo() + '\n' +
                " nacionalidad= " + getNacionalidad() + '\n' +
                " club= " + getClub() + '\n' +
                " edad= " + getEdad() + '\n' +
                " categoria= " + getCategoria() + '\n' +
                " marca= " + ((marca.getHoras() >= 6) ? "mas de 6 horas en la carrera"+'\n' :
                    marca.toString())+'\n'+
                " posicion general= " + getPosicionGeneral() + '\n' +
                 " posicion por categoria= " + getPosicionCategoria() + '\n' ;
    }


    /**
     * ordena por dlos dorsales de menor a mayor
     * @param o1
     * @param o2
     * @return un entero
     */
    @Override
    public int compare(Atleta o1, Atleta o2) {

        return o1.getDorsal() - o2.getDorsal();
    }

    /**
     * se encarga de comparar a los atletas por su dorsal
     * @param obj tipo Object y lo transformamos a un tipo de atleta
     * @return si los dosales son iguales devuelve un verdadero
     */
    @Override
    public boolean equals(Object obj) {

        //se compruba que no sea nulo
        if (obj == null){return false;}

        //comprobamos que sean iguales
        if (obj == this){return true;}

        //comprobaos que sean la misma clase
        if (obj.getClass() != this.getClass()){return false;}


        Atleta j = (Atleta) obj;

        return Objects.equals(j.getDorsal(),this.getDorsal());
    }

    @Override
    public int compareTo(Atleta o) {

        if (o.marca.getHoras() > this.marca.getHoras()){

            return -1;

        }else  if (o.marca.getHoras() < this.marca.getHoras()){

            return 1;

        }else{

            if (o.marca.getMin() > this.marca.getMin()){

                return -1;

            }else  if (o.marca.getMin() < this.marca.getMin()){

                return 1;

            }else{

                if (o.marca.getSeg() > this.marca.getSeg()){

                    return- 1;

                }else  if (o.marca.getSeg() < this.marca.getSeg()){

                    return 1;

                }else{

                    return 0;

                }

            }

        }
    }


}
