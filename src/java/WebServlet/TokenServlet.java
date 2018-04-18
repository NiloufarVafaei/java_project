package WebServlet;

import Business.IBalance;
import Business.IRefreshToken;
import Business.IStatement;
import Business.IToken;
import Entity.BalanceEntity;
import Entity.StatementEntity;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author niloufar vafaei
 */
public class TokenServlet extends HttpServlet {

    @EJB
    private IStatement statement;

    @EJB
    private IRefreshToken refreshToken;

    @EJB
    private IBalance balance;

    @EJB
    private IToken token;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //Come from firstForm page in clicking the sumbit button
        String N = request.getParameter("nid");
        String D = request.getParameter("deposit");
        String TokenType = request.getParameter("TokenType");
        String ServiceType = request.getParameter("ServiceType");

        //This parameters come from redirecte url from finotec
        //String code = request.getParameter("code");
        String GetToken = null;

        if (TokenType.matches("Client_Credential") == true) {
            //This parameter take Token Client_Credential
            GetToken = token.GetClient_Credential(N, D).access_token.value;
            String Re = token.GetClient_Credential(N, D).access_token.refreshToken;

            if (ServiceType.equals("Balance")) {
                // <editor-fold defaultstate="Balance">
                //This paramiters calculate account balance and send it to BalanceForm.html
                BalanceEntity be = balance.BalanceService(N, D, GetToken);
                Long Beb = be.availableBalance;
                String num = be.number;
                //  String Url = "http://localhost:8080/AdanikProject/BalanceForm.html?" + GetToken + "&" + "balance=" + Beb;
                String Url = "http://localhost:8080/AdanikProject/BalanceForm.html?" + "&" + "number=" + num + "&balance=" + Beb;
                if (be.code == 0) {
                    response.sendRedirect(Url);
                } else {
                    GetToken = refreshToken.GetRefreshToken(Re).access_token.value;
                    //Url="http://localhost:8080/AdanikProject/ResulteForm.html?" + GetToken + "&" + "balance=" + Beb;
                    Url = "http://localhost:8080/AdanikProject/BalanceForm.html?" + "&" + "balance=" + Beb;
                    response.sendRedirect(Url);

                }
                // </editor-fold>
            } else if (ServiceType.equals("Statement")) {
                // <editor-fold defaultstate="Statement">
                StatementEntity state = statement.StatementService(N, D, GetToken);
                List<StatementEntity> StatementResponse = new ArrayList<StatementEntity>();
                StatementResponse.add(state);
                //convert StatementEntity result to Json Strig
                // ObjectMapper mapper = new ObjectMapper();
                //String jsonInString = mapper.writeValueAsString(state);
                //************************************** 
                request.setAttribute("Info", StatementResponse);
                request.getRequestDispatcher("Statement.jsp").forward(request, response);
                // getServletConfig().getServletContext().getRequestDispatcher("/Statement.jsp").forward(request,response);
                // </editor-fold>
            }

        } else if (TokenType.matches("Authorization_Code") == true /*&& code == null*/) {
            // <editor-fold defaultstate="Authorization_Code">
            //This parameter take Token Authorization_Code
            //Create HttpSession
            HttpSession session = request.getSession();
            session.setAttribute("ServiceType", ServiceType);
            if (ServiceType.equals("Balance")) {
            
            
            String Url = "https://sandbox.finnotech.ir/id/authorize/user?client_id=123456niloofar&response_"
                    + "type=code&redirect_uri=http://localhost:8080/AdanikProject/oauth2callback&scope=oak:balance:get";
            response.sendRedirect(Url);
                    }
            else if (ServiceType.equals("Statement")){
                 String Url = "https://sandbox.finnotech.ir/id/authorize/user?client_id=123456niloofar&response_"
                    + "type=code&redirect_uri=http://localhost:8080/AdanikProject/oauth2callback&scope=oak:statement:get";
            response.sendRedirect(Url);
                
            }
            
            // </editor-fold>
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String rest_endpoint = request.getRequestURI();

        if (rest_endpoint.compareTo("/code") == 0) {
            try (PrintWriter out = response.getWriter()) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet TokenServlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h2>Token is:" + "Yes" + "<br><br>"
                        + "</h2>");
                out.println("</body>");
                out.println("</html>");
            }
        } else {
            //send account service type to callback servlet
            // request.setAttribute("AccountServiceType", "Statement");
            // RequestDispatcher reqDispatcher = request.getRequestDispatcher("oauth2callback");
            //reqDispatcher.forward(request, response);
            processRequest(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
