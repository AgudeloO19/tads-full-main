package co.edu.umanizales.tads.model;

import lombok.Data;

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

    public void deleteKidById(String Identification, int posicion) {
        Node temp = head;
        if (head != null) {
            if (head.getData().equals(Identification)) {
                head = temp.getNext();
            } else {
                int pos = 1;
                temp = temp.getNext();
                pos++;
                while (temp != null) {
                    if (pos == posicion - 1) {
                        break;
                    }

                }
                temp.setNext(temp.getNext().getNext());
            }
        } else {
            head = null;
        }
    }

    // Eliminar ninos por edad
    public void deleteKidByAge(String age, int posicion) {
        Node temp = head;
        if (head != null) {

            if (head.getData().equals(age)) {
                head = temp.getNext();

            }
            else
            {
                int pos = 1;
                while (temp != null) {
                    if (pos == posicion - 1) {
                        break;
                    }
                    temp = temp.getNext();
                    pos++;
                }
                temp.setNext(temp.getNext().getNext());
            }
        } else {
            head = null;
        }
    }

    public Kid returnKidEliminate(String Identification, int posicion) {
        Node temp = head;

        Kid eliminateKid = new Kid("","",(byte) 0, 'm', new Location("",""));
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


    public void passKIdPos(String id, int posicion )
    {
        Node temp =head;

        if(head!=null)
        {
            int pos = 1;
            if (head.getData().equals(id))
            {
                head =temp.getNext();



            }


        }


    }









    public void orderBoysToStart(){
        if(this.head !=null){
            ListSE listCp = new ListSE();
            Node temp = this.head;
            while(temp != null){
                if(temp.getData().getGender()=='M')
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







