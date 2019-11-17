package es.urjccode.mastercloudapps.adcs.draughts.views;

import es.urjccode.mastercloudapps.adcs.draughts.controllers.StartController;
import es.urjccode.mastercloudapps.adcs.draughts.models.Message;

public class StartView extends SubView {

    public StartView(){
        super();
    }

    public void interact(StartController startController) {
        this.console.writeln(Message.TITLE.getMessage());
        new GameView().write(startController);
        startController.start();
    }
}
