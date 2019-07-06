package vn.edu.tdc.mymanager.model;

public class Inventory {

    private String idArea;
    private String nameArea;

    public Inventory(String id, String nameArea) {

        this.idArea = id;
        this.nameArea = nameArea;

    }


    public String getIdArea() {
        return idArea;
    }

    public void setIdArea(String idArea) {
        this.idArea = idArea;
    }

    public String getNameArea() {
        return nameArea;
    }

    public void setNameArea(String nameArea) {
        this.nameArea = nameArea;
    }
}
