package co.edu.umanizales.tads.model;
import java.util.List;
import java.util.ArrayList;

import co.edu.umanizales.tads.controller.dto.GenderDTO;
import co.edu.umanizales.tads.controller.dto.KidByLocationAndGenderDTO;
import co.edu.umanizales.tads.exception.ListSEException;
import lombok.Data;

import java.awt.*;

@Data
public class ListSE {
    private Node head;
    private int size;
    /*
    Algoritmo de adicionar al final
    Entrada
        un niño
    si hay datos
    si
        llamo a un ayudante y le digo que se posicione en la cabeza
        mientras en el brazo exista algo
            pasese al siguiente
        va estar ubicado en el ùltimo
        meto al niño en un costal (nuevo costal)
        y le digo al ultimo que tome el nuevo costal
    no
        metemos el niño en el costal y ese costal es la cabeza
     */


    public void add(Kid kid){
        if(head != null){
            Node temp = head;
            while(temp.getNext() !=null)
            {
                temp = temp.getNext();
            }
            /// Parado en el último
            Node newNode = new Node(kid);
            temp.setNext(newNode);
        }
        else {
            head = new Node(kid);
        }
        size ++;
    }

    /* Adicionar al inicio
    si hay datos
    si
        meto al niño en un costal (nuevocostal)
        le digo a nuevo costal que tome con su brazo a la cabeza
        cabeza es igual a nuevo costal
    no
        meto el niño en un costal y lo asigno a la cabez
     */
    public void addToStart(Kid kid){
        if(head !=null)
        {
            Node newNode = new Node(kid);
            newNode.setNext(head);
            head = newNode;
        }
        else {
            head = new Node(kid);
        }
        size++;
    }

    /*

    metodo para añadir en posiscion
     */

    public void addInpos(Kid kid, int position) throws ListSEException {
        if (position < 0) {
            throw new ListSEException("La posición no puede ser negativa");
        }

        Node nuevoNodo = new Node(kid);

        if (position == 0) {
            nuevoNodo.setNext(head);
            head = nuevoNodo;
        } else {
            Node actual = head;
            int index = 1;
            while (actual != null && index < position) {
                actual = actual.getNext();
                index++;
            }

            if (actual == null) {
                throw new ListSEException("La posición es mayor que el tamaño de la lista");
            }

            nuevoNodo.setNext(actual.getNext());
            actual.setNext(nuevoNodo);
        }
    }
    /*
    niños al inicio y niñas al final
     */
    public void orderBoysToStart() throws ListSEException{
        if(this.head !=null){
            ListSE listCp = new ListSE();
            Node temp = this.head;
            while(temp != null){
                if(temp.getData().getGender().equals("M"))
                {
                    listCp.addToStart(temp.getData());
                }
                else{
                    listCp.add(temp.getData());
                }

                temp = temp.getNext();
            }
            this.head = listCp.getHead();
        }
    }
    /*
    intercalar niño niña
     */
    public void intercalateKidByGender() throws ListSEException
    {
        ListSE listM=new ListSE();
        ListSE listF=new ListSE();
        ListSE exchangeGenderList= new ListSE();
        Node temp=head;
        while(temp!=null)
        {
            if(temp.getData().getGender().equals("M"))
            {
                listM.add(temp.getData());
            }
            else
            {
                listF.add(temp.getData());
            }
            temp.getNext();
        }
        Node tempM=listM.getHead();
        Node tempF=listF.getHead();
        Node tempexchange= exchangeGenderList.head;
        while( tempM!=null && tempF!=null)
        {
            if(tempexchange.getData().getGender().equals("M"))
            {
                exchangeGenderList.add(tempF.getData());
            }
            else
            {
                exchangeGenderList.add(tempM.getData());
            }
            tempexchange.getNext();
        }
        head=exchangeGenderList.getHead();

    }

     // invertir la lista
    public void invert(){
        if(this.head !=null){
            ListSE listCp = new ListSE();
            Node temp = this.head;
            while(temp != null){
                listCp.addToStart(temp.getData());
                temp = temp.getNext();
            }
            this.head = listCp.getHead();
        }
    }


    // Eliminar por id

    public void deleteKidById(String identification)throws ListSEException
    {
        if(head!=null)
        {
            ListSE listCp=new ListSE();
            Node temp=head;
            while(temp!= null)
            {
                if(temp.getData().getIdentification().equals(identification))
                {
                    temp=temp.getNext();
                }
                listCp.add(temp.getData());
                temp = temp.getNext();
            }

            head = listCp.getHead();
        }


    }




