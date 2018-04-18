
package Business;

import Entity.TokenEntity;
import javax.ejb.Local;

@Local
public interface IRefreshToken {
  public TokenEntity GetRefreshToken(String refreshToken);   
}
