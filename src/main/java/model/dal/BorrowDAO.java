package model.dal;

import model.Borrow;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowDAO {

    private static final String CREATE_BORROW_QUERY = "INSERT INTO borrow (agent_id, equipment_id, borrow_date_borrow, borrow_date_return) values (?,?,?,?)";
    private static final String SET_BORROW_RETURN_DATE_QUERY = "UPDATE borrow SET borrow_date_return = ? WHERE borrow_id = ?";
    private static final String GET_BORROW_AGENT_QUERY = "SELECT borrow_id, agent_id, equipment_id, borrow_date_borrow, borrow_date_return FROM borrow WHERE agent_id = ?";
    private static final String GET_BORROW_FROM_EQUIPMENT_AGENT = "SELECT borrow_id FROM borrow WHERE agent_id = ? AND equipment_id = ?";
    private static final String GET_BORROWS = "SELECT borrow_id, agent_id, equipment_id, borrow_date_borrow, borrow_date_return FROM borrow";


    public List<Borrow> getBorrows() throws SQLException {
        List<Borrow> borrows = new ArrayList<>();

        Connection connection = PersistenceManager.getConnection();
        PreparedStatement st = connection.prepareStatement( GET_BORROWS, Statement.RETURN_GENERATED_KEYS );

        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            if (rs.getString("borrow_date_return") == null) {
                Borrow b = new Borrow(rs.getInt("borrow_id")
                        , rs.getInt("agent_id")
                        , rs.getInt("equipment_id")
                        , rs.getString("borrow_date_borrow")
                        , rs.getString("borrow_date_return"));
                borrows.add(b);
            }
        }

        return borrows;
    }

    List<Borrow> getBorrowsFromAgent(int agent_id) throws SQLException {
        List<Borrow> borrows = new ArrayList<>();

        Connection connection = PersistenceManager.getConnection();
        PreparedStatement st = connection.prepareStatement( GET_BORROW_AGENT_QUERY, Statement.RETURN_GENERATED_KEYS );
        st.setInt(1, agent_id);

        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            if (rs.getString("borrow_date_return") == null) {
                Borrow b = new Borrow(rs.getInt("borrow_id")
                        , rs.getInt("agent_id")
                        , rs.getInt("equipment_id")
                        , rs.getString("borrow_date_borrow")
                        , rs.getString("borrow_date_return"));
                borrows.add(b);
            }
        }

        return borrows;
    }

    public void createBorrow(Borrow b) throws SQLException {
        java.util.Date utilDate = new java.util.Date();
        Connection connection = PersistenceManager.getConnection();
        PreparedStatement st = connection.prepareStatement( CREATE_BORROW_QUERY, Statement.RETURN_GENERATED_KEYS );
        st.setInt( 1, b.getAgent_id());
        st.setInt( 2, b.getEquipment_id());
        st.setDate( 3, new java.sql.Date(utilDate.getTime()));
        st.setDate( 4, null);
        st.executeUpdate();

        System.out.println(st);
    }

    public void setBorrowReturnDate(int equipmentId, int agentId) throws SQLException {
        java.util.Date utilDate = new java.util.Date();
        Connection connection = PersistenceManager.getConnection();
        PreparedStatement st = connection.prepareStatement( GET_BORROW_FROM_EQUIPMENT_AGENT, Statement.RETURN_GENERATED_KEYS );
        st.setInt(1, agentId);
        st.setInt(2, equipmentId);

        ResultSet rs = st.executeQuery();
        int borrowId = 0;
        while (rs.next()) {
            borrowId = rs.getInt("borrow_id");
        }

        st = connection.prepareStatement( SET_BORROW_RETURN_DATE_QUERY, Statement.RETURN_GENERATED_KEYS );
        st.setDate(1, new java.sql.Date(utilDate.getTime()));
        st.setInt(2, borrowId);
        st.executeUpdate();
    }
}
