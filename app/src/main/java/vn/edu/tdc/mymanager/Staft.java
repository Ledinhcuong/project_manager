package vn.edu.tdc.mymanager;

public class Staft {


    private int idStaft;
    private String staftName;
    private String staftGT;
    private String dateCreate;

    public Staft(String name, String gt, String date) {

        this.staftName = name;
        this.staftGT = gt;
        this.dateCreate = date;

    }

    public Staft(int id, String name, String gt, String date) {
        this.idStaft = id;
        this.staftName = name;
        this.staftGT = gt;
        this.dateCreate = date;
    }


    public int getIdStaft() {
        return idStaft;
    }

    public void setIdStaft(int idStaft) {
        this.idStaft = idStaft;
    }

    public String getStaftName() {
        return staftName;
    }

    public void setStaftName(String staftName) {
        this.staftName = staftName;
    }

    public String getStaftGT() {
        return staftGT;
    }

    public void setStaftGT(String staftGT) {
        this.staftGT = staftGT;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }
}
