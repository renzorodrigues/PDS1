package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Usuario;
import servico.ServicoException;
import servico.UsuarioServico;

@WebServlet("/usuarios/excluir_definitivo")
public class UsuarioExcluirDefinitivo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static String DESTINO = "/usuarios/listar.jsp";
	private static String ERRO = "/publico/erro.jsp";
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UsuarioServico uS = new UsuarioServico();
		int cod = Integer.parseInt(request.getParameter("cod"));
		Usuario user = uS.buscar(cod);
		
		try {
			uS.excluir(user);
			List<Usuario> itens = uS.buscarTodos();
			request.setAttribute("itens", itens);
			request.getRequestDispatcher(DESTINO).forward(request, response);
		} catch (ServicoException e) {
			// TODO Auto-generated catch block
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher(ERRO).forward(request, response);
		}		
	}
}
