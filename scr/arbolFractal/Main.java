package arbolFractal;

import pantallaTeriminal.Pantalla;

public class Main {
    static Pantalla pantalla = new Pantalla(0, 0, 55, 40);
    static int frameCount=0;

    public static void main(String[] args) {
        final String ANSI_GREEN = "\u001B[32m";
        while(true){
            Pantalla.limpiarPantalla();
            pantalla.background("  ");
            Arbol(pantalla.width/2, pantalla.height, 8, 90, ANSI_GREEN+"##");
            pantalla.refrescar();
            try {
                Thread.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            frameCount++;
        }
    }
    static void Arbol(int x, int y, float tam, float angulo, String c){
        int xf, yf;
        xf = (int)(x+(tam*Math.cos(Math.toRadians(angulo))));
        yf = (int)(y+(tam*-Math.sin(Math.toRadians(angulo))));
        if(tam>0){
            pantalla.linea(x, y, xf, yf, c);
            Arbol(xf, yf, (float)(tam-1), angulo+45+frameCount*2, c);
            Arbol(xf, yf, (float)(tam-1), angulo-45-frameCount*2, c);
        }
    }
}