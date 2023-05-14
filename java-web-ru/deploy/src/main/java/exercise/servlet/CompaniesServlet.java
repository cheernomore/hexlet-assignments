package exercise.servlet;

import exercise.Data;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.stream.Collectors;
import static exercise.Data.getCompanies;

@WebServlet
public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        // BEGIN
        PrintWriter pw = response.getWriter();
        String searchParam = request.getParameter("search");

        if (request.getQueryString() == null) {
            for (String company: getCompanies()) {
                pw.write(company + "\n");
            }
            pw.close();
        }

        List<String> filteredUsers = getCompanies().stream()
                .filter(item -> item.contains(searchParam))
                .toList();

        if (filteredUsers.size() != 0) {
            for (String company: Data.getCompanies()) {
                if (company.contains(searchParam)) {
                    pw.write(company + "\n");
                }
            }
            pw.close();
        } else if (searchParam == null || searchParam.equals("")) {
            for (String company: getCompanies()) {
                pw.write(company + "\n");
            }
            pw.close();
        }
        pw.write("Companies not found");
        pw.close();
        // END
    }
}
