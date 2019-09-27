package com.spring.exercise;



interface Beverage {
    String description();

    float cost();
}

class Expresso implements Beverage {

    @Override
    public String description() {
        return "expresso coffe";
    }

    @Override
    public float cost() {
        return 10.0f;
    }
}

class DarkRust implements Beverage {

    @Override
    public String description() {
        return "Darkrust coffee";
    }

    @Override
    public float cost() {
        return 12;
    }
}


//we are making our BevDecorator class of type beverage, as the concrete class of
// BevDecorator will wrap the supplied beverage with itself
// and further enables to wraps more beverage of same type
abstract class BevDecorator implements Beverage{


}

class Mocho extends BevDecorator{

    private Beverage beverage;

    public Mocho(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String description() {
        return beverage.description() + " with " + " Mocho ";
    }

    @Override
    public float cost() {
        return 12.0f + beverage.cost();
    }
}
class Whip extends BevDecorator{

    private Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String description() {
        return beverage.description() + " with " + " Whip ";
    }

    @Override
    public float cost() {
        return 12.0f + beverage.cost();
    }
}

public class Decorator{

    public static void main(String[] args) {

        Beverage beverage1 =new Expresso();
        beverage1 = new Mocho(beverage1);
        beverage1 = new Whip(beverage1);

        System.out.println("Item details " + beverage1.description() +" : cost :" + beverage1.cost());

    }

}


