package DBMS.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DBMS.connection.DBConnect;
import DBMS.model.DonHangModel;


public class DonHangDao {
	private Connection conn;
	PreparedStatement ps=null;
	CallableStatement cstm=null;
	ResultSet rs=null;
	
	public DonHangDao(Connection conn) {
		super();
		this.conn = conn;
	}
	public List<DonHangModel> ShowList()
	{
		
		List<DonHangModel> listdonhang= new ArrayList<DonHangModel>();
		
		// KHAI BÁO CÂU TRUY VẤN
		String sql="{ call up_DanhSachDonHang }";
		try {
			// MỞ KẾT NỐI DATABASE
			
			// NÉM CÂU SQL
			//ps = conn.prepareStatement(sql);
			cstm = conn.prepareCall(sql);
			
			// THỰC THI CÂU SQL
			rs = cstm.executeQuery();
			// LẤY RESULTSET ĐỔI VÀO LIST
			while (rs.next()) {
				listdonhang.add(new DonHangModel(rs.getString(1),rs.getDate(2),rs.getString(3) ));
			}
			
			
		} catch (Exception e) {
			
		}
		return listdonhang;
	}
	public DonHangModel getIdDonHang(String id) {
		
		DonHangModel donhangmodel= new DonHangModel();
		
		String sql = "select * from ap_get_donhangBymadonhang(?) ";
		try {
			
			
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			
			// THỰC THI CÂU SQL
			rs = ps.executeQuery();
			while (rs.next()) {
				donhangmodel = new DonHangModel(rs.getString(1),rs.getDate(2),rs.getString(3));
				
			}
		
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return donhangmodel;
		
	}
	// THÊM ĐƠN HÀNG MỚI
	public int insert (DonHangModel donhangmodel) {
		
		String sql1= "{ call ap_Insert_Donhang(?,?,?,?) }";
		try {
			
			
			cstm = conn.prepareCall(sql1);
			
			cstm.setString(1, donhangmodel.getMadonhang());
			cstm.setDate(2, (java.sql.Date) donhangmodel.getNgaytao());
			cstm.setString(3, donhangmodel.getManguoidung());
			cstm.registerOutParameter(4, java.sql.Types.INTEGER);
			
			cstm.execute();
			int ktra= cstm.getInt(4);
			return ktra;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	// SỬA THÔNG TIN ĐƠN HÀNG
		public int update (DonHangModel donhangmodel) {
			
			String sql1= "{ call ap_Update_Donhang(?,?,?) }";
			try {
				
				
				cstm = conn.prepareCall(sql1);
				
				cstm.setString(1, donhangmodel.getMadonhang());
				//cstm.setDate(2, (java.sql.Date) donhangmodel.getNgaytao());
				cstm.setString(2, donhangmodel.getManguoidung());
				cstm.registerOutParameter(3, java.sql.Types.INTEGER);	
				cstm.execute();
				int ktra= cstm.getInt(3);
				return ktra;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}
	
	// XÓA ĐƠN HÀNG HIỆN TẠI
	public int delete(String madonhang) {
		String sql3="{ call ap_Delete_DonHang(?,?) }";
		try {
			
			cstm = conn.prepareCall(sql3);
			cstm.setString(1, madonhang);
			cstm.registerOutParameter(2, java.sql.Types.INTEGER);
			
			cstm.execute();
			int ktra= cstm.getInt(2);
			return ktra;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	


}
