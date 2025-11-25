package CongTyBatDongSan;

public class NhaO extends BatDongSan{
    private int soTang;
    private boolean sanVuon;

    public NhaO(){};
    public NhaO(String maBDS, DiaChi diaChi, float dienTich, double gia,
                int soTang, boolean sanVuon){
        super(maBDS, diaChi, dienTich, gia);
        this.soTang=soTang;
        this.sanVuon=sanVuon;
    }

    @Override
    public void Nhap() {
        super.Nhap();
        System.out.print("Số tầng: ");
        this.soTang=Integer.parseInt(Input.sc.nextLine());
        System.out.print("Có sân vườn không? True/False: ");
        this.sanVuon=Boolean.parseBoolean(Input.sc.nextLine());
    }

    @Override
    public void Xuat() {
        super.Xuat();
        System.out.println("Số tầng: "+this.soTang);
        if (this.sanVuon) {
            System.out.println("Có sân vườn");
        } else System.out.println("Không có sân vườn");
    }
    @Override
    public String toDataString() {
        return "CanHo;" + maBDS + ";" + diaChi.toDataString() + ";" +
                dienTich + ";" + gia + ";" + soTang + ";" + sanVuon;
    }

    @Override
    public double TinhThue() {
        return this.getGia()*0.02;
    }

    public int getSoTang() {
        return soTang;
    }
    public boolean isSanVuon() {
        return sanVuon;
    }


    public void setSoTang(int soTang) {
        this.soTang = soTang;
    }
    public void setSanVuon(boolean sanVuon) {
        this.sanVuon = sanVuon;
    }
}
