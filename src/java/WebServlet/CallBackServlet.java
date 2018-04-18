/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServlet;

import Business.IBalance;
import Business.IStatement;
import Business.IToken;
import Entity.BalanceEntity;
import Entity.StatementEntity;
import Entity.TokenEntity;

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
 * @author niloufar
 */
//@WebServlet(urlPatterns="/oauth2callback", asyncSupported=true)
public class CallBackServlet extends HttpServlet {

    @EJB
    private IStatement statement;

    @EJB
    private IBalance balance;
    @EJB
    private IToken token;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Reading HttpSession
        HttpSession session = request.getSession(false);
        String ServiceType = (String) session.getAttribute("ServiceType");
        //Check if the user have rejected 
        String error = request.getParameter("error");
        if ((null != error) && ("access_denied".equals(error.trim()))) {
            HttpSession sess = request.getSession();
            sess.invalidate();
            response.sendRedirect(request.getContextPath());
            return;
        }
        String code = request.getParameter("code");
        TokenEntity Te = token.GetAuthorization_Code(code);
        response.setContentType("text/html;charset=UTF-8");
        //Get Token Authorization_Code  
        List<String> re = Te.access_token.deposits.get(0).resources;

        //Get List of account number
        List<BalanceEntity> be = new ArrayList<BalanceEntity>();
        //Get Account type 

        //Check Account type 
        if (ServiceType.equals("Statement")) {
            // <editor-fold defaultstate="Statement">
            List<StatementEntity> StatementResponse = new ArrayList<StatementEntity>();
            //for run Statetment Servise for each Account number that saved in resourses attribout
            for (int i = 0; i < re.size(); i++) {
                StatementEntity se = statement.StatementService(Te.access_token.userId,
                        Te.access_token.deposits.get(0).resources.get(i), Te.access_token.value);
                StatementResponse.add(se);
            }
            //convert StatementEntity result to Json Strig
           // ObjectMapper mapper = new ObjectMapper();
           // String jsonInString = mapper.writeValueAsString(StatementResponse);
            request.setAttribute("Info", StatementResponse);
            request.getRequestDispatcher("Statement.jsp").forward(request, response);
            // </editor-fold>
        }
        else if (ServiceType.equals("Balance")) {
            // <editor-fold defaultstate="Balance">
            for (int i = 0; i < re.size(); i++) {
                BalanceEntity e = balance.BalanceService(Te.access_token.userId,
                        Te.access_token.deposits.get(0).resources.get(i), Te.access_token.value);
                be.add(e);
            }
            String url = "http://localhost:8080/AdanikProject/BalanceForm.html?";

            for (int i = 0; i < be.size(); i++) {
                if (be.get(i).code == 0) {
                    url += "&number=" + be.get(i).number + "&" + "balance=" + be.get(i).availableBalance;
                }
            }
            if (!url.equals("http://localhost:8080/AdanikProject/BalanceForm.html?")) {
                response.sendRedirect(url);
            } else {
                try (PrintWriter out = response.getWriter()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet TokenServlet</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h2>" + "<br><br>" + "url is empty"
                            + "</h2>");
                    out.println("</body>");
                    out.println("</html>");
                }
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
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
