package com.rq.staticproxy;

public class Buyer  implements IShop{
    @Override
    public void buy() {
        System.out.println("购买……");
    }
}
