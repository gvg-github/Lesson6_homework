import org.junit.*;

import java.sql.*;

/**
 * Created by GVG on 02.09.2017.
 */
public class TestWorkWithBD {

    private Savepoint save;

    @Before
    public void StartTest() throws SQLException {
        WorkWithBD.connect();
        save = WorkWithBD.createSave();
    }

    @Test
    public void testChangeLine() throws SQLException {
        WorkWithBD.changeLine(4, "Sam", 95);
        String testText = "SELECT * FROM Students WHERE id = 4 and Name = 'Sam' and Ball = 95";
        Assert.assertEquals(true, WorkWithBD.retunRS(testText));
    }

    @Test
    public void testDelLine() throws SQLException {
        WorkWithBD.deleteLine(4);
        String testText = "SELECT * FROM Students WHERE id = 4";
        Assert.assertEquals(false, WorkWithBD.retunRS(testText));
    }

    @Test
    public void testAddLine() throws SQLException {
        WorkWithBD.addLine(6, "Maxim", 95);
        String testText = "SELECT * FROM Students WHERE id = 6 and Name = 'Maxim' and Ball = 95";
        Assert.assertEquals(true, WorkWithBD.retunRS(testText));
    }

    @Test(expected = SQLException.class)
    public void testAddLineEx() throws SQLException {
        WorkWithBD.addLine(5, "Maxim", 95);
        String testText = "SELECT * FROM Students WHERE id = 5 and Name = 'Maxim' and Ball = 95";
        Assert.assertEquals(true, WorkWithBD.retunRS(testText));
    }

    @After
    public void EndTest() throws SQLException {
//        System.out.println("После теста:");
//        WorkWithBD.testTable();
        WorkWithBD.restoreFromSave(save);
//        System.out.println("После восстановления:");
//        WorkWithBD.testTable();
        WorkWithBD.disconnect();
    }
}

