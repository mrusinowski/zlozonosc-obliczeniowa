/**
 *  Klasa realizujaca metode eliminacji Gaussa
 */

public class Gauss {
    int n;          //rozmiar problemu
    Uklad u;        //zadany uklad rownan
    double[] x;     //wektor przechowujacy rozwiazanie
       
    //Konstruktor przyjmujący na wejściu uklad do rozwiazania
    public Gauss(Uklad u1) {
        n  = u1.n;
        u = new Uklad(u1);
        x = new double[n];
    }  
    
    //Metoda wykonujaca operacje elementarne na wierszach zadanego ukladu
    public void Eliminacja(){
        double wsp;
        int k;
        for(int i = 0; i < n; i++){
            //sprawdzam, czy na przekatnej nie mam zera
            if (u.A[i][i] == 0){
                //jesli tak - szukam w tej koloumnie ponizej wiersza o niezerowym wspolczynniku
                k = i+1;
                while(k < n && u.A[k][i] == 0) k++;
                //jezeli nie znalazlem elementu niezerowego - wychodze z petli for
                if(k == n){
                    System.out.println("Uklad nie jest oznaczony.\n");
                    break;
                } 
                //jezeli znalazlem elment niezerowwy - zamieniam miejscami wiersze
                for(int l = i; l < n; l++){
                    wsp = u.A[i][l];
                    u.A[i][l] = u.A[k][l];
                    u.A[k][l] = wsp;
                }
                wsp = u.b[i];
                u.b[i] = u.b[k];
                u.b[k] = wsp;
            }
            //wykonuje eliminacje elmentow pod przekatna
            for(int j = i+1; j < n ; j++){
                wsp = u.A[j][i] / u.A[i][i];
                for(int m = i; m < n; m++) u.A[j][m] -= wsp * u.A[i][m]; 
                u.b[j] -= wsp * u.b[i];
            }
            //ponizsza linia umozliwia obserwacje kolejnych faz procesu eliminacji
            //u.WyswietlUklad();
        }
    }
    
    //Metoda rozwiazujaca uzyskany w wyniku eliminacji uklad trojkatny gorny
    public void RozwiazTrojkatny(){
        double suma;
        for(int i = n - 1; i >= 0; i--) {
            if(u.A[i][i] == 0) {
                System.out.println("Uklad trojkatny nieoznaczony.\n");
                break;
            }
            suma = u.b[i];
            for(int k = n-1; k > i; k--) suma -= u.A[i][k] * x[k];
            x[i] = suma / u.A[i][i];
        }
    }
    
    //Metoda wyswietlajaca przeksztalcany uklad 
    public void Wyswietl() {
        u.WyswietlUklad();
    }
    
    //Metoda wyswietlajaca wektor rozwiazania
    void WyswietlRozwiazanie() {
        for(int i = 0; i < n; i++) System.out.format("%4.3f\t", x[i]);
        System.out.print("\n\n");
    }
    
    //Metoda sprawdzajaca poprawnosc rozwiazania
    public void SprawdzRozwiazanie() {
        u.SprawdzRozwiazanie(x);
     }
}