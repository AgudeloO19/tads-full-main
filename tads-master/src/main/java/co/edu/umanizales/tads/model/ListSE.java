package co.edu.umanizales.tads.model;
import java.util.List;
import java.util.ArrayList;

import co.edu.umanizales.tads.controller.dto.GenderDTO;
import co.edu.umanizales.tads.controller.dto.KidByLocationAndGenderDTO;
import co.edu.umanizales.tads.controller.dto.ReportKidsDTO;
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


    public void add(Kid kid) throws ListSEException {
        if(head != null){
            Node temp = head;
            while(temp.getNext() !=null)
            {
                if(temp.getData().getIdentification().equals(kid.getIdentification()))
                {
                    throw new ListSEException("Ya existe un niño");
                }
                temp = temp.getNext();

            }
            if(temp.getData().getIdentification().equals(kid.getIdentification())){
                throw new ListSEException("Ya existe un niño");
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
    public int size() {
        int count = 0;
        Node temp = head;
        while (temp != null) {
            count++;
            temp = temp.getNext();
        }
        return count;
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
    public void addToStart(Kid kid)throws ListSEException
    {
        if(head!=null)
        {
            Node temp = head;
            while(temp.getNext() !=null)
            {
                if(temp.getData().getIdentification().equals(kid.getIdentification()))
                {
                    throw new ListSEException("Ya existe un niño");
                }
                temp = temp.getNext();

            }
            if(temp.getData().getIdentification().equals(kid.getIdentification()))
            {
                throw new ListSEException("Ya existe un niño");
            }
            Node newNode= new Node(kid);
            newNode.setNext(head);
            head=newNode;
        }
        else
        {
            head= new Node(kid);
        }
    }

    /*

    metodo para añadir en posiscion
     */

    public void addInPos(Kid kid, int posicion)throws ListSEException
    {
        Node temp=head;
        if(head!=null)
        {

            if(posicion==1)
            {
                addToStart(kid);
            }
            else
            {
                for(int i=1;i<posicion-1;i++)
                {
                    temp = temp.getNext();
                }
                Node newNode= new Node(kid);
                newNode.setNext(temp.getNext());
                temp.setNext(newNode);

            }
        }
        else head= new Node(kid);

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
        ListSE interspersedlist= new ListSE();
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
            temp=temp.getNext();
        }
        Node tempM=listM.getHead();
        Node tempF=listF.getHead();

        while (tempM != null && tempF != null)
        {
            interspersedlist.add(tempF.getData());
            interspersedlist.add(tempM.getData());
            tempF = tempF.getNext();
            tempM = tempM.getNext();
        }
        while (tempF != null)
        {
            interspersedlist.add(tempF.getData());
            tempF = tempF.getNext();
        }

        while (tempM != null)
        {
            interspersedlist.add(tempM.getData());
            tempM = tempM.getNext();
        }
        head=interspersedlist.getHead();

    }

     // invertir la lista
     public void invert ()throws ListSEException
     {
         if(head!=null)
         {
             ListSE listCp=new ListSE();
             Node temp=head;
             while(temp!=null)
             {
                 listCp.addToStart(temp.getData());
                 temp= temp.getNext();
             }
             head=listCp.getHead();
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
                if(!temp.getData().getIdentification().equals(identification))
                {
                    listCp.add(temp.getData());
                }

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
            this.head=listCp.getHead();
        }
    }
    //Obtener el promedio de edad de los niños de la lista.

    public float averageAge() throws ArithmeticException {
        if(head != null){
            Node temp = head;
            int countkids = 0;
            int ageskids = 0;
            while (temp!= null){
                countkids++;
                ageskids = ageskids + temp.getData().getAge();
                temp = temp.getNext();
            }
            return (float) ageskids/countkids;
            }
        else {
            return  0;

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
            if(count<position)
            {
                throw new ListSEException("No se puede adelantar este numero de posiciones");
            }
            int newPosition = count - position;
            Kid listCopy = temp.getData();
            listSE.deleteKidById(temp.getData().getIdentification());
            listSE.addInPos(listCopy, newPosition);
        }
    }


    //el niño retrocede oosiciones dadas

    //El niño pierda posiciones
    public void backPosition(String id, int position, ListSE listSE) throws ListSEException {
        if (head == null) {
            throw new ListSEException("The list is empty");
        }
        if (id == null) {
            throw new ListSEException("Id can't be null");
        }
        if (position < 1 || position > listSE.size() + 1) {
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
        listSE.addInPos(listCopy, newPosition);
    }






    public void addToEndNameChar(String letter) throws ListSEException {
        if (head != null)
        {
            ListSE listCp = new ListSE();
            Node temp = head;
            while (temp != null)
            {
                if (temp.getData().getName().startsWith(letter))
                {
                    listCp.add(temp.getData());
                }
                else
                {
                    listCp.addToStart(temp.getData());
                }
                temp = temp.getNext();
            }
            head = listCp.getHead();
        }
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
    public int getCountKidsByLocationSize(int size)
    {
        int count=0;
        if (head!=null)
        {
            Node temp=head;
            while(temp!=null)
            {
                if(temp.getData().getLocation().getCode().length()==size)
                {count++;}
                temp=temp.getNext();
            }

        }
        return count;
    }


    //Informe de niños por rango de edad
    public int quantityByRangeAge(int min,int max)
    {
        int count = 0;
        if (head != null)
        {
            Node temp = head;
            while (temp!= null)
            {
                if(temp.getData().getAge()>=min && temp.getData().getAge()<=max)
                {
                    count++;
                }
                temp=temp.getNext();
            }
        }
        return count;
    }
    public void getReportKidsByLocationGendersByAge(byte age, ReportKidsDTO report){
        if(head !=null){
            Node temp = this.head;
            while(temp!=null)
            {
                if(temp.getData().getAge()>age)
                {
                    report.updateQuantity(temp.getData().getLocation().getName(), temp.getData().getGender());
                }
                temp = temp.getNext();
            }
        }
    }











}















