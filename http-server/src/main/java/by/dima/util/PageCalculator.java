package by.dima.util;

public class PageCalculator {
    public static int getStartIndex(int pageNumber, int pageSize) {
        return pageNumber * pageSize;
    }
}
