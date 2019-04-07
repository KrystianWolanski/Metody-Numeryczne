package com.company;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);

    static ZbiorLiczb wprowadzanie_danych_do_zbioru(){
        int ile_liczb;
        int potega;
        int stopien = 0;

        System.out.print("Podaj ile liczb: ");
        ile_liczb= scan.nextInt();

        ZbiorLiczb temp = new ZbiorLiczb(ile_liczb);

        for(int i=0; i<ile_liczb;i++){
            System.out.print("Podaj "+ (i+1) + " współczynnik: ");
            int wspolczynnik = scan.nextInt();
            System.out.print("Podaj "+ (i+1) + " potęge: ");
            potega = scan.nextInt();
            if(potega>stopien)
                stopien = potega;

            System.out.println("-----------------------------------");

            temp.liczba[i] = new Liczba(wspolczynnik, potega);
        }
        ZbiorLiczb zb = new ZbiorLiczb(ile_liczb,stopien);
        for(int i=0;i<ile_liczb;i++)
            zb.liczba[i] = temp.liczba[i];

        return zb;
    }
    static ZbiorLiczb sprawdzanie_czy_sie_powtarzaja(ZbiorLiczb zb){

       for(int i=0;i<zb.ile_liczb;i++){
               for(int j=i+1;j<zb.ile_liczb;){
                   if(zb.liczba[i].potega == zb.liczba[j].potega){
                       zb.liczba[i].wspolczynnik+=zb.liczba[j].wspolczynnik;

                       for(int k=j;k<zb.ile_liczb-1;k++){
                           zb.liczba[k] = zb.liczba[k+1];
                       }
                       zb.ile_liczb--;
                   }
                   else j++;
               }
       }
       return zb;
    }
    static void wypisanie_wielomianu(ZbiorLiczb zb){
        int i=0;
        while(i<zb.ile_liczb){
                if(zb.liczba[i].wspolczynnik==1 && zb.liczba[i].potega!=0){
                    System.out.print("x");
                }
                else if(zb.liczba[i].wspolczynnik==-1 && zb.liczba[i].potega!=0){
                    System.out.print("-x");
                }
                else if(zb.liczba[i].potega==0){
                    System.out.print(zb.liczba[i].wspolczynnik);
                }

                else if(zb.liczba[i].wspolczynnik>1 || zb.liczba[i].wspolczynnik<-1){
                    System.out.print(zb.liczba[i].wspolczynnik+"x");
                }

                if(zb.liczba[i].potega>1 && zb.liczba[i].wspolczynnik!=0){
                    System.out.print("^"+zb.liczba[i].potega);
                }
                if(i<zb.ile_liczb-1){
                    if(zb.liczba[i+1].wspolczynnik>0){
                        System.out.print(" + ");
                    }
                    else{
                        System.out.print(" ");
                    }
                }
           i++;

       }
    }
    static ZbiorLiczb uzupelnienie_zbioru(ZbiorLiczb zb){
        ZbiorLiczb temp = new ZbiorLiczb(zb.stopien_wielomianu+1);
        temp.stopien_wielomianu = zb.stopien_wielomianu;
        temp.ile_liczb = zb.ile_liczb;

        for(int i=0;i<zb.ile_liczb;i++){
            temp.liczba[i] = zb.liczba[i];
        }
        zb = temp;

        boolean x = true;
        int begin_amount_of_count = zb.ile_liczb;
        for(int i=zb.stopien_wielomianu;i>=0;i--){
            for(int j=0;j<begin_amount_of_count;j++){
                if(i == zb.liczba[j].potega){
                    x=true;
                    break;
                }
                else
                    x=false;
            }
            if(!x){
                zb.liczba[zb.ile_liczb] = new Liczba(0,i);
                zb.ile_liczb++;
            }
        }
        return zb;
    }
    static ZbiorLiczb licz_Horner(ZbiorLiczb zb, int dzielnik){
        zb.sort();

        ZbiorLiczb zb2 = new ZbiorLiczb(zb.liczba.length, zb.stopien_wielomianu);

        zb2.liczba[0] = new Liczba(zb.liczba[0].wspolczynnik, zb.liczba[0].potega-1);

        for(int i=1;i<zb.liczba.length;i++){
            zb2.liczba[i] = new Liczba(zb2.liczba[i-1].wspolczynnik * dzielnik + zb.liczba[i].wspolczynnik,zb.liczba[i].potega-1);
            if(zb2.liczba[i].potega<0){
                zb2.ile_liczb--;
                zb2.reszta = zb2.liczba[i].wspolczynnik;
            }
        }

        return zb2;

    }

    public static void main(String[] args) {

        //Inicjalizacja skanera


        ZbiorLiczb zb = new ZbiorLiczb();
        //TESTY

           /*int stopien_wielomianu = 3;
            ile_liczb = 3;
            ZbiorLiczb zb = new ZbiorLiczb(ile_liczb,stopien_wielomianu);
            zb.liczba[0] = new Liczba(2,3);
            zb.liczba[1] = new Liczba(-1,1);
            zb.liczba[2] = new Liczba(3,0);
           // zb.liczba[2] = new Liczba(3,0);
            //zb.liczba[3] = new Liczba(-1,0);*/


        zb = wprowadzanie_danych_do_zbioru();
        System.out.println("Podaj dzielnik: ");
        int dzielnik = scan.nextInt();

        sprawdzanie_czy_sie_powtarzaja(zb);

        System.out.print("(");wypisanie_wielomianu(zb); System.out.print(") : (x"); if(dzielnik>=0) System.out.print(" - "); System.out.print(dzielnik+") = ");
        zb = uzupelnienie_zbioru(zb);
        zb = licz_Horner(zb,dzielnik);
        System.out.print("("); wypisanie_wielomianu(zb); System.out.print(") * (x"); if(dzielnik>=0) System.out.print(" - "); System.out.print(dzielnik+")");
        System.out.print(" Reszta: "+zb.reszta);
        System.out.println();

    }
}
