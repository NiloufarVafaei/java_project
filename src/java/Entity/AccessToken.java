package Entity;

import java.util.List;

public class AccessToken {

    public String value;
    public List<String> scopes;
    public String userId;
    public String refreshToken;
    public List<AccessDeposits> deposits;
    public String lifeTime;
    public String creationDate;
}
