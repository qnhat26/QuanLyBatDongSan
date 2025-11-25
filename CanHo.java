package CongTyBatDongSan;

public class CanHo extends BatDongSan{
    //thuoctinh
    private int tang;
    private int soPhongNgu;

    //constructor
    public CanHo(){}
    public CanHo(String maBDS, DiaChi diaChi, float dienTich, double gia,
                    int tang, int soPhongNgu){
                        super(maBDS, diaChi, dienTich, gia);
                        this.tang=tang;
                        this.soPhongNgu=soPhongNgu;
    }

    //Nhap
    @Override
    public void Nhap(){
        super.Nhap();
        System.out.print("Tầng: ");
        this.tang=Integer.parseInt(Input.sc.nextLine());
        System.out.print("Số phòng ngủ: ");
        this.soPhongNgu=Integer.parseInt(Input.sc.nextLine());
    }

    //Xuat
    @Override
    public void Xuat(){
        super.Xuat();
        System.out.println("Tầng: "+this.tang);
        System.out.println("Số phòng ngủ: "+this.soPhongNgu);
    }
    
    @Override
    public String toDataString() {
        return "CanHo;" + maBDS + ";" + diaChi.toDataString() + ";" +
                dienTich + ";" + gia + ";" + tang + ";" + soPhongNgu;
    }

    //Abstract
    @Override
    public double TinhThue() {
        return this.getGia()*0.1;
    }
    
    //Getter
    public int getTang() {
        return tang;
    }
    public int getSoPhongNgu() {
        return soPhongNgu;
    }

    //Setter
    public void setTang(int tang) {
        this.tang = tang;
    }
    public void setSoPhongNgu(int soPhongNgu) {
        this.soPhongNgu = soPhongNgu;
    }
}


