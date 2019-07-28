package vn.edu.tdc.mymanager.model;

public class Inventory {


    private int idArea;     // Mã khu vực
    private String nameArea;   // Tên khu vực
    private String date;    // Ngày tạo

    public Inventory(int id, String nameArea, String date) {

        this.idArea = id;
        this.nameArea = nameArea;
        this.date = date;

    }

    public Inventory (String nameArea, String date) {

        this.nameArea = nameArea;
        this.date = date;

    }


    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public String getNameArea() {
        return nameArea;
    }

    public void setNameArea(String nameArea) {
        this.nameArea = nameArea;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
