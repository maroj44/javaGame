package mainPackage;

public class GrassTile extends Tile {

	public GrassTile(int id) {
		super(Assets.grass, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}

}
