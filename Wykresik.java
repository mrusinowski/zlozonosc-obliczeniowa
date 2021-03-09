/**
 *
 *  Klasa realizujaca ramke wykresu
 */

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Wykresik extends JFrame {

    //Konstruktor tworzacy wykres zaleznosci T(n) oraz zwracajacy oszacowany wykladnik zlozonosci
    public Wykresik(int N, int[] n, double[] T, String opis){
        super(opis);
        JPanel panel;
        panel = new Szablon1(N,n,T);
        add(panel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    //Konstruktor tworzacy wykres zaleznosci T(n) dla 2 metod, pierwsza metoda - czerwone punkty, druga - niebieskie
    public Wykresik(int N, int[] n, double[][] T, String opis){
        super(opis);
        JPanel panel;
        panel = new Szablon2(N,n,T);
        add(panel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
