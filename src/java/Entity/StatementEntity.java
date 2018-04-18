package Entity;

import java.util.List;

/**
 *
 * @author niloufar
 */
public class StatementEntity {

    public int branchCode;
    public String branchName;
    public int accountTypeCode;
    public long subTypeCode;
    public String shaba;
    public String customerName;
    public String customerFamilyName;
    public long backupAccountNumber;
    public String[] accountCurrentBalanceSign;
    public long availableBalance;
    public long accountCurrentBalance;
    public long accountAvailableBalance;
    public String[] accountAvailableBalanceSign;
    public long effectiveAccountBalance;
    public String effectiveAccountBalanceSign;
    public String openDate;
    public List<TransactionsStatementEntity> transactions;
    public int code;
    public String trackId;
    public String state;

}
