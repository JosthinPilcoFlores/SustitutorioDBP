package net.examen.cursos.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.examen.cursos.dao.CursoDAO;
import net.examen.cursos.model.Curso;

/**
 * Servlet implementation class CursoServlet
 */
@WebServlet("/CursoServlet")
public class CursoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CursoDAO cursoDAO;
	public void init() {
        cursoDAO = new CursoDAO();
    }
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CursoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertUser(request, response);
                    break;
                case "/delete":
                    deleteUser(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateUser(request, response);
                    break;
                default:
                    listUser(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
	}
	private void listUser(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException, ServletException {
		        List < Curso > listCurso = cursoDAO.selectAllCursos();
		        request.setAttribute("listCurso", listCurso);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("curso-list.jsp");
		        dispatcher.forward(request, response);
		    }

		    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {
		        RequestDispatcher dispatcher = request.getRequestDispatcher("curso-form.jsp");
		        dispatcher.forward(request, response);
		    }

		    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, ServletException, IOException {
		        int id = Integer.parseInt(request.getParameter("id"));
		        Curso existingCurso = cursoDAO.selectCurso(id);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		        request.setAttribute("curso", existingCurso);
		        dispatcher.forward(request, response);

		    }

		    private void insertUser(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException {
		        String nombre = request.getParameter("nombre");
		        String profesor = request.getParameter("profesor");
		        String semestre = request.getParameter("semestre");
		        String pre_requisito = request.getParameter("pre_requisito");
		        Curso newCurso = new Curso(nombre, profesor, semestre, pre_requisito);
		        cursoDAO.insertCurso(newCurso);
		        response.sendRedirect("list");
		    }

		    private void updateUser(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException {
		        int id = Integer.parseInt(request.getParameter("id"));
		        String nombre = request.getParameter("nombre");
		        String profesor = request.getParameter("profesor");
		        String semestre = request.getParameter("semestre");
		        String pre_requisito = request.getParameter("pre_requisito");

		        Curso curso = new Curso(id, nombre, profesor,semestre,pre_requisito);
		        cursoDAO.updateUser(curso);
		        response.sendRedirect("list");
		    }

		    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException {
		        int id = Integer.parseInt(request.getParameter("id"));
		        cursoDAO.deleteUser(id);
		        response.sendRedirect("list");

		    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
