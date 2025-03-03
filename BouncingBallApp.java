import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BouncingBallApp extends JPanel implements ActionListener {
    private Ball ball;
    private Timer timer;

    public BouncingBallApp() {
        ball = new Ball(100, 100, 2, 2, 20); // Начальные параметры шарика
        timer = new Timer(10, this); // Таймер для обновления анимации
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Градиентный фон
        Graphics2D g2d = (Graphics2D) g;
        Color color1 = new Color(200, 200, 255); // Светло-голубой
        Color color2 = new Color(100, 100, 200); // Темно-голубой
        GradientPaint gradient = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // int shadowRadius = ball.getRadius() + 5; // Тень немного больше шарика
        // int shadowAlpha = 50; // Прозрачность тени (0-255)
        // g.setColor(new Color(0, 0, 0, shadowAlpha));
        // g.fillOval(ball.getX() - shadowRadius, ball.getY() + 5, // Смещение тени вниз
        //         2 * shadowRadius, 2 * shadowRadius);

        // g.setColor(Color.BLUE);
        // g.fillOval(ball.getX() - ball.getRadius(), ball.getY() - ball.getRadius(),
        //         2 * ball.getRadius(), 2 * ball.getRadius()); // Рисуем шарик

        // // Отрисовка блика
        // int highlightRadius = ball.getRadius() / 3; // Размер блика
        // g.setColor(new Color(255, 255, 255, 100)); // Белый цвет с прозрачностью
        // g.fillOval(ball.getX() - ball.getRadius() / 2, ball.getY() - ball.getRadius() / 2,
        //         highlightRadius, highlightRadius);


        // Рассчитываем размер шарика в зависимости от положения
        int baseRadius = ball.getRadius();
        int dynamicRadius = (int) (baseRadius * (1 - 0.2 * Math.sin(ball.getX() / 100.0)));

        // Отрисовка тени
        int shadowRadius = dynamicRadius + 5; // Тень немного больше шарика
        int shadowAlpha = 50; // Прозрачность тени (0-255)
        g.setColor(new Color(0, 0, 0, shadowAlpha));
        g.fillOval(ball.getX() - shadowRadius, ball.getY() + 5, // Смещение тени вниз
                2 * shadowRadius, 2 * shadowRadius);

        // Отрисовка шарика с динамическим размером
        g.setColor(Color.BLUE);
        g.fillOval(ball.getX() - dynamicRadius, ball.getY() - dynamicRadius,
                2 * dynamicRadius, 2 * dynamicRadius);

        // Отрисовка блика
        int highlightRadius = dynamicRadius / 3; // Размер блика
        g.setColor(new Color(255, 255, 255, 100)); // Белый цвет с прозрачностью
        g.fillOval(ball.getX() - dynamicRadius / 2, ball.getY() - dynamicRadius / 2,
                highlightRadius, highlightRadius);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ball.move(); // Двигаем шарик
        ball.checkCollision(getWidth(), getHeight()); // Проверяем столкновения
        repaint(); // Перерисовываем панель
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Движение шарика");

        BouncingBallApp panel = new BouncingBallApp();
        frame.add(panel);
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
