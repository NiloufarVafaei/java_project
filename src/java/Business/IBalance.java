
package Business;

import Entity.BalanceEntity;

import javax.ejb.Local;

/**
 *
 * @author niloufar
 */
@Local
public interface IBalance {
   
    public BalanceEntity BalanceService(String nid, String deposit,String returnTokenEntity);
    
}
