package model.dal;

import model.Agent;
import model.Borrow;
import model.Equipment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgentDAO {
    private static final String GET_AGENTS_QUERY = "SELECT agent_id, agent_name, agent_image_url FROM agent";
    private static final String GET_AGENTS_NAME_BY_ID = "SELECT agent_name FROM agent WHERE agent_id = ?";

    public List<Agent> getAgents() throws SQLException {
        List<Agent> agents = new ArrayList<>();
        Connection connection = PersistenceManager.getConnection();
        PreparedStatement st = connection.prepareStatement( GET_AGENTS_QUERY, Statement.RETURN_GENERATED_KEYS );

        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Agent a = new Agent(rs.getInt("agent_id")
                    , rs.getString("agent_name")
                    , rs.getString("agent_image_url"));

            agents.add(a);
        }
	// sout rien tamere
        return agents;
    }

    public List<Equipment> getEquipmentsFromAgent(int agent_id) throws SQLException {
        List<Borrow> borrows = new BorrowDAO().getBorrowsFromAgent(agent_id);
        List<Equipment> equipments = new EquipmentDAO().getEquipmentsWithBorrow();

        equipments.forEach(equipment -> {
            equipment.setBorrowed(false);
            borrows.forEach(borrow -> {
                if (borrow.getEquipment_id() == equipment.getId()) {
                    equipment.setBorrowed(true);
                }
            });
        });


        return equipments;
    }
    public String GetAgentName(Integer Id) throws SQLException
    {
        Connection connection = PersistenceManager.getConnection();
        PreparedStatement st = connection.prepareStatement(GET_AGENTS_NAME_BY_ID, Statement.RETURN_GENERATED_KEYS );
        st.setInt(1, Id);
        ResultSet rs = st.executeQuery();
        if(rs.next())
        {
            return rs.getString("agent_name");
        }

        return null;
    }

}
