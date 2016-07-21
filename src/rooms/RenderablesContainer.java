package rooms;

import trash.RenderObject;
import trash.Renderable;

public class RenderablesContainer extends Container<Renderable> implements Renderable{

	@Override
	public void render(RenderObject r) {
		for (Renderable renderable : this ) {
			renderable.render(r);

		}
		
	}

	
}
