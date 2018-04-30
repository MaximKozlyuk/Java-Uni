package lab5.barBossHouse.tests;

import lab5.barBossHouse.MyList;
import org.junit.*;
import org.junit.jupiter.api.Test;

class MyListTest {

    private Integer[] testIntArr = new Integer[] {1,2,3,4,5};
    private MyList<Integer> labList = new MyList<>(testIntArr);

    @BeforeClass
    public void beforeClass() {
        System.out.println("\nStarting tests of MyList class\n");
        initTest();
    }

    @AfterClass
    public void afterClass() {
        System.out.println("\nEnd of testing MyList\n");
        afterTest();
    }

    protected void setLabList () {
        labList = new MyList<Integer>(testIntArr);
    }

    @Before
    public void initTest() { labList = new MyList<Integer>(testIntArr); }

    @After
    public void afterTest() { labList = null; }

    @Test
    void testGetSize() { Assert.assertSame(testIntArr.length,labList.getSize()); }

    @Test
    void testContains () { Assert.assertEquals(true, labList.contains(3)); }

    @Test
    void testToArray () { Assert.assertArrayEquals(testIntArr,labList.toArray(new Integer[labList.getSize()]));}
}