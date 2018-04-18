/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.List;

/**
 *
 * @author niloufar
 */
public class TransactionsStatementEntity {
    
   
    public OperationCodeEntity operationCode;
    public int recordNumber;
    public long balance;
    public long transactionAmountCredit;
    public long transactionAmountDebit;
    public int transactionDescription;
    public int branchNo;
    public String date;
    public int time;
    public String userID;
    public String originKey;
    public int source;
    public String optionalInformation;
    public String optionalInformation1;
}
