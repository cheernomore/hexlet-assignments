package exercise.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import exercise.User;
import org.apache.commons.lang3.ArrayUtils;

@WebServlet
public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        ObjectMapper objectMapper = new ObjectMapper();
        List<User> userList = objectMapper.readValue(
                new File("src/main/resources/users.json"),
                new TypeReference<List<User>>(){});

        return userList;
        // END
    }

    private void showUsers(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException {

        // BEGIN
        List<User> userList = getUsers();
        PrintWriter pw = response.getWriter();
        pw.write("<table>" + "<tr>" + "<th>id</th>" + "<th>fullName</th>" + "</tr>");

        for (User user: userList) {
            pw.write("<tr>" + "<td>" + user.getId() + "</td>"
                    + "<td>" + "<a href=\"/users/" + user.getId() + "\"" + ">" + user.getFirstName() + " "
                    + user.getLastName() + "</a>" + "</td>" + "</tr>");
        }
        pw.write("</table>");
        pw.close();
        // END
    }

    private void showUser(HttpServletRequest request,
                         HttpServletResponse response,
                         String id)
                 throws IOException {

        // BEGIN
        PrintWriter pw = response.getWriter();
        List<User> userList = getUsers();

        User user = userList.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Not found");
        } else {
            pw.write("<table>" + "<tr>" + "<th>id</th" + "<th>fullName</th>" + "<th>email</th>" + "</tr>");
            pw.write("<tr>" + "<td>" + user.getId() + "</td>"
                    + "<td>" + user.getFirstName() + " " + user.getLastName() + "</td>"
                    + "<td>" + user.getEmail() + "</td>" + "</tr>" + "</table>");
            pw.close();
        }
        // END
    }
}
