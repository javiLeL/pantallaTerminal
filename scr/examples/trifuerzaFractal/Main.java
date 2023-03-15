package examples.trifuerzaFractal;

import pantallaTeriminal.Pantalla;

public class Main {
    static Pantalla pantalla = new Pantalla(0, 0, 80, 80);
    static int frameCount=0;
    public static void main(String[] args) {
        do{
            Pantalla.limpiarPantalla();
            pantalla.background("  ");
            trinagulo(pantalla.width/2, pantalla.height/2, 40-frameCount, "**");

            pantalla.refrescar();
            try {
                Thread.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            frameCount++;
        }while(false);
    }

    static void trinagulo(int x, int y, int tam, String c) {
        pantalla.poligonos(3, x, y, tam, 90, c);
        if (tam > 5) {
            pantalla.poligonos(3, x, y, tam / 2, 30, c);
            for (int i = 0; i < 3; i++) {
                int xf = ((x-((int) (tam * Math.cos(Math.toRadians(30 + i * 360 / 3))) + x))/2)+(int) (tam * Math.cos(Math.toRadians(30 + i * 360 / 3))) + x;
                int yf = ((y-((int) (tam * Math.sin(Math.toRadians(30 + i * 360 / 3))) + y))/2)+(int) (tam * Math.sin(Math.toRadians(30 + i * 360 / 3))) + y;

                trinagulo(xf, yf, tam / 2, c);
            }
        }
    }
}