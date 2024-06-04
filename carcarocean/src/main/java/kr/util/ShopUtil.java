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
    
    public static int getDiscountFee(int mem_grade, int car_price) {
    	double discount;
    	switch(mem_grade){
    		case 1: discount = car_price * 0.01; break;
    		case 2: discount = car_price * 0.02; break;
    		case 3: discount = car_price * 0.03; break;
    		case 4: discount = car_price * 0.04; break;
    		default: discount = car_price * 0.05;
    	}
    	return (int)discount;
    }
}
