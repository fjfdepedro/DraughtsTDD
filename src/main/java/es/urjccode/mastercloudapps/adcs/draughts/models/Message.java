package es.urjccode.mastercloudapps.adcs.draughts.models;

public enum Message {
    LOSE_THE_GAME("Derrota!!! No puedes mover tus fichas!!!"),
    ASK_PLAY_AGAIN("¿Queréis jugar otra"),
    TITLE("Draughts"),
    ERROR("Error!!!"),
    MOVE_IT("Mueven las ");

    private String message;

    private Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}

