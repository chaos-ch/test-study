package com.ch.jeight;

public class LambdaCh{
    public static void useLambda(){
        Runnable runnable2 = ()->{
            System.out.println("h1");
            System.out.println("h2");
        };
    }

    public static void main(String[] args) {
        useLambda();
    }
}