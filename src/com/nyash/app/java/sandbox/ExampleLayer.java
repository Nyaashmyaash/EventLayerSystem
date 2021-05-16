package sandbox;

import events.Dispatcher;
import events.Event;
import events.types.MouseMotionEvent;
import events.types.MousePressedEvent;
import events.types.MouseReleasedEvent;
import layers.Layer;

import java.awt.*;
import java.util.Random;

public class ExampleLayer extends Layer {

    private String name;
    private Color color;
    private Rectangle rect;
    private boolean dragging = false;
    private int pX, pY;

    private static final Random random = new Random();

    public ExampleLayer(String name, Color color) {
        this.name = name;
        this.color = color;

        rect = new Rectangle(random.nextInt(100) + 150, random.nextInt(100) + 250, 120, 240);
    }


    @Override
    public void onEvent(Event event) {
        Dispatcher dispatcher = new Dispatcher(event);
        dispatcher.dispatch(Event.Type.MOUSE_PRESSED, (Event e) -> onPressed((MousePressedEvent) e));
        dispatcher.dispatch(Event.Type.MOUSE_RELEASED, (Event e) -> onReleased((MouseReleasedEvent) e));
        dispatcher.dispatch(Event.Type.MOUSE_MOVED, (Event e) -> onMoved((MouseMotionEvent) e));
    }

    public void onRender(Graphics g) {
        g.setColor(color);
        g.fillRect(rect.x, rect.y, rect.width, rect.height);

        g.setColor(Color.WHITE);
        g.drawString(name, rect.x + 15, rect.y + 15);
    }

    private boolean onPressed(MousePressedEvent event) {
        if (rect.contains(new Point(event.getX(), event.getY())))
            dragging = true;
        return dragging;
    }

    private boolean onReleased(MouseReleasedEvent event) {
        dragging = false;
        return dragging;
    }

    private boolean onMoved(MouseMotionEvent event) {
        if (dragging == true){
            rect.x += event.getX() - pX;
            rect.y += event.getY() - pY;
        }
        pX = event.getX();
        pY = event.getY();

        return dragging;
    }
}
