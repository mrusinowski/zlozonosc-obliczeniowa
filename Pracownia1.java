/**
 * Pracownia nr 1 - badanie zlozonosci obliczeniowej i porownywanie 2 metod
 */
public class Pracownia1 {

    public static void main(String[] args) {
        
        /*SEKCJA 1 - Przyklad sortowania tablic o zadanej dlugosci  
        * ustalam minimalna dlugosc listy, ktora da niezerowy czas
        * oraz maksymalna dlugosc listy, dla ktorej sortowanie nie bedzie trwalo 
        * zbyt dlugo (mniej niz 1 s) */ 
        /*
        int n = 10;                                         //okreslam dlugosc listy
        long pomiar;                                        //zmienna przechowujaca czas systemowy
        Sortowania test1 = new Sortowania(n);               //powoluje do istnienia obietk klasy Sortowania
        
        //testuje metode sortowania przez wstawianie
        test1.Losuj(n);                                     //losuje elementy listy
        test1.WyswietlListe(n);                             //wyswietlam liste
        pomiar = System.currentTimeMillis();                //wlaczam stoper
        test1.SortujPrzezWstawianie(n);                     //wywoluje metode sortujaca liste
        pomiar = System.currentTimeMillis() - pomiar;       //zatrzymuje stoper
        test1.WyswietlListe(n);                             //wyswietlam posortowana liste
        System.out.format("%4.3f s\n\n", pomiar / 1000.0);  //wystwietlam czas sortowania
        
        //testuje metode sortowania przez wybieranie
        test1.Losuj(n);                                     //losuje elementy listy
        test1.WyswietlListe(n);                             //wyswietlam liste
        pomiar = System.currentTimeMillis();                //wlaczam stoper
        test1.SortujPrzezWybieranie(n);                     //wywoluje metode sortujaca liste
        pomiar = System.currentTimeMillis() - pomiar;       //zatrzymuje stoper
        test1.WyswietlListe(n);                             //wyswietlam posortowana liste
        System.out.format("%4.3f s\n\n", pomiar / 1000.0);  //wystwietlam czas sortowania
        */
                
        /*SEKCJA 2 - Badamy zlozonosc obliczeniowa obu metod dla M = 7, N = 77, 
        * n_min = 500, n_max = 10000*/
        /*
        int M = 7;
        int N = 77;
        int n_min = 500;
        int n_max = 10000;
        Sortowania test2 = new Sortowania(M, N, n_max);
        System.out.println("Sortowanie przez wstawianie:");
        test2.BadajZlozonosc(1, n_min);
        //System.out.println("Sortowanie przez wybieranie:");
        //test2.BadajZlozonosc(2, n_min);
        */
        /*
        //SEKCJA 3 - Porownujemy obie metody dla M = 7, N = 77, n_max = 10000
        int M = 6;
        int N = 23;
        int n_max = 10000;
        Sortowania test3 = new Sortowania(M, N, n_max);
        test3.PorownajMetody();
        */
        
        //SEKCJA 4 - miejsce na rozwiazanie - cz 1. - okreslenie n_min i n_max
        
        /* zadajemy rozmiar ukladu
        int range = n_max - n_min;
        int norma = 1;
        double esp = 0.00001;
        var x_0 = new double[];
        powolujemy do istnienia obiekt klasy Uklad
        Uklad uklad = new Uklad((int) Math.random() * range + n_min);
        losujemy odpowiedni uklad rownan
        uklad.LosujUklad();
        powolujemy do istnienia obiekt klasy zwiazanej z badana metoda
        IteracjaProsta zad1 = new IteracjaProsta(uklad, x_0);
        uruchamiamy stoper
        long pomiar = System.currentTimeMillis();
        wywolujemy odpowiednie metody, ktore pozwalaja rozwiazac dany uklad
        zad1.Przygotuj();
        zad1.IterujA(esp, norma);
        zatrzymujemy stoper
        pomiar = System.currentTimeMillis() - pomiar;
        System.out.format("Czas rozwiazywania: %4.3f\n", pomiar/1000.0);
        */
        
                
        //SEKCJA 5 - miejsce na rozwiazanie - cz 2. - badanie zlozonosci obliczeniowej i porowanie metod
        
        //zadajemy miniamlny i maksymalny rozmiar macierzy
        int n_min = 300;
        int n_max = 1400;
        //zadajemy liczbe eksperymentow, ktore wykonamy dla zadanej wielkosci ukladu 
        int M = 6;
        //zadajemy liczbe roznych rozmiarow macierzy, ktore bedziemy rozwazac
        int N = 23;
        
        //tworzymy obiekt klasy Zadanie i wywolujemy go z odpowiednimi parametrami
        Zadanie zad1 = new Zadanie(M, N, n_max);
        //Badamy zlozonosc obliczeniowa metody
        zad1.BadajZlozonosc(1, n_min, "Badanie zlozonosci obliczeniowej metodą iteracji Prostej");
        //porownujemy dwie metody
        zad1.PorownajMetody("Porownanie metody iteracji prostej i Gaussa");
    }
}