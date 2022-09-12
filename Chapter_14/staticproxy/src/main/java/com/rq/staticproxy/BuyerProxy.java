package com.rq.staticproxy;

public class BuyerProxy implements IShop{
    private IShop mShop;

    public BuyerProxy(IShop shop){
        mShop = shop;
    }

    @Override
    public void buy() {
        mShop.buy();
    }
}
