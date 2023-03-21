package com.driver;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        super(name, balance, 5000);
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        if(balance < 5000){
            throw new printError("Insufficient Balance");
        }
        this.tradeLicenseId = tradeLicenseId;
        validateLicenseId();
    }


    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        if(isValid(this.tradeLicenseId)){
            return;
        }

        try{
            this.tradeLicenseId = rearrangeString(this.tradeLicenseId);
        }
        catch (Exception e){
            throw e;
        }



    }

    public boolean isValid(String str){
        for(int i=0; i<str.length()-1; i++){
            if(str.charAt(i) == str.charAt(i+1)){
                return false;
            }
        }
        return true;
    }


    // Function to rearrange character of a string
    // so that no char repeat twice
    static String rearrangeString(String str) throws Exception {
        int MAX_CHAR = 26;

        int n = str.length();

        // Store frequencies of all characters in string
        int[] count = new int[MAX_CHAR];

        for (int i = 0; i < n; i++) {
            count[str.charAt(i) - 'A']++;
        }
        // Insert all characters with their
        // frequencies into a priority_queue
        PriorityQueue<Key> pq
                = new PriorityQueue<>(new KeyComparator());
        for (char c = 'a'; c <= 'z'; c++) {
            int val = c - 'a';
            if (count[val] > 0)
                pq.add(new Key(count[val], c));
        }

        // 'str' that will store resultant value
        String ansStr = "";

        // work as the previous visited element
        // initial previous element be. ( '#' and
        // it's frequency '-1' )
        Key prev = new Key(-1, '#');

        // traverse queue
        while (pq.size() != 0) {

            // pop top element from queue and
            // add it to string.
            Key k = pq.peek();
            pq.poll();
            ansStr = ansStr + k.ch;

            // If frequency of previous character
            // is less than zero that means it is
            // useless, we need not to push it
            if (prev.freq > 0)
                pq.add(prev);

            // make current character as the previous
            // 'char' decrease frequency by 'one'
            (k.freq)--;
            prev = k;
        }

        // If length of the resultant string
        // and original string is not same then
        // string is not valid
        if (ansStr == "" || n != ansStr.length())
            throw new printError("Valid License can not be generated");
        else
            return ansStr;
    }
}

class KeyComparator implements Comparator<Key> {

    // Overriding compare()method of Comparator
    public int compare(Key k1, Key k2)
    {
        if (k1.freq < k2.freq)
            return 1;
        else if (k1.freq > k2.freq)
            return -1;
        return 0;
    }
}

class Key {

    int freq; // store frequency of character
    char ch;
    Key(int val, char c)
    {
        freq = val;
        ch = c;
    }
}
/*
CurrentAccount: CurrentAccount extends BankAccount. A current account also contains a tradeLicenseId (consisting of Uppercase English characters only). The minimum balance required to open a current account is 5000 by default.

a. validateLicenseId(): A trade license Id is said to be valid if no two consecutive characters are same. If the license Id is valid, do nothing, else rearrange the characters of the license Id to create any valid license Id. If it is not possible, throw "Valid License can not be generated" Exception
 */