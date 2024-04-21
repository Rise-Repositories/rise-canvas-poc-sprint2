package school.sptech.risepocsprint2;

import school.sptech.risepocsprint2.entity.Endereco;

import java.util.Arrays;
import java.util.List;

public class ListaObj<T> {
    private T[] array;
    private int elementCount;

    public ListaObj(int size) {
        this.array = (T[]) new Object[size];
        this.elementCount = 0;
    }
    public ListaObj(List<T> array) {
        this.array = (T[]) new Object[array.size()];
        this.elementCount = array.size();
        for (int i = 0; i < array.size(); i++) {
            this.array[i] = array.get(i);
        }
    }

    public void add(T elem) {
        if (elementCount < array.length) {
            array[elementCount] = elem;
            elementCount++;
        } else {
            T[] aux = Arrays.copyOf(array, array.length);
            array = Arrays.copyOf(aux, aux.length * 2);

            array[elementCount] = elem;
            elementCount++;
            throw new IllegalStateException("Lista cheia");
        }
    }

    public int search(T elem) {
        int indiceEncontrado = -1;
        for (int i = 0; i < elementCount; i++) {
            if (array[i] == elem) {
                indiceEncontrado = i;
                return indiceEncontrado;
            }
        }
        return indiceEncontrado;
    }

    public boolean delete(int index) {
        if (index >= 0 && index < elementCount) {
            for (int i = index; i < elementCount - 1; i++) {
                array[i] = array[i + 1];
            }
            elementCount--;
            return true;
        }
        return false;
    }

    public boolean delete(T elem) {
        if (search(elem) != -1) {
            delete(search(elem));
            return true;
        }
        return false;
    }

    public int size() {
        return elementCount;
    }

    public T get(int indice) {
        if (indice < 0 || indice >= elementCount) {
            return null;
        }
        return array[indice];
    }

    public T set(int indice, T elem) {
        if (indice < 0 || indice >= elementCount) {
            return null;
        }
        return array[indice] = elem;
    }

    public void clean() {
        elementCount = 0;
    }

    public void show() {
        for (int i = 0; i < elementCount; i++) {
            System.out.println(array[i]);
        }
    }

    public T[] getArray() {
        return array;
    }
}
