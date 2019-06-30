package vn.edu.tdc.mymanager.model;

public class Function {

    private String nameFuction;
    private String explainFunction;
    private int icon;

    public Function(String name, String explain, int icon) {

        this.nameFuction = name;
        this.explainFunction = explain;
        this.icon = icon;

    }


    public String getNameFuction() {
        return nameFuction;
    }

    public void setNameFuction(String nameFuction) {
        this.nameFuction = nameFuction;
    }

    public String getExplainFunction() {
        return explainFunction;
    }

    public void setExplainFunction(String explainFunction) {
        this.explainFunction = explainFunction;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
