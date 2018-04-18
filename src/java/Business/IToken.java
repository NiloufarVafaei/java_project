
package Business;

import Entity.TokenEntity;
import javax.ejb.Local;

@Local
public interface IToken {
    public TokenEntity GetClient_Credential(String nid, String deposit);
    public TokenEntity GetAuthorization_Code(String code);
    
}