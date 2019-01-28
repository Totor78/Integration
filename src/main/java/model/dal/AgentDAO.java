package model.dal;

import model.Agent;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgentDAO {
    private static final String GET_AGENTS_QUERY = "SELECT agent_id, agent_name, agent_image_url FROM agent";

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
        return agents;
    }
}
