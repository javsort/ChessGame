package main;

public class Player {
    boolean white;
    protected String name;

    public Player(){
        name = "";
        white = false;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setColor(boolean white){
        this.white = white;
    }

    public String getName(){
        return name;
    }
}
