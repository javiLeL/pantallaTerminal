package arbolFractal;

import pantallaTeriminal.Pantalla;

public class Main {
    static String keyPressed = "";
    static Pantalla pantalla = new Pantalla(0, 0, 55, 40);
    static int frameCount=0;

    public static void main(String[] args) {
        while(true){
            Pantalla.limpiarPantalla();
            pantalla.background("  ");
            Arbol(pantalla.width/2, pantalla.height, 8, 90);
            pantalla.refrescar();
            try {
                Thread.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            frameCount++;
        }
    }
    static void Arbol(int x, int y, float tam, float angulo){
        int xf, yf;
        xf = (int)(x+(tam*Math.cos(Math.toRadians(angulo))));
        yf = (int)(y+(tam*-Math.sin(Math.toRadians(angulo))));
        if(tam>0){
            pantalla.linea(x, y, xf, yf, "##");
            Arbol(xf, yf, (float)(tam-1), angulo+45+frameCount*2);
            Arbol(xf, yf, (float)(tam-1), angulo-45-frameCount*2);
        }
    }
}