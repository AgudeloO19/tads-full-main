package co.edu.umanizales.tads.model;


import co.edu.umanizales.tads.controller.dto.ReportPetsDTO;
import co.edu.umanizales.tads.controller.dto.ReportPetsDTO;
import co.edu.umanizales.tads.exception.ListDEExeption;
import co.edu.umanizales.tads.exception.ListSEException;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListDE {
    private NodeDE head;
    private int size;

    //Adicionar
    public void addPet(Pet pet) throws ListSEException {
        if (this.head != null) {
            NodeDE temp = this.head;
            while (temp.getNext() != null) {
                if(temp.getData().getCarnet().equals(pet.getCarnet())){
                    throw new ListSEException("Ya existe una mascota");
                }
                temp = temp.getNext();
            }
            if(temp.getData().getCarnet().equals(pet.getCarnet())){
                throw new ListSEException("Ya existe una mascota");
            }
            NodeDE newPet = new NodeDE(pet);
            temp.setNext(newPet);
            newPet.setPrevius(temp);
        } else {
            this.head = new NodeDE(pet);
        }
        size++;
    }

    //Invertir lista
    public void invertPets() throws ListSEException{
        if (this.head != null) {
            ListDE listCP = new ListDE();
            NodeDE temp = this.head;
            while (temp != null) {
                listCP.addPetsToStart(temp.getData());
                temp = temp.getNext();
            }
            this.head = listCP.getHead();
        }
        else{
            throw new ListSEException("La lista está vacía");
        }
    }

    //Mascotas masculinas al inicio y femeninos al final.
    public void orderPetsToStart() throws ListSEException {
        if (this.head != null) {
            ListDE listCP = new ListDE();
            NodeDE temp = this.head;
            while (temp != null) {
                if (temp.getData().getGenderpet().equals("M")) {    // tengo que cambiar algo
                    listCP.addPetsToStart(temp.getData());
                } else {
                    listCP.addPet(temp.getData());
                }
                temp = temp.getNext();
            }
            this.head = listCP.getHead();
        }
        else{
            throw new ListSEException("La lista está vacía");
        }
    }
    public void intercalatePetBySex() throws ListSEException
    {
        ListDE listM=new ListDE();
        ListDE listF=new ListDE();
        ListDE interspersedlist= new ListDE();
        NodeDE temp=head;
        while(temp!=null)
        {
            if(temp.getData().getGenderpet().equals('M'))
            {
                listM.addPet(temp.getData());
            }
            else
            {
                listF.addPet(temp.getData());
            }
            temp.getNext();
        }
        NodeDE tempM=listM.getHead();
        NodeDE tempF=listF.getHead();
        NodeDE tempInterspersed= interspersedlist.head;
        while( tempM!=null && tempF!=null)
        {
            if(tempInterspersed.getData().getGenderpet().equals('M'))
            {
                interspersedlist.addPet(tempF.getData());
            }
            else
            {
                interspersedlist.addPet(tempM.getData());
            }
            tempInterspersed.getNext();
        }
        head=interspersedlist.getHead();


    }



    //Dada un código eliminar a las mascotas del carnet dado
    public void deletePetByIdentification(String code) throws ListSEException {
        if (this.head != null) {
            if (this.head.getData().getCarnet().equals(code)) {
                this.head = this.head.getNext();
                if (this.head != null) {
                    this.head.setPrevius(null);
                }
            }
            else {
                NodeDE temp = this.head;
                while (temp != null) {
                    if (temp.getData().getCarnet().equals(code)) {
                        temp.getPrevius().setNext(temp.getNext());
                        if (temp.getNext() != null) {
                            temp.getNext().setPrevius(temp.getPrevius());
                        }
                        return;
                    }
                    temp = temp.getNext();
                }
                throw new ListSEException("El código " + code + " no existe en la lista");
            }
        }
        else {
            throw new ListSEException("No hay datos en la lista");
        }
    }

    //Obtener el promedio de edad de las mascotas de la lista
    public float averageAgePets() throws ListSEException {
        if (head != null) {
            NodeDE temp = head;
            int contador = 0;
            int ages = 0;
            while (temp.getNext() != null) {
                contador++;
                ages = ages + temp.getData().getAgepet();
                temp = temp.getNext();
            }
            return (float) ages / contador;
        } else {
            throw new ListSEException("La lista está vacía");
        }
    }

    //Generar un reporte que me diga cuantas mascotas hay de cada ciudad.
    public int getCountPetsByLocationCode(String code) throws ListSEException {
        int count = 0;
        if (this.head != null) {
            NodeDE temp = this.head;
            while (temp != null) {
                if (temp.getData().getLocationpet().getCode().equals(code)) {
                    count++;
                }
                temp = temp.getNext();
            }
            return count;
        } else{
            throw new ListSEException("La lista está vacía");
        }
    }

    public int getCountPetsByDepartmentCode(String code) throws ListSEException{
        int count = 0;
        if (this.head != null) {
            NodeDE temp = this.head;
            while (temp != null) {
                if (temp.getData().getLocationpet().getCode().equals(code)) {
                    count++;
                }
                temp = temp.getNext();
            }
            return count;
        }
        else{
            throw new ListSEException("La lista está vacía");
        }
    }

    public void getReportPetsByLocationGendersByAge(byte age, ReportPetsDTO report){
        if(head != null){
            NodeDE temp = this.head;
            while(temp!=null){
                if(temp.getData().getAgepet() > age){
                    report.updateQuantityPets(temp.getData().getLocationpet().getName(),
                            temp.getData().getGenderpet()); // tengo que cambiar algo
                }
                temp =temp.getNext();
            }

        }
    }

    //Método que me permita decirle a un perro determinado que adelante un número de posiciones dadas
    public void passPetByPosition(String codePet, int positions) throws ListSEException{
        if (head != null){
            if(positions<size){
                if(head.getData().getCarnet()==codePet){

                }
                else{
                    int count = 1;
                    NodeDE temp = head;
                    while(temp.getNext().getData().getCarnet()!=codePet){
                        temp = temp.getNext();
                        count++;
                        if(temp.getNext()!=null){
                            return;
                        }
                    }
                    NodeDE temp2 = new NodeDE(temp.getNext().getData());
                    temp2.setPrevius(temp);
                    temp.setNext(temp2);
                    if(positions >= count+1){
                        addPetByPosition(temp2.getData(), positions);
                    }
                }
            }
            else{
                throw new ListSEException("La posición ingresada es mayor o igual al tamaño de la lista.");
            }
        }
        else{
            throw new ListSEException("La lista se encuentra vacía.");
        }
    }

    //Método que me permita decirle a un perro determinado que pierda un numero de posiciones dadas
    public void afterwardsPetsPositions(String codePet, int positions) throws ListSEException {
        if (head != null) {
            if (positions < size) {
                if (head.getData().getCarnet() == codePet) {
                    NodeDE node = new NodeDE(head.getNext().getData());
                    addPetByPosition(node.getData(),positions + 1 );
                    head = head.getNext();
                    head.setPrevius(null);
                } else {
                    int count = 1;
                    NodeDE temp = head;
                    while (temp.getNext() != null && temp.getNext().getData().getCarnet() != codePet) {
                        temp = temp.getNext();
                        count++;
                    }
                    if (temp.getNext() == null) {
                        throw new ListSEException("No se encontró un nodo con la identificación proporcionada.");
                    } else {
                        NodeDE temp2 = new NodeDE(temp.getNext().getData());
                        temp.setNext(temp.getNext().getNext());
                        if (temp.getNext() != null) {
                            temp.getNext().setPrevius(temp);
                        }
                        addPetByPosition(temp2.getData(), count + 1 + positions);
                    }
                }
            } else {
                throw new ListSEException("La posición proporcionada excede el tamaño de la lista");
            }
        } else {
            throw new ListSEException("La lista se encuentra vacía.");
        }
    }

    public void addPetByPosition(Pet pet, int position){
        NodeDE newNode = new NodeDE(pet);
        if (position == 0){
            newNode.setNext(head);
            if (head != null){
                head.setPrevius(newNode);
            }
            head = newNode;
        } else {
            NodeDE temp = head;
            for (int i = 0; i < position - 1; i++){
                temp = temp.getNext();
            }
            newNode.setNext(temp.getNext());
            if (temp.getNext()!=null){
                temp.getNext().setPrevius(newNode);
            }
            temp.setNext(newNode);
            newNode.setPrevius(temp);
        }
        size++;
    }

    public void addPetsToStart(Pet pet) {
        if (head != null) {
            NodeDE newNodeDE = new NodeDE(pet);
            newNodeDE.setNext(head);
            head.setPrevius(newNodeDE);
            head = newNodeDE;
        } else {
            head = new NodeDE(pet);
        }
        size++;
    }

    public void changesPetExtremes() {
        if (this.head != null && this.head.getNext() != null) {
            NodeDE temp = this.head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }

            Pet copy = this.head.getData();
            this.head.setData(temp.getData());
            temp.setData(copy);

            NodeDE tempPrev = temp.getPrevius();
            NodeDE headNext = this.head.getNext();
            this.head.setNext(temp.getNext());
            this.head.setPrevius(temp);
            temp.setNext(headNext);
            temp.setPrevius(tempPrev);
        }
    }

    public int rangePetsByAge(int min, int max){
        NodeDE temp = head;
        int count = 0;
        while(temp != null){
            if(temp.getData().getAgepet() >= min && temp.getData().getAgepet() <= max){
                count++;
            }
            temp = temp.getNext();
        }
        return count;
    }

    public void boysByLetter(char initial) throws ListSEException{

        ListDE listCP = new ListDE();
        NodeDE temp = this.head;

        while (temp != null){
            if (temp.getData().getName().charAt(0) != Character.toUpperCase(initial)){
                listCP.addPet(temp.getData());
            }
            temp = temp.getNext();
        }

        temp = this.head;

        while (temp != null){
            if (temp.getData().getName().charAt(0) == Character.toUpperCase(initial)){
                listCP.addPet(temp.getData());
            }
            temp = temp.getNext();
        }

        this.head = listCP.getHead();
    }

    /*
    metodo que permita elimar un niño si pararse uno antes sino en sitio


    elimarNiñoEnSitioPorIdentificacion(identificacion)

    si
    la cabeza es igual al identificacion pedida le decimos al ayudante que ponga como cabeza el sigunte

    primero llamamo a un ayudnate
    preguntamos si hay datos
    si
    le dicimos al ayudante que recorra la lista y que busque al niño con la identficacion ingresada
    cuando lo encuntre y tiene los dos brazos llenos le digo el siguiente agarrre a mi anterior y mi aterior agarre al siguiente y si mi siguiente es nulo
    le digo que agarre a mi aterior

     */

    public void deletePetBySite (String carnet){

        if (this.head != null)
        {
            if(this.head.getData().getCarnet().equals(carnet))
            {

                head= head.getNext();
                if (this.head != null)
                {
                 head.setPrevius(null);
                }
            }
            else
            {
                NodeDE temp = this.head;
                while (temp != null)
                {
                    if (temp.getData().getCarnet().equals(carnet))
                    {
                        temp.getPrevius().setNext(temp.getNext());
                        if (temp.getNext() != null)
                        {
                            temp.getNext().setPrevius(temp.getPrevius());
                        }
                        break;
                    }
                    temp = temp.getNext();

                }
            }


        }



    }

}