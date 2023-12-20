package ex1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/serv_persoana")
public class serv_persoana extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PrintWriter pw;
    private List<Persoana> listpersoana;

    public serv_persoana() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String numeprenume = request.getParameter("numeprenume");
        int zi = Integer.parseInt(request.getParameter("zi"));
        int luna = Integer.parseInt(request.getParameter("luna"));
        int an = Integer.parseInt(request.getParameter("an"));
        String adresa = request.getParameter("adresa");
        String telefon = request.getParameter("telefon");

        // Retrieve the listpersoana from the session
        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        List<Persoana> listpersoana = (List<Persoana>) session.getAttribute("listpersoana");

        if (listpersoana == null) {
            listpersoana = new ArrayList<>();
            session.setAttribute("listpersoana", listpersoana);
        }

        PrintWriter pw = response.getWriter();
        pw.println("<html><head><title>Persoanele aduagate</title></head><body>" + "Persoanele aduagate</body></html>");

        if (numeprenume == null || adresa == null || telefon == null) {
            pw.println("Something is null");
        } else {
            try {
                Persoana p = new Persoana(numeprenume, zi, luna, an, adresa, telefon);
                listpersoana.add(p);
            } catch (Exception e) {
                pw.println("Error: " + e.getMessage());
            }
        }

        pw.println("<table align='center' border='3'>");
        pw.println("<th> nume si prenume </th>");
        pw.println("<th> data </th>");
        pw.println("<th> adresa </th>");
        pw.println("<th> telefon </th>");

        for (Persoana p : listpersoana) {
            pw.println("<tr>");
            pw.println("<td>");
            pw.println(p.getNumeprenume());
            pw.println("</td>");
            pw.println("<td>");
            pw.println(p.getZi() + "/" + p.getLuna() + "/" + p.getAn());
            pw.println("</td>");
            pw.println("<td>");
            pw.println(p.getAdresa());
            pw.println("</td>");
            pw.println("<td>");
            pw.println(p.getTelefon());
            pw.println("</td>");
            pw.println("</tr>");
        }
        pw.println("</table>");

        pw.println("<form action=\"serv_persoana\" method=\"post\">");
        pw.println("<td>");
        pw.print("<select name=\"selectedMonth\">"); // Fixed attribute name
        pw.print("<option value=\"1\">ianuarie</option> ");
        pw.print("<option value=\"2\">februarie</option> ");
        pw.print("<option value=\"3\">martie</option> ");
        pw.print("<option value=\"4\">aprilie</option> ");
        pw.print("<option value=\"5\">mai</option> ");
        pw.print("<option value=\"6\">iunie</option> ");
        pw.print("<option value=\"7\">iulie</option> ");
        pw.print("<option value=\"8\">august</option> ");
        pw.print("<option value=\"9\">septembrie</option> ");
        pw.print("<option value=\"10\">octombrie</option> ");
        pw.print("<option value=\"11\">novembrie</option> ");
        pw.print("<option value=\"12\">decembrie</option> ");
        pw.print("</select>");
        pw.print("<input type=\"SUBMIT\" value=\"Vizualizare\">");
        pw.println("</td>");
        pw.println("</form>");


        pw.println("<a HREF=\"http://localhost:8082/lab10/informatii.html\"> Adaugare </a> ");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       int selectedMonth = Integer.parseInt(request.getParameter("selectedMonth"));


       // Retrieve the listpersoana from the session
       HttpSession session = request.getSession();
       @SuppressWarnings("unchecked")
       List<Persoana> listpersoana = (List<Persoana>) session.getAttribute("listpersoana");

       if (listpersoana == null) {
           listpersoana = new ArrayList<>();
           session.setAttribute("listpersoana", listpersoana);
       }

       PrintWriter pw = response.getWriter(); // Initialize pw here

       pw.println("<table align='center' border='3'>");
       pw.println("<tr>");
       pw.println("<th> nume si prenume </th>");
       pw.println("<th> data </th>");
       pw.println("<th> adresa </th>");
       pw.println("<th> telefon </th>");
       pw.println("</tr>");

       for (Persoana p : listpersoana) {
           if (p.getLuna() == selectedMonth) {
               pw.println("<tr>");
               pw.println("<td>" + p.getNumeprenume() + "</td>");
               pw.println("<td>" + p.getZi() + "/" + p.getLuna() + "/" + p.getAn() + "</td>");
               pw.println("<td>" + p.getAdresa() + "</td>");
               pw.println("<td>" + p.getTelefon() + "</td>");
               pw.println("</tr>");
           }
       }

       pw.println("</table>");

   }

}
