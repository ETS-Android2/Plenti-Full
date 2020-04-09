package com.example.plenti_full.Javabeans;

public class Instruction {
    private int id;
    private String step;

    public Instruction(int id, String step) {
        this.id = id;
        this.step = step;
    }

    public Instruction(String step) {
        this.step = step;
    }

    public int getId() {
        return id;
    }
    public String getStep() {
        return step;
    }
}