    // Eliminar ninos por edad
    public void deleteKidByAge(byte age)throws ListSEException
    {
        if (head != null)
        {
            ListSE listCp = new ListSE();
            Node temp = head;
            while (temp != null)
            {
                if (temp.getData().getAge() != age)
                {
                    listCp.add(temp.getData());
                }
                temp=temp.getNext();
            }
            listCp.head=head;
        }
    }
    //Obtener el promedio de edad de los niños de la lista.

    public float avarageAge() {
        if (head != null) {
            Node temp = head;
            int count =0;
            int ages = 0;
            while (temp.getNext()!=null) {
                count++;
                ages = ages + temp.getData().getAge();
            }
            return  (float) ages/count;
        }else
        {
            return (int) 0;
        }
    }
    //El niño adelante posiciones

    public void gainPosition(String id, int position, ListSE listSE) throws ListSEException {
        if (id == null) {
            throw new ListSEException("Kid's identification can't be null");
        }
        if (position < 1) {
            throw new ListSEException("Position can't be less than 1");
        }
        if (listSE == null) {
            throw new ListSEException("List can't be null");
        }

        if (head != null) {
            Node temp = this.head;
            int count = 1;

            while (temp != null && !temp.getData().getIdentification().equals(id)) {
                temp = temp.getNext();
                count++;
            }
            int newPosition = count - position;
            Kid listCopy = temp.getData();
            listSE.deleteKidById(temp.getData().getIdentification());
            listSE.addInpos(listCopy, newPosition);
        }
    }


    //el niño retrocede oosiciones dadas

    public void backPosition(String id, int position, ListSE listSE) throws ListSEException {
        if (head == null) {
            throw new ListSEException("The list is empty");
        }
        if (id == null) {
            throw new ListSEException("Id can't be null");
        }
        if (position < 1 || position > listSE.size + 1) {
            throw new ListSEException("Position out of range");
        }

        Node temp = this.head;
        int count = 1;

        while (temp != null && !temp.getData().getIdentification().equals(id)) {
            temp = temp.getNext();
            count++;
        }

        if (temp == null) {
            throw new ListSEException("Kid with id " + id + " not found");
        }

        int newPosition = position + count;
        Kid listCopy = temp.getData();
        listSE.deleteKidById(temp.getData().getIdentification());
        listSE.addInpos(listCopy, newPosition);
    }




    public void addToEndNameChar(String letra) throws ListSEException
    {

        if(head!=null)
        {
            ListSE listCp=new ListSE();
            Node temp=head;
            if(temp.getData().getName().startsWith(letra))
            {
                listCp.add(temp.getData());
                temp=temp.getNext();
            }
            else
            {
                listCp.addToStart(temp.getData());
                temp=temp.getNext();
            }
            head=listCp.getHead();
        }
    }




    public Kid returnKidEliminate(String Identification, int posicion) {
        Node temp = head;

        Kid eliminateKid = new Kid("","",(byte) 0, "m", new Location("",""));
        if (head != null)

            if (head.getData().equals(Identification)) {
                eliminateKid = head.getData();

                head = temp.getNext();
            } else {
                int pos = 1;
                while (temp != null) {
                    if (pos == posicion - 1) {
                        break;
                    }
                    temp = temp.getNext();
                    pos++;
                    eliminateKid=temp.getData();

                }
                temp.setNext(temp.getNext().getNext());
            }
        else {
            head = null;
        }
        return eliminateKid;
    }



    public int getPosById(String id) {
        Node temp = head;
        int acum = 0;
        if (head != null) {
            while (temp != null && !temp.getData().getIdentification().equals(id)) {
                acum = acum + 1;
                temp = temp.getNext();

            }


        }
        return acum;
    }

    public int informRangeByAge(int first, int last)  {
        if (first < 0 || last < 0 || first > last) {

        }
        Node temp = head;
        int count = 0;
        while (temp != null){
            if (temp.getData().getAge() >= first && temp.getData().getAge() <= last){
                count ++;
            }
            temp = temp.getNext();
        }
        return count;
    }










    public void changeExtremes(){
        if(this.head !=null && this.head.getNext() !=null)
        {
            Node temp = this.head;
            while(temp.getNext()!=null)
            {
                temp = temp.getNext();
            }
            //temp está en el último
            Kid copy = this.head.getData();
            this.head.setData(temp.getData());
            temp.setData(copy);
        }

    }

    public int getCountKidsByLocationCode(String code){
        int count =0;
        if( this.head!=null){
            Node temp = this.head;
            while(temp != null){
                if(temp.getData().getLocation().getCode().equals(code)){
                    count++;
                }
                temp = temp.getNext();
            }
        }
        return count;
    }








}







