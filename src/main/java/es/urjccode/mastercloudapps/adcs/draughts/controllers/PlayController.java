package es.urjccode.mastercloudapps.adcs.draughts.controllers;

import es.urjccode.mastercloudapps.adcs.draughts.models.Coordinate;
import es.urjccode.mastercloudapps.adcs.draughts.models.Piece;
import es.urjccode.mastercloudapps.adcs.draughts.models.State;
import es.urjccode.mastercloudapps.adcs.draughts.models.Game;
import es.urjccode.mastercloudapps.adcs.draughts.models.Error;


public class PlayController extends Controller {

    public PlayController(Game game, State state) {
		super(game, state);
	}

	public void move(Coordinate origin, Coordinate target){
		this.game.move(origin, target);
		if (this.game.isBlocked()){
			this.state.next();
		}
    }

	public Piece getPiece(Coordinate coordinate) {
		return game.getPiece(coordinate);
	}

	public String getColor() {
		return game.getColor().getColorName();
	}
	
	public boolean isBlocked() {
		return game.isBlocked();
	}	

	@Override
	public void accept(ControllersVisitor controllersVisitor) {
		controllersVisitor.visit(this);
	}

	public Error isCorrect(Coordinate origin, Coordinate target) {
		assert origin != null;
		assert target != null;
		return this.game.isCorrect(origin, target);
	}

}