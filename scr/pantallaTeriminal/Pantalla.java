package pantallaTeriminal;

import java.util.ArrayList;

public class Pantalla {
	int xi, yi;
	public int width;
    public int height;
	String[][] pantalla;
	float df;
	int pcamx = 0, pcamy = 0;

	public Pantalla(int xi, int yi, int X, int Y) {
		this.xi = xi;
		this.yi = yi;

		width = X;
		height = Y;
		pantalla = new String[X][Y];

		// Inicializar la pantalla
		for (int x = 0; x < pantalla.length; x++) {
			for (int y = 0; y < pantalla[x].length; y++) {
				pantalla[x][y] = "";
			}
		}
	}

	public Pantalla(int xi, int yi, int X, int Y, float df) {
		this.xi = xi;
		this.yi = yi;
		this.df = df;

		width = X;
		height = Y;
		pantalla = new String[X][Y];

		// Inicializar la pantalla
		for (int x = 0; x < pantalla.length; x++) {
			for (int y = 0; y < pantalla[x].length; y++) {
				pantalla[x][y] = "";
			}
		}
	}

	public static void limpiarPantalla() {
		try {
			// new java.util.Scanner(System.in).nextLine();
			String sistemaOperativo = System.getProperty("os.name");
			ArrayList<String> comando = new ArrayList<>();
			if (sistemaOperativo.contains("Windows")) {
				comando.add("cmd");
				comando.add("/C");
				comando.add("cls");

			} else {
				comando.add("clear");
			}

			ProcessBuilder pb = new ProcessBuilder(comando);
			Process iniciarProceso = pb.inheritIO().start();
			iniciarProceso.waitFor();

		} catch (Exception e) {
			System.out.println("Error al limpiar la pantalla" + e.getMessage());
		}
	}

	public void background(String c) {
		for (int x = 0; x < pantalla.length; x++) {
			for (int y = 0; y < pantalla[x].length; y++) {
				pantalla[x][y] = c;
			}
		}
	}

	public void refrescar() {
		for (int i = 0; i < yi; i++) {
			System.out.print("\n");
		}
		for (int y = 0; y < pantalla[0].length; y++) {
			for (int i = 0; i < xi; i++) {
				System.out.print(" ");
			}
			for (int x = 0; x < pantalla.length; x++) {
				System.out.print(pantalla[x][y]);
			}
			System.out.print("\n");
		}
	}

	public void posCamara(int x, int y, int z) {
		pcamx = x;
		pcamy = y;
		df = z;
	}

	public void point(int x, int y, String c) {
		if (x >= 0 && x < width && y >= 0 && y < height) {
			pantalla[x][y] = c;
		}
	}

	public void point(int x, int y, int z, String c) {

		int px = (int) ((((df - z) * x) / df) + pcamx);
		int py = (int) ((((df - z) * y) / df) + pcamy);
		// System.out.println("puntos: " + px + " // " + py);
		if (x + px >= 0 && x + px < width && y + py >= 0 && y + py < height) {
			pantalla[x + px][y + py] = c;
		}
	}

	public void linea(int x0, int y0, int x1, int y1, String c) {
		lineaBasica(x0, y0, x1, y1, c);
		lineaBasica(x1, y1, x0, y0, c);
	}

	public void linea(int x0, int y0, int z0, int x1, int y1, int z1, String c) {

		int px0 = (int) (((df - z0) * x0) / df + pcamx);
		int py0 = (int) (((df - z0) * y0) / df + pcamy);

		int px1 = (int) (((df - z1) * x1) / df + pcamx);
		int py1 = (int) (((df - z1) * y1) / df + pcamy);

		// System.out.println("lineas: " + px0 + " // " + py0);
		// System.out.println("lineas: " + px1 + " // " + py1);

		lineaBasica(x0 + px0, y0 + py0, x1 + px1, y1 + py1, c);
		lineaBasica(x1 + px1, y1 + py1, x0 + px0, y0 + py0, c);
	}

	public void lineaBasica(int x0, int y0, int x1, int y1, String c) {
		int x, y, dx, dy, p, incE, incNE, stepx, stepy;
		dx = (x1 - x0);
		dy = (y1 - y0);

		if (dy < 0) {
			dy = -dy;
			stepy = -1;
		} else {
			stepy = 1;
		}
		if (dx < 0) {
			dx = -dx;
			stepx = -1;
		} else {
			stepx = 1;
		}
		x = x0;
		y = y0;

		if (dx > dy) {
			p = 2 * dy - dx;
			incE = 2 * dy;
			incNE = 2 * (dy - dx);
			while (x != x1) {
				x = x + stepx;
				if (p < 0) {
					p = p + incE;
				} else {
					y = y + stepy;
					p = p + incNE;
				}
				point(x, y, c);
			}
		} else {
			p = 2 * dx - dy;
			incE = 2 * dx;
			incNE = 2 * (dx - dy);
			while (y != y1) {
				y = y + stepy;
				if (p < 0) {
					p = p + incE;
				} else {
					x = x + stepx;
					p = p + incNE;
				}
				point(x, y, c);
			}
		}
		if (x0 == 0 && y0 == 0) {
			point(0, 0, c);
		}
	}

	public void rectangulo(int X, int Y, int tamX, int tamY, String c) {
		for (int x = X; x < X + tamX; x++) {
			for (int y = Y; y < Y + tamY; y++) {
				point(x, y, c);
			}
		}
	}

	public void estrella(int num, int x, int y, int tam0, int tam1, double anguloInicial, String c) {
		int n = num * 2;
		for (int i = 0; i < n; i++) {
			if (i % 2 == 0) {
				linea((int) (tam0 * Math.cos(Math.toRadians(anguloInicial + i * 360 / n))) + x,
						(int) (tam0 * -Math.sin(Math.toRadians(anguloInicial + i * 360 / n))) + y,
						(int) (tam1 * Math.cos(Math.toRadians(anguloInicial + (i + 1) % n * 360 / n))) + x,
						(int) (tam1 * -Math.sin(Math.toRadians(anguloInicial + (i + 1) % n * 360 / n))) + y, c);
			} else {
				linea((int) (tam1 * Math.cos(Math.toRadians(anguloInicial + i * 360 / n))) + x,
						(int) (tam1 * -Math.sin(Math.toRadians(anguloInicial + i * 360 / n))) + y,
						(int) (tam0 * Math.cos(Math.toRadians(anguloInicial + (i + 1) % n * 360 / n))) + x,
						(int) (tam0 * -Math.sin(Math.toRadians(anguloInicial + (i + 1) % n * 360 / n))) + y, c);
			}
		}
	}

	public void poligonos(int n, int x, int y, int tam, double anguloInicial, String c) {
		for (int i = 0; i < n; i++) {
			// pantalla.point((int)(7*Math.cos(Math.toRadians(i*360/5)))+15,
			// (int)(7*-Math.sin(Math.toRadians(i*360/5)))+15,"* ");
			linea((int) (tam * Math.cos(Math.toRadians(anguloInicial + i * 360 / n))) + x,
					(int) (tam * -Math.sin(Math.toRadians(anguloInicial + i * 360 / n))) + y,
					(int) (tam * Math.cos(Math.toRadians(anguloInicial + (i + 1) % n * 360 / n))) + x,
					(int) (tam * -Math.sin(Math.toRadians(anguloInicial + (i + 1) % n * 360 / n))) + y, c);
		}
	}
}