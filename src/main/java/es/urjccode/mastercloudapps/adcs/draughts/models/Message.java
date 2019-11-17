package es.urjccode.mastercloudapps.adcs.draughts.models;

public enum Message {
    LOSE_THE_GAME("Derrota!!! No puedes mover tus fichas!!!");

    private String message;

    private Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}

