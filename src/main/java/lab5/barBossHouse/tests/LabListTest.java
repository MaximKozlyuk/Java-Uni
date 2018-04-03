package lab5.barBossHouse.tests;

import lab5.barBossHouse.LabList;
import org.junit.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runners.JUnit4;

class LabListTest {

    private Integer[] testIntArr = new Integer[] {1,2,3,4,5};
    private LabList<Integer> labList = new LabList<>(testIntArr);

    @BeforeClass
    public void beforeClass() {
        System.out.println("\nStarting tests of LabList class\n");
        initTest();
    }

    @AfterClass
    public void afterClass() {
        System.out.println("\nEnd of testing LabList\n");
        afterTest();
    }

    protected void setLabList () {
        labList = new LabList<Integer>(testIntArr);
    }

    @Before
    public void initTest() { labList = new LabList<Integer>(testIntArr); }

    @After
    public void afterTest() { labList = null; }

    @Test
    void testGetSize() { Assert.assertSame(testIntArr.length,labList.getSize()); }

    @Test
    void testContains () { Assert.assertEquals(true, labList.contains(3)); }

    @Test
    void testToArray () { Assert.assertArrayEquals(testIntArr,labList.toArray(new Integer[labList.getSize()]));}
}