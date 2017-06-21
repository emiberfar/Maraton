package com.company.Modelo;


import java.io.*;
import java.util.*;

/**
 * Created by EBF10 on 14/06/2017.
 */
public class ListaAtletas{

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

        borrarNull();

        atletas.add(new Atleta("pepe", "rodrigez", "fernandez",7 , "masculino","esl","corre forest corre", 26,3,25,42));
        atletas.add(new Atleta("robeto", "lopo", "",4 , "masculino","esl","corre forest corre", 27,2,25,42));
        atletas.add(new Atleta("manolo", "figueroa", "valuarte",795 , "masculino","esp","tu fast tu furious", 18,4,35,10));
        atletas.add(new Atleta("ana", "lopez", "mascaro",123 , "femenino","usa","gran turismo", 42,4,6,25));
        atletas.add(new Atleta("pepa", "martinez", "flor",2369 , "femenino","and","need for speed", 58,1,8,59));

        guardarFichero();

    }


    /**
     * muestra el atleta pedido
     */
    public void mostrarAtletas() {

        borrarNull();
        for (Atleta atleta : atletas) {



            posicionGeneral(atleta);

            posicionCategoria(atleta,atleta.getCategoria());



            System.out.println(atleta.toString() + diferenciaMarca(atleta, mejorMarca()) + "\n" + posicionCategoria(atleta,atleta.getCategoria().toLowerCase()));


        }
    }

        /**
         * muestra los atletas dependiendo de la categoria
         */
    public void mostrarAtletas(String categoria){

        Scanner scanner = new Scanner(System.in);

        System.out.println("introduzca masculino o femenino de pendiendo del sexo que quiera ver la calificafion");
        System.out.println("sino escriba general");

        String sexo = scanner.nextLine().toLowerCase().replaceAll("\\s+", " ");

        borrarNull();

        for (Atleta atleta: atletas) {

            posicionGeneral(atleta);




                posicionCategoria( atleta ,categoria.toLowerCase());



            if ( atleta.getCategoria().toLowerCase().equals(categoria.toLowerCase() ) ) {
               if ( atleta.getSexo().toLowerCase().equals( ( sexo  ) ) ){



                   System.out.println(atleta.toString() + diferenciaMarca(atleta, mejorMarca()) + "\n" + posicionCategoria( atleta ,categoria.toLowerCase()));

               }else if (sexo.equals("general")){

                   System.out.println( atleta.toString() + diferenciaMarca( atleta ,mejorMarca() )  + "\n" + posicionCategoria(atleta , categoria.toLowerCase()) );


               }

            }

       }


    }
    /**
     * buaca un altleta a traves de su dorsal
     * @return tipo Altleta
     */
    private int bucarAtleta(){
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


        borrarNull();
        guardarFichero();
    }


    /**
     * edita al atleta buscado
     */
    public void editarAtleta() {

        atletas.set(bucarAtleta(),recienIscrito());

        borrarNull();
        guardarFichero();
    }

    /**
     * busca el atleta en el array dependiendo del dorsal
     */
    public void buscarAtletaPedido() {

        Atleta buscar = atletas.get(bucarAtleta());


        borrarNull();
        for (Atleta atleta : atletas) {


            posicionGeneral(atleta);

            posicionCategoria(atleta, atleta.getCategoria());

            if (buscar == atleta) {

                System.out.println(atleta.toString() +
                        diferenciaMarca(atleta , mejorMarca()) + "\n" +
                        posicionCategoria(atleta, atleta.getCategoria()));


            }
        }
    }


    /**
     * añadimos un nuevo atleta al array
     */
    public void atletaNuevo(){

        borrarNull();
        atletas.add(recienIscrito());
        guardarFichero();


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
        }while (nuevo.getNombre().equals(" ") || nuevo.getNombre().equals("") );


        do {
            System.out.println("introduce el apelido 1");
            nuevo.setApellido(scanner.nextLine().replaceAll("\\s+", " "));
        }while (nuevo.getApellido().equals(" ") || nuevo.getApellido().equals(""));


        System.out.println("introduce el apellido 2, si no tiene introduzca un espacio en blanco");
        nuevo.setApellido2(scanner.nextLine().replaceAll("\\s+", " "));


        nuevo.setDorsal(introduceDorsal());

        do {
            System.out.println("introduce la nacionalidad con el codigo de 3 letras de su pais");
            nuevo.setNacionalidad(scanner.nextLine().replaceAll("\\s+", " "));
        }while (nuevo.getNacionalidad().length() > 4 || nuevo.getNacionalidad().length() < 2);

        do {
            System.out.println("sexo del participante");
            System.out.println("debe ser masculino o femenino");

            nuevo.setSexo(scanner.nextLine().toLowerCase());
        }while (!nuevo.getSexo().equals("masculino") && !nuevo.getSexo().equals("femenino") );

        System.out.println("Introduzca al club que pertenece");
        System.out.println("si no pertenece a ninguno no esciba nada");

        nuevo.setClub(scanner.nextLine());

        nuevo.setEdad(introducirEdad());

        nuevo.getMarca().pedirTiempo();


        return nuevo;




    }

    /**
     * ordena por las mejor marca realizada de menos tiempo a mas
     */
    public void ordenarMarcas(){

        Collections.sort( atletas );

        mostrarAtletas();


    }

    /**
     * ordena por los dorsales que han sido asigndos de menor a mayor
     */
    public void ordenarDorsal(){

        Collections.sort( atletas, new Atleta());

        mostrarAtletas();

    }


    /**
     * calcula el tiempo de le leva al primero despues de pasar por la meta
     * @param atleta se usa para coger el tiepo de cada atleta
     * @return String donde mostramos la diferencia de tiempo
     */
    public String diferenciaMarca(Atleta atleta, Marca marcaMejor){


        Marca diferencia = new Marca();

        Marca mejor;

        mejor=marcaMejor;

        diferencia.setHoras(atleta.getMarca().getHoras() - mejor.getHoras());


        diferencia.setMin(atleta.getMarca().getMin() - mejor.getMin());

        diferencia.setSeg(atleta.getMarca().getSeg() - mejor.getSeg());


        if (diferencia.getMin() < 0){

            diferencia.setHoras(diferencia.getHoras()-1);
            diferencia.setMin(60+diferencia.getMin());

        }


        if (diferencia.getSeg() < 0){

            diferencia.setMin(diferencia.getMin()-1);
            diferencia.setSeg(60+diferencia.getSeg());

        }

        if ( diferencia.getHoras() == 0  && diferencia.getMin() == 0 && diferencia.getSeg() == 0 ){

            return " ( Es la mejor marca )";


        }else{

            return " ( la diferencia con la mejor marca es = "+diferencia.getHoras() + ":" + diferencia.getMin() + ":" + diferencia.getSeg() +" )";

        }


    }



    /**
     * se encarga de guardar los datos en un fichero
     */
    public void guardarFichero(){

        try {
            ObjectOutputStream guardar = new ObjectOutputStream( new FileOutputStream("Maraton/Datos/atletas.dat"));
            guardar.writeObject( atletas );
            guardar.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * se encarga de cargar los valores del archivo
     */
    public void cargarFichero(){

        try {
            ObjectInputStream cargar = new ObjectInputStream( new FileInputStream( "Maraton/Datos/atletas.dat" ));
            atletas =  (ArrayList<Atleta>) cargar.readObject();
            cargar.close();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (FileNotFoundException e){
            System.out.println("No hay valores en el fichero");
            System.out.println("añada altetas o cree uno");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }





    //helper





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


    /**
     * saca la marca meas pequeña en el array
     * @return mejor de tipo Marca
     */
    private Marca mejorMarca(){



        return duplicarArray().get(0).getMarca();

    }


    /**
     * duplicamos el array de atletas y lo orena por marcas
     * @return develve u narraylis de tipo atleta
     */
    private ArrayList<Atleta> duplicarArray(){


        ArrayList<Atleta> duplicado = new ArrayList<>();


        for (Atleta atleta: atletas) {

            duplicado.add(atleta);

        }


        Collections.sort(duplicado);
        return duplicado;



    }

    /**
     * Se encarga de comparar los arrays y mirar si tienen la misma marca
     * si es asi introducimos la posicion de en la que se encuartra el atleta
     * @param pos
     */
    private void posicionGeneral(Atleta pos){

        ArrayList<Atleta> copia;

        copia = duplicarArray();

       try {


           for (int j = 0; j < copia.size(); j++) {

               if (pos.getMarca() == copia.get(j).getMarca()){

                   pos.setPosicionGeneral(j+1);

               }



           }

       }catch (NullPointerException e) {
           e.printStackTrace();
       }



    }

    /**
     * devuelve el tamaño del array
     * @return numero de elementos que tiene el array
     */
    public int tamanoArray(){


        return atletas.size();

    }



    /**
     * recorre el arry y busca si hay un nulo y lo borra
     */
    private void borrarNull(){

        Iterator<Atleta> itAtleta = atletas.iterator();

        while( itAtleta.hasNext() ){
            if( itAtleta.next() == null ){
                itAtleta.remove();
            }
        }

    }

    /**
     * posicion por categoria donde utilizamos un array con los atletas de ccada categria y sacamos su posicion y la diferencia de tiempo con las marcas
     * @param cat la categoria que queremos pasarle
     * @return la informacion del atleta de su categoria
     */
    private String posicionCategoria( Atleta atleta ,String cat){

        ArrayList<Atleta> posicion;

        posicion = arrayPosicion(cat);





        for (int i = 0; i < posicion.size() ; i++) {


                if (atleta.equals(posicion.get(i))) {

                    posicion.get(i).setPosicionCategoria(i + 1);


                    return diferenciaMarca(posicion.get(i), mejorMarcaCategoria(posicion)) + " de la categoria ";

                }


        }

        return " no va ";
    }


    /**
     * introducimos todos los atletas dentro de un array por categorias
     * @param categoria String el cual pasa las categorias
     * @return ArrayList de Atletas con los atletas de la ccategoria
     */
    private ArrayList<Atleta> arrayPosicion(String categoria){


        ArrayList<Atleta> posCat = new ArrayList<>();

        for (Atleta atleta: atletas
             ) {

            if (atleta.getCategoria().toLowerCase().equals(categoria.toLowerCase())){
                posCat.add(atleta);
            }

        }

        Collections.sort( posCat );

        return posCat;

    }



    private Marca mejorMarcaCategoria(ArrayList<Atleta> marcas){



        Marca mejorCat = new Marca();

        Collections.sort(marcas);

        mejorCat = marcas.get(0).getMarca();



        return mejorCat;
    }


}
