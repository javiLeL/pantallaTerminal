package reloj;

import java.time.LocalDateTime;

import pantallaTeriminal.Pantalla;

public class Main {
	public static void main(String[] args) {
		Pantalla pantalla = new Pantalla(0, 0, 30, 30);//49 cmd 
		
		Pantalla.limpiarPantalla();
		while(true) {
			pantalla.background("  ");
			
			for(int i=0;i<12;i++) {
				int tam = 13;
				double anguloInicial = 90;
				if(11-i+1<10) {
					pantalla.point((int)(tam*Math.cos(Math.toRadians(anguloInicial+i*360/12)))+15, (int)(tam*-Math.sin(Math.toRadians(anguloInicial+i*360/12)))+15,(11-i+1)+" ");
				}else {
					pantalla.point((int)(tam*Math.cos(Math.toRadians(anguloInicial+i*360/12)))+15, (int)(tam*-Math.sin(Math.toRadians(anguloInicial+i*360/12)))+15,(11-i+1)+" ");
					pantalla.point((int)(tam*Math.cos(Math.toRadians(anguloInicial+i*360/12)))+15+1, (int)(tam*-Math.sin(Math.toRadians(anguloInicial+i*360/12)))+15," ");
				}
			}
			LocalDateTime locaDate = LocalDateTime.now();
			int hours  = locaDate.getHour();
			int minutes = locaDate.getMinute();
			int seconds = locaDate.getSecond();
			//System.out.println("Hora actual : " + hours  + ":"+ minutes +":"+seconds); 
			//seg
			aguja(pantalla, 15, 15, 11,60-seconds,60);
			//min
			aguja(pantalla, 15, 15, 7, 60-minutes,60);
			//hor
			aguja(pantalla, 15, 15, 5, (24-hours)%12, 12);
			Pantalla.limpiarPantalla();
			pantalla.refrescar();
			try {
	            Thread.sleep(60);
	         } catch (Exception e) {
	            System.out.println(e);
	         }
			
		}
	}
	public static void aguja(Pantalla pantalla,int x, int y, int tam,int num ,int max) {
		pantalla.linea(x, y,(int)(tam*Math.cos(Math.toRadians(90+num*360/max)))+x, (int)(tam*-Math.sin(Math.toRadians(90+num*360/max)))+y, "* ");
	}
}