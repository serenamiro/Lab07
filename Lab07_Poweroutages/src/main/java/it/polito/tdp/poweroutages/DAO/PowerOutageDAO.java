package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.Out;

public class PowerOutageDAO {
	
	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	

	public List<Out> selecData(int nercId){
		
		String sql = "SELECT date_event_began, date_event_finished, HOUR(TIMEDIFF(date_event_finished,date_event_began)) AS hours, YEAR(date_event_began) AS year, customers_affected " + 
				"FROM poweroutages " + 
				"WHERE nerc_id = ? "+
				"ORDER BY year";
		
		List<Out> found = new ArrayList<>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, nercId);
			ResultSet res = st.executeQuery();
			
			
			while(res.next()) {
				Out o = new Out(res.getTimestamp("date_event_began").toInstant(), res.getTimestamp("date_event_finished").toInstant(), res.getInt("hours"), res.getInt("year"), res.getInt("customers_affected"));
				found.add(o);
			}
			conn.close();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return found;
	}
}
