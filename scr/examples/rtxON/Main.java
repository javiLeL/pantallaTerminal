package examples.rtxON;

import pantallaTeriminal.Pantalla;

public class Main {
    static Pantalla pantalla = new Pantalla(0, 0, 60, 40, 10);
    static int frameCount = 0;

    public static void main(String[] args) {
        // double[][][] cubo = { { { 5, 5, -5 }, { 5, -5, -5 }, { -5, -5, -5 }, { -5, 5,
        // -5 } },
        // { { 5, 5, 5 }, { 5, -5, 5 }, { -5, -5, 5 }, { -5, 5, 5 } } };
        do {
            double[][][] cubo = {
                    { { Math.cos(Math.toRadians(45 + frameCount)) * 8, 5,
                            -Math.sin(Math.toRadians(45 + frameCount)) * 8 },
                            { Math.cos(Math.toRadians(45 + frameCount)) * 8, -5,
                                    -Math.sin(Math.toRadians(45 + frameCount)) * 8 },
                            { -Math.cos(Math.toRadians(45 - frameCount)) * 8, -5,
                                    -Math.sin(Math.toRadians(45 - frameCount)) * 8 },
                            { -Math.cos(Math.toRadians(45 - frameCount)) * 8, 5,
                                    -Math.sin(Math.toRadians(45 - frameCount)) * 8 } },

                    { { Math.cos(Math.toRadians(45 - frameCount)) * 8, 5,
                            Math.sin(Math.toRadians(45 - frameCount)) * 8 },
                            { Math.cos(Math.toRadians(45 - frameCount)) * 8, -5,
                                    Math.sin(Math.toRadians(45 - frameCount)) * 8 },
                            { -Math.cos(Math.toRadians(45 + frameCount)) * 8, -5,
                                    Math.sin(Math.toRadians(45 + frameCount)) * 8 },
                            { -Math.cos(Math.toRadians(45 + frameCount)) * 8, 5,
                                    Math.sin(Math.toRadians(45 + frameCount)) * 8 } } };
            Pantalla.limpiarPantalla();
            pantalla.background("  ");
            pantalla.posCamara(pantalla.width / 2, pantalla.height / 2, 10);

            for (int i = 0; i < cubo.length; i++) {
                for (int j = 0; j < cubo[0].length; j++) {
                    pantalla.linea((int) cubo[i][j][0], (int) cubo[i][j][1], (int) cubo[i][j][2],
                            (int) cubo[i][(j + 1) % 4][0], (int) cubo[i][(j + 1) % 4][1], (int) cubo[i][(j + 1) % 4][2],
                            "* ");
                    pantalla.linea((int) cubo[0][j][0], (int) cubo[0][j][1], (int) cubo[0][j][2],
                            (int) cubo[1][j][0], (int) cubo[1][j][1], (int) cubo[1][j][2], "* ");
                }
            }
            // pantalla.point((int) (Math.cos(Math.toRadians(45 + frameCount)) * 8),
            // -5, (int) (-Math.sin(Math.toRadians(45 + frameCount)) * 8), "# ");
            pantalla.refrescar();
            try {
                Thread.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            frameCount++;
        } while (true);
    }
}