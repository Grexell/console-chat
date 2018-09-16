package by.dima.util;

import org.junit.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ListPaginatorTest {

    @Test
    public void paginate() {
        List<String> test = new LinkedList<>();
        test.add("1");
        test.add("2");
        test.add("3");
        test.add("4");
        test.add("5");
        test.add("6");
        test.add("7");
        test.add("8");

        assertEquals(ListPaginator.paginate(test, 0, 5), test.subList(0, 5));
        assertEquals(ListPaginator.paginate(test, 2, 1), test.subList(2, test.size()));
        assertEquals(ListPaginator.paginate(test, 2, 15), test.subList(2, test.size()));
        assertEquals(ListPaginator.paginate(new LinkedList<>(), 2, 1), Collections.emptyList());
        assertEquals(ListPaginator.paginate(new LinkedList<>(), -2, 1), Collections.emptyList());
        assertEquals(ListPaginator.paginate(null, 2, 1), Collections.emptyList());
    }
}