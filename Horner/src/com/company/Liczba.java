package com.company;

public class Liczba {
    int wspolczynnik;
    int potega;

    Liczba(int wspolczynnik, int potega){
        this.wspolczynnik=wspolczynnik;
        this.potega=potega;
    }

    @Override
    public String toString() {
        return wspolczynnik+"x^"+potega+" ";
    }
}
