package com.ywq.dispatc;

public class DynamicDispatchTest {
    static class Father {
        public int money = 1;

        public Father() {
            money = 2;
            sayHello();
        }

        public void sayHello() {
            System.out.println("The father has $" + money);
        }
    }

    static class Son extends Father {
        public int money = 2;

        public Son() {
            money = 4;
            sayHello();
        }

        public void sayHello() {
            System.out.println("The son has $" + money);
        }
    }

    public static void main(String[] args) {
        Father gay = new Son();
        System.out.println("This gay has $" + gay.money);
    }
}
