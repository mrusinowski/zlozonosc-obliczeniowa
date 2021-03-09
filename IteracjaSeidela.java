/**
 *  Klasa realizujaca metode iteracji Seidela
 */

import java.util.*;

public class IteracjaSeidela {
    
    Uklad u;                //uklad do rozwiazania
    int n;                  //rozmiar problemu
    double[] x;             //wektor biezacego rozwiazania
    double[] x_st;          //wektor poprzedniego rozwiazania
    List<Double> norma_x;   //lista norm 
    double[][] D;           //macierz D
    double[] C;             //wektor C
    double[] X0;            //wektor startowy
      
    //Konstruktor zadjacy uklad wejsciowy
    public IteracjaSeidela(Uklad uk, double[] X_start) {
        n = uk.n;
        u = new Uklad(uk);
        X0 = new double[n];
        System.arraycopy(X_start, 0, X0, 0, n);
    }
    
    //Metoda obliczajaca macierz D oraz wektor C
    public void Przygotuj(){
        D = new double[n][n];
        C = new double[n];
        x = new double[n];
        x_st = new double[n];
        norma_x = new ArrayList<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++) D[i][j] = - u.A[i][j] / u.A[i][i];
            D[i][i] = 0.0;
            C[i] = u.b[i] / u.A[i][i];
        }
    }
    
    //Metoda wykonujaca zadana liczbe iteracji, zaczyna od X0
    //parametr norma - opisany jest ponizej
    public void Iteruj(int iteracje, int norma, int wyswietlaj) {
        int k = 0;
        System.arraycopy(X0, 0, x, 0, n);
        norma_x.add(NormaWektora(norma, x));
        //System.out.format("%d \t %4.4f\n",k, norma_x.get(k));
        while(k < iteracje){
            for(int i = 0; i < n; i++) {
                x[i] = C[i];
                for(int j = 0; j < n; j++) x[i] += D[i][j] * x[j];
            }
            k++;
            norma_x.add(NormaWektora(norma, x));
            //System.out.format("%d \t %4.4f\n",k, norma_x.get(k));
            if(wyswietlaj == 1) WyswietlRozwiazanie();
        }
    }
    
    /*Metoda wykonujaca iteracje do momentu, gdy norma roznicy kolejnych rozwiazan 
     * jest nie wieksza niz eps, zaczyna od X0 */
    //parametr norma - opisany jest ponizej
    public void IterujA(double eps, int norma) {
        System.arraycopy(X0, 0, x, 0, n);
        System.arraycopy(X0, 0, x_st, 0, n);
        double blad = 100.0;
        int k = 0;
        norma_x.add(NormaWektora(norma, x));
        //System.out.format("%d \t %4.4f\n",k, norma_x.get(k));
        while(blad > eps){
            k++;
            for(int i = 0; i < n; i++) {
                x[i] = C[i];
                for(int j = 0; j < n; j++) x[i] += D[i][j] * x[j];
            }  
            blad = NormaRoznicy(norma, x, x_st);
            norma_x.add(NormaWektora(norma, x));
            //System.out.format("%d \t %4.4f\n",k, norma_x.get(k));
            System.arraycopy(x, 0, x_st, 0, n);
        }
    }
       
    //Metoda wyznaczajaca norme wierszowa macierzy D, przyjmuje parametr rodzaj:
    //0 - norma nieskonczonosc
    //1 - norma kolumnowa
    //2 - norma 2 (pierwiastek sumy kwadratow wspolrzednych - dla uproszczenia,
    //              powinna byc norma spektralna, ale trudniej ja wyznaczyc)
    public double NormaMacierzy(int norma) {
        double norm, suma;
        norm = 0.0;
        switch (norma) {
            case 0: for(int i = 0; i < n; i++) {
                        suma = 0.0;
                        for(int j = 0; j < n; j++) suma += Math.abs(D[i][j]);
                        if(suma > norm) norm = suma;
                    }
                    break;
            case 1: for(int i = 0; i < n; i++) {
                        suma = 0.0;
                        for(int j = 0; j < n; j++) suma += Math.abs(D[j][i]);
                        if(suma > norm) norm = suma;
                    }
                    break;
            case 2: suma = 0.0;
                    for(int i = 0; i < n; i++) {
                        for(int j = 0; j < n; j++) suma += D[i][j] * D[i][j];
                    }
                    norm = Math.sqrt(suma);
                    break;
            }     
        return norm;
    }
    
    //Metoda wyznaczajaca norme wektor, przyjmuje parametr rodzaj:
    //0 - norma nieskonczonosc
    //1 - norma kolumnowa
    //2 - norma euklidesowa
    public double NormaWektora(int norma, double[] y) {
        double norm = 0.0;
        switch (norma) {
            case 0: norm = y[0];   
                    for(int i = 1; i < y.length; i++) if(Math.abs(y[i]) > norm) norm = Math.abs(y[i]);
                    break;
            case 1: for(int i = 0; i < y.length; i++) norm += Math.abs(y[i]);
                    break;
            case 2: for(int i = 0; i < y.length; i++) norm += y[i] * y[i];
                    norm = Math.sqrt(norm);
                    break;
        }
        return norm;
    }
    
    //Metoda wyznaczajaca norme roznicy dwoch wektorow, argument rodzaj - j.w.
    public double NormaRoznicy(int rodzaj, double[] x1, double[] x2){
        double[] x0 = new double[x1.length];
        for(int i = 0; i < x1.length; i++) x0[i] = x1[i] - x2[i];
        return NormaWektora(rodzaj, x0);
    }
    
    //Metoda wyswietlajaca macierz D oraz wektor C
    public void Wyswietl() {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) System.out.format("%4.4f\t",D[i][j]);
            System.out.format("\t | \t %4.4f\t",C[i]);
            System.out.print("\n");
        }
        System.out.print("\n");
    }
    
    //Metoda wypisujaca aktualne rozwiazanie
    public void WyswietlRozwiazanie() {
        for(int i = 0; i < n; i++) System.out.format("%4.3f\t", x[i]);
        System.out.print("\n");
    }
    
    //Metoda sprawdzajaca poprawnosc aktualnego rozwiazania
    public void SprawdzRozwiazanie(){
        u.SprawdzRozwiazanie(x);
        System.out.print("\n");
    }
}
