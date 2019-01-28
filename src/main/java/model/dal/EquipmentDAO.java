package model.dal;

import model.Equipment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipmentDAO {

    private static final String GET_EQUIPMENTS_QUERY = "SELECT equipment_id, equipment_name, equipment_quantity FROM equipment";

    public List<Equipment> getEquipments() throws SQLException {
        List<Equipment> equipments = new ArrayList<>();

        Connection connection = PersistenceManager.getConnection();
        PreparedStatement st = connection.prepareStatement( GET_EQUIPMENTS_QUERY, Statement.RETURN_GENERATED_KEYS );

        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Equipment e = new Equipment(rs.getInt("equipment_id")
                    , rs.getString("equipment_name")
                    , rs.getInt("equipment_quantity"));

            equipments.add(e);
        }

        return equipments;
    }
}