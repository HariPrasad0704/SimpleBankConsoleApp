package com.bank;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class BankAccount {
    private int accountNumber;
    private String name;
    private long phoneNumber;
    private double balance;
    private List<String> transactionHistory;//stores the transaction history of a account
    

//    public BankAccount(int accountNumber) {
//        this.accountNumber = accountNumber;
//        this.balance = 500;
//        this.transactionHistory = new ArrayList<String>();
//    }
    
    public BankAccount(int accountNumber, String name, long phoneNumber) {
    	this.accountNumber=accountNumber;
    	this.name=name;
    	this.phoneNumber=phoneNumber;
    	this.balance=500;// initial balance is 500
    	//this.balance=initialBalance;
    	this.transactionHistory=new ArrayList<String>();//ArrayList is intialized once when an bankAccount is created. 
        //It stores the transaction history .
    }
    public String getName() {
    	return this.name;
    }
    public int getAccountNumber() {
        return this.accountNumber;
    }

    public double getBalance() {
        return this.balance;
    }
    
    public long getPhoneNumber() {
    	return this.phoneNumber;
    }

    public void deposit(double amount) {
        this.balance += amount;
        this.transactionHistory.add("Deposit: +" + amount + " Balance: " + this.balance );
    }

    public void withdraw(double amount) {
        if (this.balance < amount) {
            System.out.println("Insufficient funds");
            return;
        }

        this.balance -= amount;
        this.transactionHistory.add("Withdrawal: -" + amount + " Balance: " + this.balance);
    }

    public void printTransactionHistory() {
        System.out.println("Transaction history for account " + this.accountNumber + ":");
        for (String transaction : this.transactionHistory) {
            System.out.println(transaction);
        }
    }
}
//Banking Console class 
class BankingConsole {
    private Map<Integer, BankAccount> accounts;//Map which stores account number as key and BankAccount object as Value.

    public BankingConsole() {
        this.accounts = new HashMap<Integer, BankAccount>(); //accounts map object is initialized 
    }

    public void createBankAccount(int accountNumber,String name,long phoneNumber) {
        if (this.accounts.containsKey(accountNumber)) { //check if account is already available or not
            System.out.println("Account is already available");
            return;
        }

        BankAccount account = new BankAccount(accountNumber,name,phoneNumber); //create a  new BankAccount Object
        this.accounts.put(accountNumber, account);//stores in a map object.
        System.out.println("Account created with  Account Number " + accountNumber);
    }

    public void checkBalance(int accountNumber) {
        BankAccount account = this.accounts.get(accountNumber);//getting BankAccount object
        if (account == null) {
            System.out.println("Account not found");
             return;
        }

       System.out.println("Balance for account " + accountNumber + ": " + account.getBalance());
    }

    public void depositAmount(int accountNumber, double amount) {
        BankAccount account = this.accounts.get(accountNumber);
        if (account == null) {
           System.out.println("Account not found");
            return;
        }

        account.deposit(amount);
        System.out.println("Amount deposited into account " + accountNumber);
    }

    public void withdrawAmount(int accountNumber, double amount) {
        BankAccount account = this.accounts.get(accountNumber);
        if (account == null) {
            System.out.println("Account not found");
            return;
        }
//        double currentBalance=account.getBalance();
//        if(currentBalance>amount) {
//        	currentBalance-=amount;
//        }
//        BankAccount.transactionHistory.add("Withdrawal: -" + amount + " Balance: " + currentBalance);
        account.withdraw(amount);
        System.out.println("Amount withdrawn from account " + accountNumber);
    }

    public void showTransactionHistory(int accountNumber) {
        BankAccount account = this.accounts.get(accountNumber);
        if (account == null) {
            System.out.println("Account not found");
            return;
        }

        account.printTransactionHistory();
    }
}

public class Main {
    public static void main(String[] args) {
        BankingConsole bankingConsole = new BankingConsole();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Create BankAccount");
            System.out.println("2. Check Balance");
            System.out.println("3. Deposit Amount");
            System.out.println("4. Withdraw Amount");
            System.out.println("5. Show Transaction History");
            System.out.println("6. Exit");
            System.out.println("Enter your choice:");

            int choice = scanner.nextInt();

            if (choice == 1) {
            	
            	    System.out.println("Enter Account Number:");
            		int accountNumber = scanner.nextInt();
            		System.out.println("Enter Account Holder Name:");
            		String name=scanner.next();
            		System.out.println("Enter Phone Number:");
            		long phoneNumber=scanner.nextLong();
            	
            		bankingConsole.createBankAccount(accountNumber,name,phoneNumber);
            		}
            else if (choice == 2) {
            	
            		System.out.println("Enter Account Number:");
            		int accountNumber = scanner.nextInt();
            		bankingConsole.checkBalance(accountNumber);
            		}
            else if (choice == 3) {
            	
            		System.out.println("Enter Account Number:");
            		int accountNumber = scanner.nextInt();
            		System.out.println("Enter Amount:");
            		double amount = scanner.nextDouble();
            		bankingConsole.depositAmount(accountNumber, amount);
            		}
            else if (choice == 4) {
            		System.out.println("Enter Account Number:");
            		int accountNumber = scanner.nextInt();
            		System.out.println("Enter Amount:");
            		double amount = scanner.nextDouble();
            		bankingConsole.withdrawAmount(accountNumber, amount);
            		}
            else if (choice == 5) {
            		System.out.println("Enter Account Number:");
            		int accountNumber = scanner.nextInt();
            		bankingConsole.showTransactionHistory(accountNumber);
            		}
            else if (choice == 6) {
            		break;
            		}
            else {
            		System.out.println("Invalid option");
            	 }
            		}
        scanner.close();
    }
}

