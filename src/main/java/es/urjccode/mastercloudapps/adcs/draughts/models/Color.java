package es.urjccode.mastercloudapps.adcs.draughts.models;

public enum Color {
    WHITE("blancas"),
    BLACK("negras");

    private String message;

    private Color(String message) {
        this.message = message;
    }

    public String getColorName() {
        return this.message;
    }
}