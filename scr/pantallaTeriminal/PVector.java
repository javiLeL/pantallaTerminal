package pantallaTeriminal;

public class PVector {
    public float x;
    public float y;
    public float z;

    public PVector(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void cambiar(int x, int y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void add(PVector mas) {
        x += mas.x;
        y += mas.y;
        z += mas.z;
    }

    public void limit(int e) {
        int f = modulo() / e;
        if (f != 0) {
            x /= f;
            y /= f;
        }
    }

    public int modulo() {
        return (int) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
}