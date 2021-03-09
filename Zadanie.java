/**
 * To jest szablon do stworzenia rozwiazania
 */
public class Zadanie {
    
    int n_max;      //maksymalny rozmiar macierzy
    double[] T;     //tablica czasow do badania zbieznosci
    double[][] T2;  //tablica czasow do porownywania metod
    int[] rozm;     //tablica rozmiarow macierzy
    int M;          //liczba pomiarow w ramach jednego testu
    int N;          //liczba testow do wykonania
    
    //Konstruktor okreslajacy parametry M, N oraz maksymalny rozmiar macierzy n_max
    public Zadanie(int MM, int NN, int nn){
        M = MM;
        N = NN;
        n_max = nn;
        T = new double[N];
        T2 = new double[N][2];
        rozm = new int[N];
    }
    
    //Metoda mierzaca czas rozwiazywania zadania wybrana metoda
    public double MierzCzas(int n, int metoda){
        double czas = 0.0;
        Long pomiar;

        for(int i=0; i<M; i++){
            var uklad = new Uklad(n);
            uklad.LosujUkladSymetrycznyDodatnioOkreslony();
            var x_0 = new double [n];
            var iterp = new IteracjaProsta(uklad, x_0);
            var gauss = new Gauss(uklad);
            pomiar = System.currentTimeMillis();
            switch(metoda){
                case 1:
                    iterp.Przygotuj();
                    iterp.IterujA(0.00001, 1);
                    break;
                case 2:
                    gauss.Eliminacja();
                    gauss.RozwiazTrojkatny();
                    break;
            }
            pomiar = System.currentTimeMillis() - pomiar;
            czas += pomiar;
        }
        return czas / (M * 1000.0);                            
    }
    
    //Metoda badajaca zlozonosc wyznaczania rozwiazania zadana metoda
    public void BadajZlozonosc(int metoda, int n_min, String opis){
        
        double krok = (n_max - n_min) / (N - 1.0);              //wyznaczam krok zmiany rozmiaru macierzy
        for(int i = 0; i < N; i++){
            rozm[i] = (int) Math.round(n_min + i * krok);       //najmniejszy rozmiar macierzy to n_min
            T[i] = this.MierzCzas(rozm[i], metoda);             //zapisuje srednie czasy do tablicy
            System.out.format("%d \t %4.3f\n", rozm[i], T[i]);  //wyswietlam rozmiar i czas obliczen na wyjsciu
        }
        //tworze wykres obrazujacy zlozonosc
        Wykresik wykresik = new Wykresik(N, rozm, T, opis);
    }
    
    //Metoda porownujaca dwie metody
    public void PorownajMetody(String opis){
        
        double krok = n_max / (N - 1.0);                        //wyznaczam krok zmiany rozmiaru macierzy
        
        for(int i = 0; i < N; i++){
            rozm[i] = (int) Math.round((i + 1) * krok);         //najmniejszy rozmiar macierzy to zaokrag. zm. krok
            T2[i][0] = this.MierzCzas(rozm[i], 1);              //zapisuje srednie czasy do tablicy
            T2[i][1] = this.MierzCzas(rozm[i], 2);
            System.out.format("%d \t %4.3f \t %4.3f\n", rozm[i], T2[i][0], T2[i][1]);  //wyswietlam rozmiar i czas obliczen na wyjsciu
        }
        
        //tworze wykres obrazujacy zlozonosc
        Wykresik wykresik = new Wykresik(N, rozm, T2, opis);
    }
}
