/**
 *  Klasa rysujaca szablon wykresu dla jednej zmiennej
 */

import java.awt.*;
import java.awt.geom.*;
import javax.swing.JPanel;

public class Szablon2 extends JPanel {
    
    int width;
    int height; 
    int MAX_n;
    double MAX_T;
    int dostY;
    int N;  
    int[] n;
    double[][] T;
    
    public Szablon2() {
        width = 800;
        height = 600;
        MAX_n = 100;
        MAX_T = 10.0;   
        setPreferredSize(new Dimension(width,height));
    }
    
    public Szablon2(int w, int h) {
        width = w;
        height = h;
        setPreferredSize(new Dimension(width,height));
    }
    
    /*Konstruktor przyjmujący:
     *  nn - liczba danych
     *  dane_n - wskaźnik na tablicę rozmiarów
     *  dane_T - wskaźnik na tablicę czasów
     *  alpha - oczekiwany współczynnik 
    */
    public Szablon2(int nn, int[] dane_n, double[][] dane_T) {
        width = 800;
        height = 600;
        N = dane_n.length;
        n = new int[N];
        T = new double[N][2];
        for(int i = 0; i < N; i++) {
            n[i] = dane_n[i];
            T[i][0] = dane_T[i][0];
            T[i][1] = dane_T[i][1];
        }
        setPreferredSize(new Dimension(width,height));
    }
    
    public void getMax(){
        double temp;
        MAX_n = n[0];
        MAX_T = Math.max(T[0][0],T[0][1]);
        for(int i = 1; i < n.length; i++) {
            if(n[i] > MAX_n) MAX_n = n[i];
            temp = Math.max(T[i][0], T[i][1]);
            if(temp > MAX_T) MAX_T = temp;
        }
        //dopasowanie skali na osi Y
        if(MAX_T < 1) dostY = 0;
        else if (MAX_T < 10) {
            dostY = 1;
            MAX_T = Math.ceil(MAX_T);
        }
        else if (MAX_T < 100) {
            dostY = 2;
            MAX_T = 5*Math.ceil(MAX_T/5.0);
        }
        else {
            dostY = 3;
            MAX_T = 50*Math.ceil(MAX_T/50.0);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        super.setBackground(Color.WHITE);
        Graphics2D g2d = (Graphics2D) g;
        int dist = 10;
        
        //Osie
        Line2D osx = new Line2D.Double(4*dist, height-4*dist, width-2*dist, height-4*dist); 
        Line2D osxau = new Line2D.Double(width-3*dist, height-5*dist,width-2*dist, height-4*dist); 
        Line2D osxad = new Line2D.Double(width-3*dist, height-3*dist,width-2*dist, height-4*dist); 
        Line2D osy = new Line2D.Double(5*dist, height-3*dist, 5*dist, 2*dist);
        Line2D osyal = new Line2D.Double(4*dist, 3*dist, 5*dist, 2*dist);
        Line2D osyar = new Line2D.Double(6*dist, 3*dist, 5*dist, 2*dist);   
        g2d.draw(osx);
        g2d.draw(osxau);
        g2d.draw(osxad);
        g2d.draw(osy);
        g2d.draw(osyal);
        g2d.draw(osyar);
        
        //Podziałka
        double dx = (width - 9*dist)/5.0;
        double dy = (height - 8*dist)/5.0;
        Line2D podzx = new Line2D.Double();
        Line2D podzy = new Line2D.Double();
        for(int i = 1; i < 6; i++){
            podzx.setLine(5*dist + i*dx, height-3.5*dist, 5*dist + i*dx, height-4.5*dist);
            podzy.setLine(4.5*dist, height-4*dist - i*dy, 5.5*dist, height-4*dist - i*dy);
            g2d.draw(podzx);
            g2d.draw(podzy);
        }
        
        //Opisy podziałki
        getMax();
        int dn = (int)(MAX_n/5.0);
        double dt = MAX_T/5.0;
        for(int i = 0; i < 6; i++) g2d.drawString(Integer.toString(dn*i), (float)(4.5*dist + i*dx), height-2*dist);
        switch(dostY){
            case 0:
                for(int i = 0; i < 6; i++) g2d.drawString(String.format("%4.3f",dt*i), 0, (float)(height-3.5*dist-i*dy));
                break;
            case 1:
                for(int i = 0; i < 6; i++) g2d.drawString(String.format("%4.2f",dt*i), 0, (float)(height-3.5*dist-i*dy));
                break;
            case 2:
                for(int i = 0; i < 6; i++) g2d.drawString(String.format("%4.1f",dt*i), 0, (float)(height-3.5*dist-i*dy));
                break;
            default:
                for(int i = 0; i < 6; i++) g2d.drawString(String.format("%4.0f",dt*i), 0, (float)(height-3.5*dist-i*dy));
                break;
        }
                
        //Rysujemy wykres 
        Ellipse2D punkt = new Ellipse2D.Double();
        double scn = (width-9.0*dist)/MAX_n;
        double scT = (height-8.0*dist)/MAX_T;
        
        for(int i = 0; i<n.length; i++){
            g2d.setPaint(Color.RED);
            punkt.setFrame(scn*n[i]+5*dist-3,height-4*dist-scT*T[i][0]-3,6,6);
            g2d.fill(punkt);
            g2d.draw(punkt); 
            g2d.setPaint(Color.BLUE);
            punkt.setFrame(scn*n[i]+5*dist-3,height-4*dist-scT*T[i][1]-3,6,6);
            g2d.fill(punkt);
            g2d.draw(punkt); 
        }   
    }
}
