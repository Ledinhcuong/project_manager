package vn.edu.tdc.mymanager.model;

public class Product {

   private String idProduct;
   private String idArea;
   private String nameProduct;
   private int productAmount;
   private  double sizeProduct;
   private String kindProduct;


   private String photo1;
   private String photo2;
   private String photo3;
   private  String photo4;
   private String photo5;
   private String photo6;

   public Product (String idProduct, String idArea, String nameProduct, int productAmount, double sizeProduct, String kind) {

       this.idProduct = idProduct;
       this.idArea = idArea;
       this.productAmount = productAmount;
       this.sizeProduct = sizeProduct;
       this.kindProduct = kind;
       this.nameProduct = nameProduct;

   }




    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getIdArea() {
        return idArea;
    }

    public void setIdArea(String idArea) {
        this.idArea = idArea;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    public double getSizeProduct() {
        return sizeProduct;
    }

    public void setSizeProduct(double sizeProduct) {
        this.sizeProduct = sizeProduct;
    }

    public String getKindProduct() {
        return kindProduct;
    }

    public void setKindProduct(String kindProduct) {
        this.kindProduct = kindProduct;
    }

    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }

    public String getPhoto2() {
        return photo2;
    }

    public void setPhoto2(String photo2) {
        this.photo2 = photo2;
    }

    public String getPhoto3() {
        return photo3;
    }

    public void setPhoto3(String photo3) {
        this.photo3 = photo3;
    }

    public String getPhoto4() {
        return photo4;
    }

    public void setPhoto4(String photo4) {
        this.photo4 = photo4;
    }

    public String getPhoto5() {
        return photo5;
    }

    public void setPhoto5(String photo5) {
        this.photo5 = photo5;
    }

    public String getPhoto6() {
        return photo6;
    }

    public void setPhoto6(String photo6) {
        this.photo6 = photo6;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }
}
