/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientservlet;

import com.dataaccess.webservicesserver.NumberConversion;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author akhilchandran
 */
@WebServlet(name = "NewNumberConvertionServlet", urlPatterns = {"/NewNumberConvertionServlet"})
public class NewNumberConvertionServlet extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/www.dataaccess.com/webservicesserver/NumberConversion.wso.wsdl")
    private NumberConversion service;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }
            }
        };

// Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
        }

// Now you can access an https URL without having the certificate in the truststore
        try {
            URL url = new URL("https://hostname/index.html");
        } catch (MalformedURLException e) {
        }

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            com.dataaccess.webservicesserver.NumberConversionSoapType port = service.getNumberConversionSoap();
            // TODO initialize WS operation arguments here

            String num = request.getParameter("CNumber");

            java.math.BigInteger ubiNum = new java.math.BigInteger(num);
            java.math.BigDecimal dNum = new java.math.BigDecimal(num);
            // TODO process result here
            java.lang.String resultWords = port.numberToWords(ubiNum);
            // TODO process result here
            java.lang.String resultDollars = port.numberToDollars(dNum);

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewNumberConvertionServlet</title>");
            out.println("<style>h1{font: 3rem \"Roboto\", sans-serif; color:white;"
                    + "    font-weight: bold; /* specifying font weight */\n"
                    + "    font-size: 4rem; /* specifying font size */\n"
                    + "    letter-spacing: 0.02em; /* specifying letter spacing */\n"
                    + "    padding: 0 0 30px 0; /* specifying padding */"
                    + "}"
                    + "body{background:#012631; text-align: center;}"
                    + ".bodyText{padding-top:15%;}"
                    + "/* FOOTER\n"
                    + "–––––––––––––––––––––––––––––––––––––––––––––––––– */\n"
                    + ".footer {\n"
                    + "    text-align: center;\n"
                    + "    font-size: 1rem;\n"
                    + "    color: white;\n"
                    + "    margin-top: 40px;\n"
                    + "    color: #ffffff;\n"
                    + "}\n"
                    + "\n"
                    + "footer {\n"
                    + "    position: fixed; \n"
                    + "    padding: 10px 10px 0px 10px; \n"
                    + "    bottom: 0; \n"
                    + "    width: 100%;  \n"
                    + "    height: 40px;\n"
                    + "}\n"
                    + "\n"
                    + ".heartSymbel {\n"
                    + "    color: rgb(238, 6, 6);\n"
                    + "    font-size: 16px;\n"
                    + "    padding-left: 5px;\n"
                    + "    padding-right: 5px;\n"
                    + "}"
                    + "</style>");
            out.println("</head>");
            out.println("<body><div class=\"bodyText\">");
            out.println("<h1>In Words: " + "<font color='red'> " + resultWords + "</font></h1>");
            out.println("<h1>In Dollars: " + "<font color='red'>" + resultDollars + "</font></h1></div>");
            out.println("        <footer class=\"footer\"> <!--start footer-->\n"
                    + "            <div class=\"container\"> <!--div start with class-->\n"
                    + "                <!--footer with copy symbol, class, heart symbole and my name and student id-->\n"
                    + "                <small>&copy; 2020 made with<span class=\"heartSymbel\">&#10084; </span>by Akhil Chandran - 2017219</small>\n"
                    + "            </div> <!--end div-->\n"
                    + "        </footer> <!--end footer-->");
            out.println("</body>");
            out.println("</html>");
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

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
