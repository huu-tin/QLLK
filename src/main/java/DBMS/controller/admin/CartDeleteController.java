package DBMS.controller.admin;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DBMS.dao.CartDao;
import DBMS.dao.CartItemDao;
import DBMS.model.CartItemModel;
import DBMS.model.CartModel;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/admin/cart-delete"})
public class CartDeleteController extends HttpServlet{
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String magiohang = req.getParameter("magiohang");
		
		HttpSession session  = req.getSession();
		Connection conn = (Connection) session.getAttribute("connect");
		CartDao cartdao = new CartDao(conn);
		CartItemDao cartitemdao = new CartItemDao(conn);
		
		CartModel cart = cartdao.getGioByMaGioHang(magiohang);
		
		String alert = "";
		if (cartdao.delete(magiohang) == 1) {
			resp.sendRedirect(req.getContextPath() + "/admin/cart");
		} else {
			alert = "Xóa thất bại";
			req.setAttribute("alertmess", alert);
			req.setAttribute("cart", cart);
			List<CartItemModel> listcartitem = cartitemdao.getSanPhamByMaGioHang(magiohang);
			
			req.setAttribute("listcartitem", listcartitem);
			req.getRequestDispatcher("/views/admin/giohang/chitietgiohang.jsp").forward(req, resp);
		}
		
	}
}
