/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neededfiles;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import pkg.CalWebService_Service;
import pkg.Courses;

/**
 *
 * @author PR-PC
 */
public class ThisShouldWorkServlet extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/ItHappens/CalWebService.wsdl")
    private CalWebService_Service service;



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
             try{
        PrintWriter out = response.getWriter();
      String cname = request.getParameter("cname");
     //int pageno = Integer.parseInt( request.getParameter("page"));
      List<Courses> macha = new ArrayList<Courses>();
     macha = getData(cname);
     //out.println(pageno);     
     out.print("<!DOCTYPE html>\n" +
"<html lang=\"en\">\n" +
"<head>\n" +
"  <title>Results</title>\n" +
"  <meta charset=\"utf-8\">\n" +
"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
"  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\n" +
"  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js\"></script>\n" +
"  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\n" +
"</head>\n" +
"<body>\n" +
"\n" +
"<div class=\"container\">\n" +
"  <h2>Showing results for keyword:  "+cname+"</h2>\n" +
"  <div class=\"list-group\">\n" );

             
     for(Courses c: macha){
    
     
out.print("    <a href='#' class=\"list-group-item\">\n" +
"      <h4 class=\"list-group-item-heading\">"+c.getName()+"</h4>\n" +
"      <p class=\"list-group-item-text\"><b>offered by : </b>"+c.getWebsite()+"</p>\n" +
  "   <p class=\"list-group-item-text\"><b>University : </b>"+c.getUniv()+"</p>\n" +
 "   <p class=\"list-group-item-text\"><b>Type :  </b>"+c.getSpl()+"</p>\n" +
 " <a href=\""+c.getLink()+"\" class=\"btn btn-info\" role=\"button\">Go to course page</a><br><br><br>"+
"    </a>\n" );
        }
out.print("  </div>\n" +
"</div>\n" +
"\n" +
"</body>\n" +
"</html>");
 
       }catch(Exception e){
       e.printStackTrace();
       }
    }

    private java.util.List<pkg.Courses> getData(java.lang.String arg0) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        pkg.CalWebService port = service.getCalWebServicePort();
        return port.getData(arg0);
    }

}
