/**
 *  Klasa odpowiedzialna za przechowywanie ukladu
 */

import java.util.Random;

public class Uklad {
    int n;              //rozmiar problemu
    double A[][];       //tablica przechowujaca macierz A ukladu
    double b[];         //tablica przechowujaca wektor wyrazow wolnych - b

    //Konstruktor zadajacy wylacznie rozmiar macierzy
    public Uklad(int nn){
        n = nn;
        A = new double[n][n];
        b = new double[n];
    }
    
    //Konstruktor przyjmujacy na wejsciu macierz A oraz wektor wyrazow wolnych
    public Uklad(double[][] tab_A, double[] tab_b){
        n = tab_b.length;
        A = new double[n][n];
        b = new double[n];
        for(int i = 0; i < n; i++) {
            System.arraycopy(tab_A[i], 0, A[i], 0, n);
        }
        System.arraycopy(tab_b, 0, b, 0, n);
    }
    
    //Konstruktor kopiujacy
    public Uklad(Uklad uk){
        n = uk.n;
        A = new double[n][n];
        b = new double[n];
        for(int i = 0; i < n; i++) {
            System.arraycopy(uk.A[i], 0, A[i], 0, n);
        }
        System.arraycopy(uk.b, 0, b, 0, n);
    }
    
    //Metoda losujaca macierz wspolczynnikow oraz wektor wyrazow wolnych
    public void LosujUklad(){
        Random rand = new Random();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j< n; j++) {
                A[i][j] = rand.nextDouble();
            }
            b[i] = rand.nextDouble();
        }
    }
    
    //Metoda losujaca uklad symetryczny i dodatnio okreslony
    public void LosujUkladSymetrycznyDodatnioOkreslony(){
        Random rand = new Random();
        double los, suma;
        for(int i = 0; i < n; i++) {
            for(int j = i+1; j< n; j++) {
                los = rand.nextDouble();
                A[i][j] = los;
                A[j][i] = los;
            }
            b[i] = rand.nextDouble();
            suma = 0.0;
            //Aby zapewnic sobie dominacje na przekatnej na przekatnej staje element silnie dominujacy w wierszu
            for(int j = 0; j < n; j++) suma += Math.abs(A[i][j])*(1+rand.nextDouble());
            A[i][i] = suma;
        }
    }
    
    //Metoda wyswietlajaca uklad
    public void WyswietlUklad(){
        for(int j = 0; j < n; j++) {
            for(int k = 0; k < n; k++) System.out.format("%4.3f\t",A[j][k]);
            System.out.format("\t | \t %4.3f \n",b[j]);
            }
        System.out.println();
    }
    
    //Metoda wyznaczajaca odchylenie iloczynu Ax od wektora wyrazow wolnych - blad rozwiazania
    public void SprawdzRozwiazanie(double[] x) {
        double suma, blad;
        blad = 0.0;
        for(int i = 0; i < n; i++){
            suma = b[i];
            for(int j = 0; j < n; j++) suma -= A[i][j]*x[j];
            blad += Math.abs(suma);
        }
        System.out.println("Blad rozwiazania: " + blad + "\n");
    }
}
