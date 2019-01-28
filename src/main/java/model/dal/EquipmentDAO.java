package model.dal;

import model.Borrow;
import model.Equipment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class EquipmentDAO {

    private static final String GET_EQUIPMENTS_QUERY = "SELECT equipment_id, equipment_name, equipment_quantity FROM equipment";

    private List<Equipment> getEquipments() throws SQLException {
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

    List<Equipment> getEquipmentsWithBorrow() throws SQLException {
        List<Equipment> equipments = this.getEquipments();
        List<Borrow> borrows = new BorrowDAO().getBorrows();
        List<Equipment> equipmentsToReturn = new ArrayList<>();

        equipments.forEach(equipment -> {
            AtomicBoolean isBorrowed = new AtomicBoolean(false);
            borrows.forEach(borrow -> {
                if (equipment.getId() == borrow.getEquipment_id()) {
                    isBorrowed.set(true);
                    AtomicBoolean inArray = new AtomicBoolean(false);
                    equipmentsToReturn.forEach(eq -> {
                        if (eq.getId() == equipment.getId()) {
                            eq.decreaseQuantity();
                            inArray.set(true);
                        }
                    });

                    if (!inArray.get()) {
                        equipment.decreaseQuantity();
                        equipmentsToReturn.add(equipment);
                    }
                }
            });

            if (!isBorrowed.get()) {
                equipmentsToReturn.add(equipment);
            }
        });

        return equipmentsToReturn;
    }
}