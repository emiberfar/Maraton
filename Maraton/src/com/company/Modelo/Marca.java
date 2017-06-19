package com.company.Modelo;


import java.io.Serializable;
import java.util.Scanner;

/**
 * Created by emilio on 16/06/2017.
 */
public class Marca implements Serializable{

    private static final long serialVersionUID = -2196807447451159L;

    private int horas,min,seg;


    //contructor


    public Marca() {
    }

    public Marca(int horas, int min, int seg) {
       setHoras(horas);
       setMin(min);
       setSeg(seg);
    }



    //accesores

    public int getHoras() {
        return horas;
    }

    /**
     * controlamos que o puedaintroducir mas de 24 horas y que no sea negativo
     * @param horas nt
     */
    public void setHoras(int horas) {

        if (horas > 24 || horas < 0 ){

            this.horas = 0;

        }else{

            this.horas = horas;

        }
    }

    public int getMin() {
        return min;
    }

    /**
     * controlamos que no pueda introducir mas de 60 min y que no sea negativo
     * @param min int
     */
    public void setMin(int min) {

        if (min > 60 ){

            this.min = 0;

        }else{

            this.min = min;

        }
    }

    public int getSeg() {
        return seg;
    }

    /**
     *  controlamos quelos segundos no superen los 60 y que no sea negativo
     * @param seg
     */
    public void setSeg(int seg) {


        if(seg > 60 ){

            this.seg = 0;

        }else{

            this.seg = seg;

        }

    }


    //metodos


    /**
     * pedimos el tiempo de marca al usuario el cual lo debera introducir en horas, minutos, segundos
     *
     * @return nueva Marca
     */
    public void pedirTiempo() {
        Scanner scanner = new Scanner(System.in);


        System.out.println("introduzca el tiepo del recorrido");



        System.out.println("introduzca las horas");
        this.setHoras(scanner.nextInt());


        System.out.println("introduzca los min");
        this.setMin(scanner.nextInt());


        System.out.println("introduzca los segundos");
        this.setSeg(scanner.nextInt());





    }


    @Override
    public String toString() {
        return  ""+horas + ":" + min + ":" + seg ;
    }



}
