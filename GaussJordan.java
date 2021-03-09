/**
 *  Klasa realizujaca metode eliminacji Gaussa-Jordana
 */

public class GaussJordan {
    int n;          //rozmiar problemu
    Uklad u;        //zadany uklad rownan
    double[] x;     //wektor przechowujacy rozwiazanie
    
    //Konstruktor przyjmujacy na wejsciu uklad do rozwiazania
    public GaussJordan(Uklad u1) {
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
            //dziele wiersz wiodacy przez element przekatnej  
            wsp = u.A[i][i];
            for(int l = i; l < n; l++) u.A[i][l] /= wsp; 
            u.b[i] /= wsp;
            //wykonuje eliminacje nad przekatna
            for(int j = 0; j < i ; j++){
                wsp = u.A[j][i];
                for(int l = i; l < n; l++) u.A[j][l] -= wsp * u.A[i][l]; 
                u.b[j] -= wsp * u.b[i];
            }
            //wykonuje eliminacje pod przekatna
            for(int j = i+1; j < n ; j++){
                wsp = u.A[j][i];
                for(int l = i; l < n; l++) u.A[j][l] -= wsp * u.A[i][l];
                u.b[j] -= wsp * u.b[i];
            }
            //ponizsza linia umozliwia obserwacje kolejnych faz procesu eliminacji
            //u.WyswietlUklad();
        }
    }
    
    //Metoda wyswietlajaca przeksztalcany uklad
    public void Wyswietl() {
        u.WyswietlUklad();
    }
    
    //Metoda wyswietlajaca wektor rozwiazania
    public void WyswietlRozwiazanie() {
        for(int i = 0; i < n; i++) System.out.format("%4.3f\t", u.b[i]);
        System.out.print("\n");
    }
    
    //Metoda sprawdzajaca poprawnosc rozwiazania
    public void SprawdzRozwiazanie() {
        u.SprawdzRozwiazanie(x);
    }
}
