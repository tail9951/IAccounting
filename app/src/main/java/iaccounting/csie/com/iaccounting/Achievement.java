package iaccounting.csie.com.iaccounting;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AlertDialog;

public class Achievement extends Application {
    private int receiptNum=0;
    private int accountNum=0;
    private int diaryNum=0;

    public void addReceiptNum(){
        receiptNum+=1;
    }
    public void addDiaryNum(){
        diaryNum+=1;
    }
    /*public void delReceiptNum(){
        receiptNum-=1;
    }*/

    public void checkReceiptNum(Context c){
        if(receiptNum == 5) {
            AlertDialog.Builder builder = new AlertDialog.Builder(c);
            builder.setTitle("發票達人");
            builder.setMessage("發票已經累計" + receiptNum + "張了");
            builder.show();
        }
        if(receiptNum == 10) {
            AlertDialog.Builder builder = new AlertDialog.Builder(c);
            builder.setTitle("發票王");
            builder.setMessage("發票已經累計" + receiptNum + "張了");
            builder.show();
        }
        if(receiptNum == 15) {
            AlertDialog.Builder builder = new AlertDialog.Builder(c);
            builder.setTitle("發票神");
            builder.setMessage("發票已經累計" + receiptNum + "張了");
            builder.show();
        }
    }
    public int getReceiptNum() {
        return receiptNum;
    }
    public int getDiaryNum() {
        return diaryNum;
    }
    public int getAccountNum() {
        return accountNum;
    }

    public void addAccountNum(){
        accountNum+=1;
    }

    /*public void delAccountNum(){
        accountNum-=1;
    }*/

    public void checkAccountNum(Context c){
        if(accountNum == 5) {
            AlertDialog.Builder builder = new AlertDialog.Builder(c);
            builder.setTitle("記帳達人");
            builder.setMessage("已經累計" + accountNum + "張帳單了");
            builder.show();
        }
        if(accountNum == 10) {
            AlertDialog.Builder builder = new AlertDialog.Builder(c);
            builder.setTitle("記帳王");
            builder.setMessage("已經累計" + accountNum + "張帳單了");
            builder.show();
        }
        if(accountNum == 15) {
            AlertDialog.Builder builder = new AlertDialog.Builder(c);
            builder.setTitle("記帳神");
            builder.setMessage("已經累計" + accountNum + "張帳單了");
            builder.show();
        }
    }
}
