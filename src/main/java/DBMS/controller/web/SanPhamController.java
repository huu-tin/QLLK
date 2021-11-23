package DBMS.controller.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBMS.dao.LoaiDao;
import DBMS.dao.SanPhamDao;
import DBMS.model.LoaiModel;
import DBMS.model.SanPhamModel;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/sanpham"})
public class SanPhamController extends HttpServlet{
	SanPhamDao spdao = new SanPhamDao();
	LoaiDao loaidao = new LoaiDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String indexPage = req.getParameter("index");
		if (indexPage == null) {
			indexPage = "1";
		}
		int index = Integer.parseInt(indexPage);
		
		int amount = 0;
		
		amount = spdao.CountAll();
		
		int endPage = amount/9;
		if (amount % 9 != 0) {
			endPage = endPage + 1;
		}
		
		List<SanPhamModel> listsp = spdao.getPagingSanPham(index);
			
		/*List<SanPhamModel> listsp = spdao.getAllLinhKien();*/
		
		req.setAttribute("listsp", listsp);
		
		req.setAttribute("endP", endPage);
		req.setAttribute("tag", index);
		
		List<LoaiModel> listloai = loaidao.getAllLoai();
		
		req.setAttribute("listloai", listloai);
		
		RequestDispatcher rq = req.getRequestDispatcher("/views/web/web_product.jsp");
		rq.forward(req, resp);
	}
}