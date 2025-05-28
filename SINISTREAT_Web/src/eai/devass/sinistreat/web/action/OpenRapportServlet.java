package eai.devass.sinistreat.web.action;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EpEdition
 */
public class OpenRapportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OpenRapportServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		String lien = recupererNomFichier(request);
		java.io.File file = new java.io.File(request.getRealPath(lien));
		file.exists();
		byte[] fileData = new byte[(int) file.length()];
		FileInputStream fis = new FileInputStream(file);
		ServletOutputStream outputStream = null;
		try {
			fis.read(fileData);
		} finally {
			fis.close();
		}

		response.reset();
		response.setContentType("application/pdf");
		response.setContentLength(fileData.length);
		response.setHeader("Content-Disposition", "inline;filename=example.pdf");

		outputStream = response.getOutputStream();
		outputStream.write(fileData);
		outputStream.flush();
		outputStream.close();

	}

	String recupererNomFichier(HttpServletRequest request) {

		String lien = request.getParameter("lien");

		return lien;

	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
