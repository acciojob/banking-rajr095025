package com.driver;

public class Main {
    public static void main(String[] args) throws Exception {

        try {
            CurrentAccount ca1 = new CurrentAccount("Rocky", 5500, "AAA");
            SavingsAccount sa1 = new SavingsAccount("Rockysa1",60000,100000,2);
            System.out.println(sa1.getBalance());
            System.out.println(sa1.getSimpleInterest(3));
            System.out.println(sa1.getCompoundInterest(2,3));
            System.out.println(sa1.generateAccountNumber(5,1));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}