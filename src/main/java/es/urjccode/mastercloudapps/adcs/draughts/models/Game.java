package es.urjccode.mastercloudapps.adcs.draughts.models;

public class Game {

	private Board board;

	private Turn turn;

	private static Integer ROW_BLACK = 2;
	private static Integer ROW_WHITE = 5;
	private static Integer DIAGONAL_MOVE_EAT = 2;

	public Game() {
		this.turn = new Turn();
		this.board = new Board();
		for (int i = 0; i < this.board.getDimension(); i++) {
			for (int j = 0; j < this.board.getDimension(); j++) {
				Coordinate coordinate = new Coordinate(i, j);
				Piece piece = this.getInitialPiece(coordinate);
				if (piece != null) {
					this.board.put(coordinate, piece);
				}
			}
		}
	}

	public Piece getInitialPiece(Coordinate coordinate) {
		if (coordinate.isBlack()) {
			final int row = coordinate.getRow();
			Color color = null;
			if (row <= ROW_BLACK) {
				color = Color.BLACK;
			} else if (row >= ROW_WHITE) {
				color = Color.WHITE;
			}
			if (color != null) {
				return new Piece(color);
			}
		}
		return null;
	}

	public void move(Coordinate origin, Coordinate target) {
		assert this.handleError(origin, target) == null;
	
		if (origin.diagonalDistance(target) == 2) {
			this.board.remove(origin.betweenDiagonal(target));
		}
		this.board.move(origin, target);
		if (this.board.getPiece(target).isLimit(target)){
			this.board.remove(target);
			this.board.put(target, new Draught(Color.WHITE));
		}
		this.turn.change();
	}

	private Error handleError(Coordinate origin, Coordinate target) {
		if (!origin.isValid() || !target.isValid()) {
			return Error.OUT_COORDINATE;
		}
		if (board.isEmpty(origin)) {
			return Error.EMPTY_ORIGIN;
		}
		Color color = this.board.getColor(origin);
		if (this.turn.getColor() != color) {
			return Error.OPPOSITE_PIECE;
		}
		if (!origin.isDiagonal(target)) {
			return Error.NOT_DIAGONAL;
		}

		Piece piece = this.board.getPiece(origin);
		if (!piece.isAdvanced(origin, target)) {
			return Error.NOT_ADVANCED;
		}

		if (origin.diagonalDistance(target) >= 3) {
			return Error.BAD_DISTANCE;
		}

		if (!this.board.isEmpty(target)) {
			return Error.NOT_EMPTY_TARGET;
		}
		if (origin.diagonalDistance(target) == DIAGONAL_MOVE_EAT) {
			Coordinate between = origin.betweenDiagonal(target);
			if (this.board.getPiece(between) == null) {
				return Error.EATING_EMPTY;
			}
			this.board.remove(between);
		}
		this.board.move(origin, target);
		this.turn.change();
		return null;
	}

	public Color getColor(Coordinate coordinate) {
		return this.board.getColor(coordinate);
	}

	@Override
	public String toString() {
		return this.board + "\n" + this.turn;
	}

	public Color getColor() {
		return this.turn.getColor();
	}

	public Piece getPiece(Coordinate coordinate) {
		return this.board.getPiece(coordinate);
	}

	public boolean isBlocked() {
		return this.board.getPieces(this.turn.getColor()).isEmpty();
	}

	public int getDimension() {
		return this.board.getDimension();
	}

}