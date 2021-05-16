import core.Window;
import sandbox.ExampleLayer;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Window window = new Window("Window",960,640);
        window.addLayer(new ExampleLayer("Layer-1", Color.blue));
        window.addLayer(new ExampleLayer("Layer-2", Color.RED));
        window.addLayer(new ExampleLayer("Layer-2", Color.BLACK));
    }
}
