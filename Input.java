package CongTyBatDongSan;

import java.util.Scanner;

public class Input {
    public static final Scanner sc=new Scanner(System.in);

    //Hàm xóa màn hình
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    //Hàm giới hạn khoảng nhập
    public static int gioiHanNhap(String msg, int a, int b){
        while (true) {
            try {
                System.out.print(msg);
                int so=Integer.parseInt(sc.nextLine());

                if (so<a || so>b)
                    System.out.println("Giá trị nhập vào phải nằm trong khoảng "+a+"-"+b+"!");
                else 
                    return so;
            } catch (Exception e) {
                System.out.println("Giá trị nhập vào phải là số nguyên!");
            }
        }
    }
}
