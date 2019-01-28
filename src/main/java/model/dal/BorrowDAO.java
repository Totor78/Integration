package model.dal;

import model.Borrow;
import java.sql.*;

public class BorrowDAO {

    private static final String CREATE_BORROW_QUERY = "INSERT INTO borrow (borrow_id, agent_id, equipment_id, borrow_date_borrow, borrow_date_return) values (?,?,?,?,?)";
    private static final String SET_BORROW_RETURN_DATE_QUERY = "UPDATE borrow SET borrow_date_return = ? WHERE borrow_id = ?";

    public void createBorrow(Borrow b) throws SQLException {
        java.util.Date utilDate = new java.util.Date();
        Connection connection = PersistenceManager.getConnection();
        PreparedStatement st = connection.prepareStatement( CREATE_BORROW_QUERY, Statement.RETURN_GENERATED_KEYS );
        st.setInt( 1, b.getBorrow_id());
        st.setInt( 2, b.getAgent_id());
        st.setInt( 3, b.getEquipment_id());
        st.setDate( 4, new java.sql.Date(utilDate.getTime()));
        st.setDate( 5, null);
        st.executeUpdate();
    }

    public void setBorrowReturnDate(Borrow b) throws SQLException {
        java.util.Date utilDate = new java.util.Date();
        Connection connection = PersistenceManager.getConnection();
        PreparedStatement st = connection.prepareStatement( SET_BORROW_RETURN_DATE_QUERY, Statement.RETURN_GENERATED_KEYS );
        st.setDate(1, new java.sql.Date(utilDate.getTime()));
        st.setInt(2, b.getBorrow_id());
        st.executeUpdate();
    }

}
