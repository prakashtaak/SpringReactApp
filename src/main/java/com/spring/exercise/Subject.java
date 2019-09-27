package com.spring.exercise;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public interface Subject {

    void registerObservers(Observer observer);

    void removeObservers(Observer observer);

    void notifyObserver();

    void setState(State state);

}

class WeatherData implements Subject {

    State state;

    Queue<State> data;

    List<Observer> observerList;

    volatile boolean isStateChanged = false;

    public WeatherData(State state) {
        this.state = state;
        this.data =new LinkedBlockingQueue<>();
        observerList =new ArrayList<>();
        pollForState();
    }

    @Override
    public void registerObservers(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObservers(Observer observer) {
        int x = -1;
        if ((x = observerList.indexOf(observer)) != -1) {
            observerList.remove(x);
        }
    }

    @Override
    public void notifyObserver() {

        observerList.stream().forEach(x -> x.update(data.poll()));
    }

    @Override
    public  void  setState(State state) {

        data.add(state);
    }



     private void pollForState(){
         Thread t1= new Thread(() -> {
            while(true) {

                    if (data.peek() != null) notifyObserver();
                }

        });

         t1.start();

    }

}


