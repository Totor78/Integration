package model;

public class Borrow {
    private int borrow_id;
    private int agent_id;
    private int equipment_id;
    private String date_borrow;
    private String date_return;

    public Borrow(int borrow_id, int agent_id, int equipment_id, String date_borrow, String date_return) {
        this.borrow_id = borrow_id;
        this.agent_id = agent_id;
        this.equipment_id = equipment_id;
        this.date_borrow = date_borrow;
        this.date_return = date_return;
    }

    public Borrow(int agent_id, int equipment_id, String date_borrow, String date_return) {
        this.agent_id = agent_id;
        this.equipment_id = equipment_id;
        this.date_borrow = date_borrow;
        this.date_return = date_return;
    }


    public int getBorrow_id() { return borrow_id; }

    public int getAgent_id() {
        return agent_id;
    }

    public int getEquipment_id() {
        return equipment_id;
    }

    public String getDate_borrow() {
        return date_borrow;
    }

    public void setDate_borrow(String date_borrow) {
        this.date_borrow = date_borrow;
    }

    public String getDate_return() {
        return date_return;
    }

    public void setDate_return(String date_return) {
        this.date_return = date_return;
    }
}
