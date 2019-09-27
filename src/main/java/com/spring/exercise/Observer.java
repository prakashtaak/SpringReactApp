package com.spring.exercise;

import java.util.List;
import java.util.Optional;


public interface Observer {

    void update(State state);
}

interface State {
}

interface Display{
    void display();
}

class CurrentConditionaData implements State{
    float temperature;
    float humidity;
    float pressure;

    public CurrentConditionaData(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    @Override
    public String toString() {
        return "CurrentConditionaData{" +
                "temperature=" + temperature +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                '}';
    }
}



class CurrentConditionDisplay implements Observer ,Display {

    State state;
    Subject subject;

    public CurrentConditionDisplay(State state, Subject subject) {
        this.state = state;
        this.subject = subject;
        this.subject.registerObservers(this);
    }



    @Override
    public void update(State state) {
        this.state =state;
        display();
    }


    @Override
    public void display() {
        Optional.of(this.state).ifPresent(System.out::println);
    }




}
class Main{
    public static void main(String[] args) throws InterruptedException {

        State data = new CurrentConditionaData(25,20,25);
        Subject subject = new WeatherData(data);
        Observer observer = new CurrentConditionDisplay(data, subject);
        ((Display) observer).display();
        Thread t2 =new Thread(() -> {
            State newData = new CurrentConditionaData(31,28,27);
            subject.setState(newData);
        });
        Thread t3 =new Thread(() -> {
            State newData = new CurrentConditionaData(32,21,23);
            subject.setState(newData);
        });
        t2.start();
        t3.start();
        //Thread.sleep(1000);

    }
}
