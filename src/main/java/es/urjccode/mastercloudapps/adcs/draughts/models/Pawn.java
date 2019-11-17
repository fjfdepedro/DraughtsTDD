package es.urjccode.mastercloudapps.adcs.draughts.models;

public class Pawn extends Piece {

	Pawn(Color color) {
		super(color);
	}

	private Color color;

	public Color getColor() {
		return this.color;
	}

	boolean isLimit(Coordinate coordinate){
		return coordinate.getRow()== 0 && this.getColor() == Color.WHITE ||
		coordinate.getRow()== 7 && this.getColor() == Color.BLACK;
	}

	public boolean isAdvanced(Coordinate origin, Coordinate target) {
		int difference = origin.getRow() - target.getRow();
		if (color == Color.WHITE){
			return difference>0;
		}
		return difference<0;
	}

}