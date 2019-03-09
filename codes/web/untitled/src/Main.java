import java.io.File;
import java.lang.Exception;
class Account{
    protected double balance;

    Account(){
        this.balance=0;
    }
    Account(double init){
        this.balance=init;

    }
    public double getBalance(){
        return this.balance;
    }
    public void deposit(double amt){
        this.balance+=amt;
    }
    public boolean withdraw(double amt) throws OverdraftException{
        if(balance>=amt){
            balance-=amt;
            return true;
        }
        else throw new OverdraftException("透支了", amt-balance);
    }
}

class CheckingAccount extends Account {
    private double overdraftProtection;

    CheckingAccount(double balance){
        this.balance=balance;
        this.overdraftProtection=0;
    }
    CheckingAccount(double balance,double protect){
        this.balance=balance;
        this.overdraftProtection=protect;
    }
    public boolean withdraw(double amt) throws OverdraftException{
        if(balance>=amt){
            balance-=amt;
            return true;
        }
        else if(balance+overdraftProtection>=amt){
            balance=0;
            overdraftProtection-=(amt-balance);
            return true;
        }
        else{
            throw new OverdraftException("透支了", amt-balance-deficit);
        }
    }
}
class OverdraftException extends Exception{
    private double deficit;
    OverdraftException(String message,double deficit){
        super(message);
        this.deficit=deficit;
    }
    public double getDeficit(){
        return this.deficit;
    }
}
public class Main{
    public static void main(String[] args) {
        CheckingAccount a = new CheckingAccount(1000, 500);
        //存了1000
        a.deposit(1000);
        //查询余额
        System.out.println(a.getBalance());
        try {
            a.withdraw(600);
            System.out.println(a.getBalance());
            a.withdraw(600);
            System.out.println(a.getBalance());
            a.withdraw(600);
            System.out.println(a.getBalance());
            a.withdraw(600);
            System.out.println(a.getBalance());
            a.withdraw(600);
            System.out.println(a.getBalance());
        } catch (OverdraftException e) {
            System.err.println("透支超额:"+e.getDeficit());
            e.printStackTrace();
        }
    }

}