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
        if(head != null){
            NodeDE temp = head;
            while(temp.getNext() !=null)
            {
                if(temp.getData().getCarnet().equals(pet.getCarnet()))
                {
                    throw new ListSEException("Ya existe un niño");
                }
                temp = temp.getNext();

            }
            if(temp.getData().getCarnet().equals(pet.getCarnet())){
                throw new ListSEException("Ya existe un niño");
            }
            /// Parado en el último
            NodeDE newNode = new NodeDE(pet);
            temp.setNext(newNode);
        }
        else {
            head = new NodeDE(pet);
        }
        size ++;
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
        if(head!=null)
        {
            ListDE listM = new ListDE();
            ListDE listF = new ListDE();
            ListDE interspersedlist = new ListDE();
            NodeDE temp = head;
            while (temp != null) {
                if (temp.getData().getGenderpet().equals("M")) {
                    listM.addPet(temp.getData());
                } else {
                    listF.addPet(temp.getData());
                }
                temp = temp.getNext();
            }
            NodeDE tempM = listM.getHead();
            NodeDE tempF = listF.getHead();

            while (tempM != null && tempF != null) {
                interspersedlist.addPet(tempF.getData());
                interspersedlist.addPet(tempM.getData());
                tempF = tempF.getNext();
                tempM = tempM.getNext();
            }
            while (tempF != null) {
                interspersedlist.addPet(tempF.getData());
                tempF = tempF.getNext();
            }

            while (tempM != null) {
                interspersedlist.addPet(tempM.getData());
                tempM = tempM.getNext();
            }
            head = interspersedlist.getHead();
        }
        else { throw  new ListSEException("No hay mascotas");}
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
            while (temp != null) {
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

    public void passPositionsPet(String carnet, int position)throws ListSEException
    {
        if(head!=null)
        {
            NodeDE temp=head;
            if(head.getData().getCarnet().equals(carnet))
            {
                throw new ListSEException("La cabeza no puede ganar más posiciones");
            }
            int count=1;
            while(temp.getNext()!=null && !temp.getNext().getData().getCarnet().equals(carnet))
            {
                temp=temp.getNext();
                count++;
            }
            if(count<position)
            {
                throw new ListSEException("No se puede adelantar este numero de posiciones");
            }
            int positiontoadd=(count+1)-position;
            Pet pedcopy=temp.getNext().getData();
            deletePetByIdentification(pedcopy.getCarnet());
            addPetByPosition(pedcopy,positiontoadd);

        }
        else { throw  new ListSEException("No hay mascotas");}
    }

    public void lostPositionsPet(String carnet, int position)throws ListSEException
    {
        if(head!=null)
        {
            NodeDE temp=head;
            if(head.getData().getCarnet().equals(carnet))
            {
                Pet headcopy=temp.getData();
                deletePetByIdentification(headcopy.getCarnet());
                addPetByPosition(headcopy,(position+1));
            }
            else
            {

                int count = 1;
                while (temp.getNext() != null && !temp.getNext().getData().getCarnet().equals(carnet))
                {
                    temp = temp.getNext();
                    count++;
                }
                int positiontoadd = (count + 1) + position;
                Pet petcopy = temp.getNext().getData();
                deletePetByIdentification(petcopy.getCarnet());
                addPetByPosition(petcopy, positiontoadd);

            }
        }
        else { throw  new ListSEException("No hay mascotas");}
    }
    public void deletePetsByAge(byte age) throws ListSEException
    {
        if (head != null)
        {
            ListDE listCp = new ListDE();
            NodeDE temp = head;
            while (temp != null)
            {
                if (temp.getData().getAgepet() != age)
                {
                    listCp.addPet(temp.getData());
                }
                temp = temp.getNext();
            }
            head = listCp.getHead();
        }
        else { throw  new ListSEException("No hay mascotas");}
    }

    public void addPetByPosition(Pet pet, int posicion)throws ListSEException
    {
        NodeDE temp=head;
        if(head!=null)
        {
            if(posicion==1)
            {
                addPetsToStart(pet);
            }
            else
            {
                for(int i=1;i<posicion-1;i++)
                {
                    if(temp.getData().getCarnet().equals(pet.getCarnet()))
                    {
                        throw new ListSEException("Ya existe una mascota");
                    }
                    temp = temp.getNext();
                }
                if(temp.getData().getCarnet().equals(pet.getCarnet()))
                {
                    throw new ListSEException("Ya existe una mascota");
                }
                NodeDE newNodeDE= new NodeDE(pet);
                newNodeDE.setNext(temp.getNext());
                temp.setNext(newNodeDE);
                newNodeDE.setPrevius(temp);
            }
        }
        else head= new NodeDE(pet);

    }

    public void addPetsToStart(Pet pet)throws ListSEException
    {
        if (head!=null)
        {
            NodeDE temp = head;
            while(temp.getNext() !=null)
            {
                if(temp.getData().getCarnet().equals(pet.getCarnet()))
                {
                    throw new ListSEException("Ya existe una mascota");
                }
                temp = temp.getNext();

            }
            if(temp.getData().getCarnet().equals(pet.getCarnet()))
            {
                throw new ListSEException("Ya existe una mascota");
            }
            NodeDE newNodeDE= new NodeDE(pet);
            newNodeDE.setNext(head);
            head.setPrevius(newNodeDE);
            head=newNodeDE;
        }
        else
        {
            head= new NodeDE(pet);
        }
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

    public void addToEndNameChar(String letter) throws ListSEException {
        if (head != null)
        {
            ListDE listCp = new ListDE();
            NodeDE temp = head;
            while (temp != null)
            {
                if (temp.getData().getName().startsWith(letter))
                {
                    listCp.addPet(temp.getData());
                }
                else
                {
                    listCp.addPetsToStart(temp.getData());
                }
                temp = temp.getNext();
            }
            head = listCp.getHead();
        }
        else { throw  new ListSEException("No hay mascotas");}
    }

    public int quantityByRangeAgeDE(int min,int max)
    {
        int count = 0;
        if (head != null)
        {
            NodeDE temp = head;
            while (temp != null)
            {
                if(temp.getData().getAgepet()>=min && temp.getData().getAgepet()<=max)
                {
                    count++;
                }
                temp=temp.getNext();
            }
        }
        return count;
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