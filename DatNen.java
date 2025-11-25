package CongTyBatDongSan;

public class DatNen extends BatDongSan{
    private loaiDat loaiDat;

    public DatNen(){}
    public DatNen(String maBDS, DiaChi diaChi, float dienTich, double gia,
                    loaiDat loaiDat){
        super(maBDS, diaChi, dienTich, gia);
        this.loaiDat=loaiDat;
    }

    @Override
    public void Nhap() {
        super.Nhap();
        
        System.out.println("Chọn loại đất:");
        System.out.println("1. Đất ở");
        System.out.println("2. Đất nông nghiệp");
        System.out.println("3. Đất công nghiệp");
        System.out.println("4. Đất du lịch");

        int chon = Integer.parseInt(Input.sc.nextLine());

        switch(chon){
            case 1: loaiDat = loaiDat.DatO; break;
            case 2: loaiDat = loaiDat.DatNongNghiep; break;
            case 3: loaiDat = loaiDat.DatCongNgiep; break;
            case 4: loaiDat = loaiDat.DatDuLich; break;
        default:
            System.out.println("Chọn sai! Mặc định Đất ở");
            loaiDat = loaiDat.DatO;
        }        
    }

    @Override
    public void Xuat() {
        super.Xuat();
        System.out.println("Loại đất: "+this.loaiDat.getTen());
    }
    @Override
    public String toDataString() {
        return "CanHo;" + maBDS + ";" + diaChi.toDataString() + ";" +
                dienTich + ";" + gia + ";" + loaiDat.getTen();
    }
    
    @Override
    public double TinhThue() {
        return this.getGia()*loaiDat.getThue();
    }

    public loaiDat getLoaiDat() {
        return loaiDat;
    }
    public void setLoaiDat(loaiDat loaiDat) {
        this.loaiDat = loaiDat;
    }
}
