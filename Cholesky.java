/**
 *  Klasa realizujaca metode Cholesky'ego
 */

public class Cholesky {
    int n;          //rozmiar problemu
    Uklad u;        //zadany uklad rownan
    double[] x;     //wektor przechowujacy rozwiazanie
    double[][] L;   //macierz dolna
    double[][] U;   //macierz gorna
    double[] y;     //wektor pomocniczy y
    
   
    //Konstruktor przyjmujący na wejściu uklad do rozwiazania
    public Cholesky(Uklad uk) {
        n  = uk.n;
        u = new Uklad(uk);
        x = new double[n];
        y = new double[n];
        U = new double[n][n];
        L = new double[n][n];
    }  
    
    //Metoda rozkladajaca macierz A na iloczyn macierzy L i U
    public void Rozklad(){
        double wsp = u.A[0][0];
        for(int i = 0; i < n; i++){
            L[i][0] = u.A[i][0];
            U[i][i] = 1.0;
            U[0][i] = u.A[0][i]/wsp;
        }
        for(int i = 1; i < n; i++){
            wsp = 0.0;
            for(int k = 0; k < i; k++) wsp += L[i][k]*U[k][i];
            L[i][i] = u.A[i][i] - wsp;
            for(int j = i+1; j < n ; j++){
                wsp = 0.0;
                for(int k = 0; k < i; k++) wsp += L[i][k]*U[k][j];
                U[i][j] = (u.A[i][j] - wsp)/L[i][i];
                wsp = 0.0;
                for(int k = 0; k < i; k++) wsp += L[j][k]*U[k][i];
                L[j][i] = (u.A[j][i] - wsp);
            }
        }
    }
    
    //Metoda rozwiazujaca uklad trojkatny dolny LY = B
    public void RozwiazTrojkatnyDolny(){
        double suma;
        for(int i = 0; i < n; i++) {
            suma = u.b[i];
            for(int k = 0; k < i; k++) suma -= L[i][k]*y[k];
            y[i] = suma/L[i][i];
        }
    }
    
    //Metoda rozwiazaujaca uklad trojkatny gorny UX = Y
    public void RozwiazTrojkatnyGorny(){
        double suma;
        for(int i = n - 1; i >= 0; i--) {
            suma = y[i];
            for(int k = n-1; k > i; k--) suma -= U[i][k]*x[k];
            x[i] = suma;
        }
    }
    
    //Metoda wyswietlajaca rozklad Cholesky'ego
    public void Wyswietl() {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) System.out.format("%4.3f\t",L[i][j]);
            System.out.format("\t");
            for(int j = 0; j < n; j++) System.out.format("%4.3f\t",U[i][j]);
            System.out.print("\n");
        }
        System.out.print("\n");
    }
    
    //Metoda wyswietlajaca rozwiazanie
    public void WyswietlRozwiazanie() {
        for(int i = 0; i < n; i++) System.out.format("%4.3f\t", x[i]);
        System.out.print("\n \n");
    }
    
    //Metoda sprawdzajaca rozwiazanie
    public void SprawdzRozwiazanie() {
        u.SprawdzRozwiazanie(x);
    }
}
