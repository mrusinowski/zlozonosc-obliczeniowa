/**
 *  Klasa realizujaca metode Banachiewicza
 */

public class Banachiewicz {
   int n;           //rozmiar problemu
    Uklad u;        //zadany uklad rownan
    double[] x;     //wektor przechowujacy rozwiazanie
    double[][] U;   //macierz gorna
    double[] y;     //wektor pomocniczy y
       
    //Konstruktor przyjmujacy na wejsciu uklad do rozwiazania
    public Banachiewicz(Uklad uk) {
        n  = uk.n;
        u = new Uklad(uk);
        x = new double[n];
        y = new double[n];
        U = new double[n][n];    
    }  
    
    //Metoda wykonujaca rozklad macierzy A na iloczyn macierzy U^T oraz U
    public void Rozklad(){
        double wsp = Math.sqrt(u.A[0][0]);
        for(int i = 0; i < n; i++) U[0][i] = u.A[0][i]/wsp;
        for(int i = 1; i < n; i++){
            wsp = 0.0;
            for(int k = 0; k < i; k++) wsp += U[k][i]*U[k][i];
            U[i][i] = Math.sqrt(u.A[i][i] - wsp);
            for(int j = i+1; j < n ; j++){
                wsp = 0.0;
                for(int k = 0; k < i; k++) wsp += U[k][i]*U[k][j];
                U[i][j] = (u.A[i][j] - wsp)/U[i][i];
            }
        }
    }
    
    //Metoda rozwiazujaca uklad trojkatny dolny U^T Y = b
    public void RozwiazTrojkatnyDolny(){
        double suma;
        for(int i = 0; i < n; i++) {
            suma = u.b[i];
            for(int k = 0; k < i; k++) suma -= U[k][i]*y[k];
            y[i] = suma/U[i][i];
        }
    }
   
    //Metoda rozwiazujaca uklad trojkatny gorny UX = Y
    public void RozwiazTrojkatnyGorny(){
        double suma;
        for(int i = n - 1; i >= 0; i--) {
            suma = y[i];
            for(int k = n-1; k > i; k--) suma -= U[i][k]*x[k];
            x[i] = suma/U[i][i];
        }
    }
    
    //Metoda wyswietlajaca wynik rozkladu - macierz gorna U
    public void Wyswietl() {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) System.out.format("%4.3f\t",U[i][j]);
            System.out.print("\n");
        }
        System.out.print("\n");
    }
    
    //Metoda wypisujaca rozwiazanie
    public void WyswietlRozwiazanie() {
        for(int i = 0; i < n; i++) System.out.format("%4.3f\t", x[i]);
        System.out.print("\n \n");
    }
    
    //Metoda sprawdzajaca rozwiazanie
    public void SprawdzRozwiazanie() {
        u.SprawdzRozwiazanie(x);
    }
}
