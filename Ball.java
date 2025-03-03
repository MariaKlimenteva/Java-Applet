public class Ball {
    private int x, y;       // Координаты центра шарика
    private int vx, vy;     // Скорость по осям X и Y
    private int radius;     // Радиус шарика

    public Ball(int x, int y, int vx, int vy, int radius) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.radius = radius;
    }

    public void move() {
        x += vx;
        y += vy;
    }

    public void checkCollision(int width, int height) {
        // Отражение от стенок
        if (x - radius < 0 || x + radius > width) {
            vx = -vx;
        }
        if (y - radius < 0 || y + radius > height) {
            vy = -vy;
        }
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getRadius() { return radius; }
}