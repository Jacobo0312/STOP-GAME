package model;

public class Match {

    public String type = "Match";

    private char letter = ' ';
    private int id = 0;

    public Match() {
        this.letter = randomLetter();
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    private char randomLetter() {
        return (char) (Math.random() * 26 + 'A');
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
