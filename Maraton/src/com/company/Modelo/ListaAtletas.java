package com.company.Modelo;


import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by EBF10 on 14/06/2017.
 */
public class ListaAtletas {

    private ArrayList<Atleta> atletas;


    //constructor


    public ListaAtletas() {
        this.atletas = new ArrayList<Atleta>();
    }


    //metodos

    /**
     * introduce una serie de atletas harcodeados en el array
     */
    public void introducirAtletasInscritos(){


        atletas.add(new Atleta("pepe", "rodrigez", "fernandez",7 , "masculino","esl","corre forest corre", 26,3,25,42));
        atletas.add(new Atleta("manolo", "figueroa", "valuarte",795 , "masculino","esp","tu fast tu furious", 18,4,35,10));
        atletas.add(new Atleta("ana", "lopez", "mascaro",123 , "femenino","usa","gran turismo", 42,4,50,25));
        atletas.add(new Atleta("pepa", "martinez", "flor",2369 , "femenino","and","need for speed", 58,1,8,59));


    }

    /**
     * muestra una lista de los atletas que estan en el array
     */
    public void mostrarAtletas(){

        for (int i = 0; i < atletas.size() ; i++) {


            System.out.println((i+1) + " " + atletas.get(i).toString()+ "(" +
                    (diferenciaDeTiempo(atletas.get(i)).getHours()+ ":" + diferenciaDeTiempo(atletas.get(i)).getMinutes()+
                            ":" + diferenciaDeTiempo(atletas.get(i)).getSeconds() +")"));

        }

    }





    public int bucarAtleta(){
        Scanner scanner = new Scanner(System.in);

        Atleta buscar = new Atleta(0);

        try {

            System.out.println("dorsal del atleta que quiere buscar");
            buscar.setDorsal(scanner.nextInt());

            return atletas.indexOf(buscar);
        }catch (InputMismatchException e){
            bucarAtleta();
            return -1;
        }catch (NullPointerException e){
            bucarAtleta();
            return -1;
        }



    }

    /**
     * borra el atleta buscado
     */
    public void borrarAtleta(){

        atletas.remove(bucarAtleta());

    }


    /**
     * edita al atleta buscado
     */
    public void editarAtleta() {

        atletas.set(bucarAtleta(),recienIscrito());

    }

    /**
     * busca el atleta en el array
     */
    public void buscarAtletaPedido(){

        atletas.get(bucarAtleta());

    }


    /**
     * añadimos un nuevo atleta al array
     */
    public void atletaNuevo(){

        atletas.add(recienIscrito());

    }
    /**
     * introduce de cero un nuevo atleta
     * pedido al usuario
     */
    private Atleta recienIscrito() {

        Scanner scanner = new Scanner(System.in);
        Atleta nuevo = new Atleta();

        do {
            System.out.println("introduce el nombre");
            nuevo.setNombre(scanner.nextLine().replaceAll("\\s+", " "));
        }while (nuevo.getNombre().equals(" "));


        do {
            System.out.println("introduce el apelido 1");
            nuevo.setNombre(scanner.nextLine().replaceAll("\\s+", " "));
        }while (nuevo.getNombre().equals(" "));


        System.out.println("introduce el apellido 2, si no tiene introduzca un espacio en blanco");
        nuevo.setNombre(scanner.nextLine().replaceAll("\\s+", " "));


        nuevo.setDorsal(introduceDorsal());


        do {
            System.out.println("sexo del participante");
            System.out.println("debe ser masculino o femenino");

            nuevo.setSexo(scanner.nextLine().toLowerCase());
        }while (!nuevo.getSexo().equals("masculino") && !nuevo.getSexo().equals("femenino") );

        System.out.println("Introduzca al club que pertenece");
        System.out.println("si no pertenece a ninguno no esciba nada");

        nuevo.setClub(scanner.nextLine());

        nuevo.setEdad(introducirEdad());

        nuevo.pedirTiempo();

        return nuevo;




    }




    //helper


    /**
     * nos muestra cual es la mejor marca entre los atletas que tenemos
     * @return tipo fecha
     */
    private Date mejorMarca(){

        Date marca = new Date();

        marca.setTime(Integer.MAX_VALUE);

        for (Atleta atleta: atletas) {

            if ( marca.getTime() > atleta.getTiempo().getTime() ){

                marca = atleta.getTiempo();

            }

        }


        return marca;

    }

    /**
     * calcula la diferencia de tiempo con la mejor marca
     * @param atleta
     * @return tipo date
     */
    private Date diferenciaDeTiempo(Atleta atleta){

        Date diferencia = new Date();

        int tiempo = (int) (atleta.getTiempo().getTime() - mejorMarca().getTime());

        diferencia.setHours(tiempo/(60*60*24));
        diferencia.setMinutes(tiempo/(60*60));
        diferencia.setSeconds(tiempo/(60));

        return diferencia;

    }

    /**
     * introduce el un dorsal de atleta el cual no esta duplicado
     */
    private int introduceDorsal() {
        Scanner scanner = new Scanner(System.in);




        try {

            System.out.println("introduzca un dorsal");
            int dorsal = scanner.nextInt();

            if (dorsalDuplicado(dorsal)){

                System.out.println("dorsal exsistente");
                introduceDorsal();
                return 0;


            }else{

                return dorsal;

            }
        }catch (InputMismatchException e){

            introduceDorsal();
            return 0;

        }


    }

    /**
     * controlamos que el dorsal no este repetido
     * @param dorsal
     * @return un falso si no esta repetid y un verrdadero si esta repetido
     */
    private boolean dorsalDuplicado(int dorsal) {

        for (Atleta atleta: atletas) {

            if (atleta.getDorsal() == dorsal){

                return true;
            }else {
                return false;
            }

        }

        return false;
    }

    /**
     * controlamos que no sea menor de edad y que nos lo vuelva a pedir
     * @return la edad
     */
    private int introducirEdad() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("introduce la edad del participante");
            int edad = scanner.nextInt();

            if (edad < 18){
                System.out.println("menor de edad no se puede inscibir");
                introducirEdad();
                return 0;
            }else {
                return edad;
            }


        }catch (InputMismatchException e){
            introducirEdad();
            return 0;
        }

    }

}
