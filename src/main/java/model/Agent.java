package model;

import javax.swing.*;

public class Agent {
    private int id;
    private String name;
    private ImageIcon image;

    public Agent(int id, String name, ImageIcon image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ImageIcon getImage() {
        return image;
    }
}
