package test;

import model.Borrow;
import model.dal.BorrowDAO;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


class BorrowDAOTest {

    private static  int borrow_id;
    private static int agent_id;
    private static int equipment_id;
    private static String date_borrow;
    private static String date_return;
    private static Borrow B;
    private static BorrowDAO dao;

    @org.junit.jupiter.api.BeforeAll
    static void setUp() {

        
        agent_id = 1;
        equipment_id = 1;
        date_borrow = "2018-12-12";
        date_return = null;
        B = new Borrow(agent_id,equipment_id,date_borrow,date_return);
        dao = new BorrowDAO();
    }

    @org.junit.jupiter.api.Test
    void getBorrows() {
    }

    @org.junit.jupiter.api.Test
    void getBorrowsFromAgent() {
    }

    @org.junit.jupiter.api.Test
    void createBorrow() {


        try {
            int size1 = dao.getBorrows().size();
            dao.createBorrow(B);
            int size2 = dao.getBorrows().size();
            assertEquals(size1+1,size2,"Verification insertion réussie");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @org.junit.jupiter.api.Test
    void setBorrowReturnDate() {
    }
}