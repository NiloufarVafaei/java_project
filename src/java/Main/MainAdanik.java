package Main;

import Business.Balance;
import Business.RefreshToken;
import Business.Statement;
import Business.Token;
import Entity.BalanceEntity;
import Entity.StatementEntity;
import Entity.TokenEntity;

public class MainAdanik {

	public static void main(String[] args) {
		try {
			Token t = new Token();
			TokenEntity te;
			te = t.GetClient_Credential("2222222222", "2222222222001");
                        
//                        String re=te.access_token.refreshToken;
//			System.out.println("The response value  is " + te.access_token.value);
//			System.out.println("The response RefreshToken  is " + te.access_token.refreshToken);
//			System.out.println("The response LifeTime  is " + te.access_token.lifeTime);
//			System.out.println("The response CreationDate  is " + te.access_token.creationDate);
//			System.out.println("The response token_type  is " + te.token_type);
			
			//Balance b = new Balance();
			
//                      BalanceEntity ben;
//			ben=b.BalanceService("2222222222", "2222222222001", te.access_token.value);
//			System.out.println("The farsi_message  is "+ben.farsi_message);
//                      
//                        RefreshToken rt=new RefreshToken();
//                        System.out.println( rt.GetRefreshToken(re).access_token.value);
//                        System.out.println( rt.GetRefreshToken(re).access_token.refreshToken);
                        
                        Statement s= new Statement();
                        StatementEntity Se;
                        Se=s.StatementService("2222222222", "2222222222001", te.access_token.value);
                        System.out.println("result :"+Se.branchName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
