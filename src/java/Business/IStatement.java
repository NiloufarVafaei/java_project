
package Business;

import Entity.StatementEntity;
import javax.ejb.Local;

/**
 *
 * @author niloufar
 */
@Local
public interface IStatement {
     public StatementEntity StatementService(String nid, String deposit, String returnTokenEntity) ;
}
