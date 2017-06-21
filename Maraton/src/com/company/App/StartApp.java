package com.company.App;

import com.company.Modelo.ListaAtletas;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by EBF10 on 14/06/2017.
 */
public class StartApp {

    private ListaAtletas listaAtletas;


    //contructor


    public StartApp() {
        this.listaAtletas = new ListaAtletas();
    }



    //metodos

    /**
     * imprime por pantalla una lista de opciones
     * @return int para poder elegir la opcion
     */
    private int mostrarTareas(){
        Scanner scanner = new Scanner(System.in);



        System.out.println("**************************************");
        System.out.println("*  1 - cargar lista de atletas       *");
        System.out.println("*  2 - nuevo atleta                  *");
        if (listaAtletas.tamanoArray() > 0) {
            System.out.println("*  3 - mostrar atletas               *");
            System.out.println("*  4 - ordenar por marca             *");
            System.out.println("*  5 - ordenar por dorsal            *");
            System.out.println("*  6 - elimina atleta                *");
            System.out.println("*  7 - actualizar fichero            *");
            System.out.println("*  8 - buscar por dorsal             *");
            System.out.println("*  9 - editar atleta                *");
        }
        System.out.println("*  0 - Salir                         *");
        System.out.println("**************************************");
        System.out.println("Opcion:");
        try {


            return scanner.nextInt();

        }catch (InputMismatchException e){
            System.out.println("introduzca numeros solo");
            inicioApp();
        }
        return 0;
    }


    /**
     * muestra la interfaz para pedir que es lo que quieres hacer.
     * si no tiene valores solo nos permitira hacer las tres primera opciones,
     * si tiene mas de una nos muestra todas las opciones
     */
    public void inicioApp() {

        int opcion;


        try {
            listaAtletas.cargarFichero();
            while ((opcion = mostrarTareas()) != 0) {

                if (listaAtletas.tamanoArray() == 0) {
                    switch (opcion) {
                        case 1:
                            listaAtletas.introducirAtletasInscritos();
                            break;
                        case 2:

                            listaAtletas.atletaNuevo();

                            break;
                    }
                } else {

                    switch (opcion) {
                        case 1:
                            listaAtletas.introducirAtletasInscritos();
                            break;
                        case 2:
                            listaAtletas.atletaNuevo();
                            break;
                        case 3:
                            mostrar();
                            break;
                        case 4:
                            listaAtletas.ordenarMarcas();
                            break;
                        case 5:
                            listaAtletas.ordenarDorsal();
                            break;
                        case 6:
                            listaAtletas.borrarAtleta();
                            break;
                        case 7:
                            listaAtletas.guardarFichero();
                            break;
                        case 8:
                            listaAtletas.buscarAtletaPedido();
                            break;
                        case 9:
                            listaAtletas.editarAtleta();
                            break;
                        default:
                    }
                }

            }

        } catch (NullPointerException e) {
            System.out.println("introduzca los valores correctos");
            inicioApp();
        }
    }



    /**
     * imprime por pantalla una lista de opciones
     * @return int para poder elegir la opcion
     */
    private int mostrarCategorias(){
        Scanner scanner = new Scanner(System.in);



        System.out.println("***********************************");
        System.out.println("* 1- Junior                       *");
        System.out.println("* 2- Promesa                      *");
        System.out.println("* 3- Senior                       *");
        System.out.println("* 4- Veterano 1                   *");
        System.out.println("* 5- Veteramo 2                   *");
        System.out.println("* 6- Veteramo 3                   *");
        System.out.println("* 7- Super Veteramo               *");
        System.out.println("* 0- volver al menu principal     *");
        System.out.println("***********************************");
        System.out.println("Opcion:");
        try {


            return scanner.nextInt();

        }catch (InputMismatchException e){
            mostrar();
        }
        return 0;
    }



    private void mostrar(){



        try {
            int option;
            while ((option = mostrarCategorias()) != 0){


                switch (option){
                    case 1:
                        listaAtletas.mostrarAtletas("junior");
                        break;
                    case 2:
                        listaAtletas.mostrarAtletas("promesa");
                        break;
                    case 3:
                        listaAtletas.mostrarAtletas("senior");
                        break;
                    case 4:
                        listaAtletas.mostrarAtletas("veterano 1");
                        break;
                    case 5:
                        listaAtletas.mostrarAtletas("veterano 2");
                        break;
                    case 6:
                        listaAtletas.mostrarAtletas("veterano 3");
                        break;
                    case 7:
                        listaAtletas.mostrarAtletas("super veterano");
                        break;

                }

                mostrar();
        }
        }catch (NullPointerException e){
            System.out.println("valores incorrectos");
           mostrar();

        }catch (InputMismatchException e){
            mostrar();
        }



    }


}
