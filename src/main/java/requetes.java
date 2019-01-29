import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class requetes implements IDAO<Contact, Long> {

    private static final String INSERT_QUERY = "INSERT INTO contact (name, email, phone, type_var, type_num) values (?,?,?,?,?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM contact  WHERE id = ?";
    private static final String FIND_ALL_QUERY = "Select * From contact";
    private static final String UPDATE_QUERY = "update contact set name = ?";
    private static final String REMOVE_QUERY = "delete from contact where id= ?";

    @Override
    public void create( Contact c ) throws SQLException {

        Connection connection = bdd.getConnection();
        PreparedStatement st = connection.prepareStatement( INSERT_QUERY, Statement.RETURN_GENERATED_KEYS );
        st.setString( 1, c.getName() );
        st.setString( 2, c.getEmail() );
        st.setString( 3, c.getPhone() );
        st.setString( 4, c.getType().getValue() );
        st.setInt( 5, c.getType().ordinal() );
        st.executeUpdate();
        ResultSet rs = st.getGeneratedKeys();

        if ( rs.next() ) {
            c.setId( rs.getString( 1 ) );
        }
    }

    @Override
    public Contact findById( Long aLong ) throws SQLException {
        Connection connection = bdd.getConnection();
        PreparedStatement st = connection.prepareStatement( FIND_BY_ID_QUERY );
        st.setLong( 1, aLong );
        ResultSet rset = st.executeQuery();
        if(rset.next()){
            Contact contact = new Contact();
            long id = rset.getLong("id");
        }
        return null;
    }

    @Override
    public List<Contact> findAll() throws SQLException {
        ArrayList<Contact> arrlist = new ArrayList<Contact>();
        Connection connection = bdd.getConnection();
        PreparedStatement st = connection.prepareStatement( FIND_ALL_QUERY );
        ResultSet rset = st.executeQuery();
        while(rset.next())
        {
            Contact contact = new Contact();
            arrlist.add(contact);
        }
        return arrlist;
    }

    @Override
    public Contact update( Contact o ) throws SQLException{
        Connection connection = bdd.getConnection();
        PreparedStatement st = connection.prepareStatement( UPDATE_QUERY);
        st.setString( 1, "Roger");
        st.executeUpdate();
        return null;
    }

    @Override
    public void remove( Contact o ) throws SQLException{
        Connection connection = bdd.getConnection();
        PreparedStatement st = connection.prepareStatement(REMOVE_QUERY);
        st.setInt( 1, 1);
        st.executeQuery();

    }
}
