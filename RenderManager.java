import java.util.ArrayList;
import java.util.List;
import Interfaces.*;

public class RenderManager {
    private List<Drawable> drawables;

    public RenderManager() {
        this.drawables = new ArrayList<>();
    }

    public void register(Drawable d) {
        this.drawables.add(d);
    }

    public void unregister(Drawable d) {
        this.drawables.remove(d);
    }

    public void drawAll(long currentTime) {

        for(int i = 0; i < drawables.size(); i++) {
            Drawable d = drawables.get(i);
            d.draw(currentTime);
        }
    	GameLib.display();
    }
}
