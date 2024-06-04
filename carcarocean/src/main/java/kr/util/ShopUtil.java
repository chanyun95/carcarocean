package kr.util;

public class ShopUtil {
    public static int getDiscount(int mem_grade, int car_price) {
    	double discount;
        switch(mem_grade) {
            case 1: discount = car_price * 0.9341; break;
            case 2: discount = car_price * 0.9441; break;
            case 3: discount = car_price * 0.9541; break;
            case 4: discount = car_price * 0.9641; break;
            default: discount = car_price * 0.9741;
        }
        return (int)discount;
    }
    
    
}
