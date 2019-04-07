package com.company;

public class ZbiorLiczb {
    Liczba liczba[];
    int ile_liczb, stopien_wielomianu;
    int reszta;
    ZbiorLiczb(int ile_liczb, int stopien_wielomianu){
        liczba = new Liczba[ile_liczb+1];
        this.ile_liczb = ile_liczb;
        this.stopien_wielomianu = stopien_wielomianu;
    }
    ZbiorLiczb(int ile_liczb){
        liczba = new Liczba[ile_liczb];

    }
    ZbiorLiczb(){

    }

    void sort(){
        for(int i=0;i<stopien_wielomianu;i++)
            for(int j=0;j<stopien_wielomianu-i;j++){
                if(liczba[j].potega<liczba[j+1].potega){
                    Liczba temp = liczba[j];
                    liczba[j] = liczba[j+1];
                    liczba[j+1] = temp;
                }
            }
    }
}
