package CongTyBatDongSan;

public abstract class BatDongSan {
    //Thuoctinh
    protected String maBDS;
    protected DiaChi diaChi=new DiaChi();
    protected float dienTich;
    protected double gia;
    
    //Constructor
    public BatDongSan(){}
    public BatDongSan(String maBDS, DiaChi diaChi, float dienTich, double gia){
        this.maBDS=maBDS;        
        this.diaChi=diaChi;
        this.dienTich=dienTich;
        this.gia=gia;
    }

    //Nhap
    public void Nhap(){
        System.out.println("====NHẬP THÔNG TIN BẤT ĐỘNG SẢN====");
        System.out.print("Mã BDS: ");
        this.maBDS=Input.sc.nextLine().toUpperCase();
        System.out.println("Địa chỉ: ");
        this.diaChi.Nhap();
        System.out.print("Diện tích: ");
        this.dienTich=Float.parseFloat(Input.sc.nextLine());
        System.out.print("Giá: ");
        this.gia=Double.parseDouble(Input.sc.nextLine());
    }

    //Xuat
    public void Xuat(){
        System.out.println("====THÔNG TIN BẤT ĐỘNG SẢN===");
        System.out.println("Mã BDS: "+this.maBDS);
        System.out.println("Địa chỉ: ");
        this.diaChi.Xuat();
        System.out.println("Diện tích: "+this.dienTich);
        System.out.println("Giá: "+this.gia);
        System.out.println("Thuế phải trả: "+TinhThue());
    }
    
    public String toDataString() {
        return "";
    }

    //Getter
    public String getMaBDS() {
        return maBDS;
    }
    public DiaChi getDiaChi() {
        return diaChi;
    }
    public double getGia() {
        return gia;
    }
    public float getDienTich() {
        return dienTich;
    }
    //Setter
    public void setMaBDS(String maBDS) {
        this.maBDS = maBDS;
    }
    public void setDiaChi(DiaChi diaChi) {
        this.diaChi = diaChi;
    }
    public void setDienTich(float dienTich) {
        this.dienTich = dienTich;
    }
    public void setGia(double gia) {
        this.gia = gia;
    }

    //Abstract
    public abstract double TinhThue();

}


