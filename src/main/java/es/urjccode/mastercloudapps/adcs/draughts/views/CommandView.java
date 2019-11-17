package es.urjccode.mastercloudapps.adcs.draughts.views;

import es.urjccode.mastercloudapps.adcs.draughts.controllers.PlayController;
import es.urjccode.mastercloudapps.adcs.draughts.models.Error;
import es.urjccode.mastercloudapps.adcs.draughts.models.Message;
import es.urjccode.mastercloudapps.adcs.draughts.models.Coordinate;

public class CommandView extends SubView {

    public CommandView(){
        super();
    }

    public void interact(PlayController playController) {
        String color = playController.getColor();
        Error error = null;
        GameView gameView = new GameView();
        do {
            String command = this.console.readString(Message.MOVE_IT.getMessage() + color + ": ");
            int origin = Integer.parseInt(command.substring(0, 2));
            int target = Integer.parseInt(command.substring(3, 5));
            playController.move(new Coordinate(originCoordinateRow(origin), originCoordinateColumn(origin)), new Coordinate(originCoordinateRow(target), originCoordinateColumn(target)));
            if (error != null){
                console.writeln(Message.ERROR.getMessage() + error.name());
            gameView.write(playController);
            }
        } while (error != null); 
        if (playController.isBlocked()){
            this.console.write(Message.LOSE_THE_GAME.getMessage());
        }
    }

    private int originCoordinateRow(int row) {
        return row/10-1;
    }

    private int originCoordinateColumn(int column) {
        return column%10-1;
    }


}