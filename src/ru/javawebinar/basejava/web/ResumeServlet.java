package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ResumeServlet extends HttpServlet {
    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
//        response.setHeader("Content-Type", "text/html; charset=UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String uuid = request.getParameter("uuid");
//        response.getWriter().write(uuid == null ? "Hello Resumes!" : "Hello " + name + '!');
        List<Resume> resumeList = storage.getAllSorted();
        StringBuilder resumeView = new StringBuilder();
        Resume resume = null;
        if (uuid != null) {
            resume = storage.get(uuid);
        }
        for (Resume r : resumeList) {
            resumeView.append("<tr>\n<td>")
                    .append(r.getUuid())
                    .append("</td>\n<td>")
                    .append(r.getFullName())
                    .append("</td>\n</tr>\n");
        }
        response.getWriter().write(uuid == null ?
                "<html>\n" +
                        "<head>\n" +
                        "<title>Resume</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<table border=\"1\">\n" +
                        "<tr>\n" +
                        "<th>uuid</th>\n" +
                        "<th>full_name</th>\n" +
                        "</tr>\n" +
                        resumeView.toString() +
                        "</table>\n" +
                        "</body>\n" +
                        "</html>" :
                        "<html>\n" +
                        "<head>\n" +
                        "<title>Resume</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        resume +
                        "</body>\n" +
                        "</html>"
        );
    }
}