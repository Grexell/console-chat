package by.dima.util;

import java.util.Collections;
import java.util.List;

public class ListPaginator {
    public static <T> List<T> paginate(List<T> list, int from, int to) {
        List<T> result;
        if (list != null && !list.isEmpty() && from < list.size()) {
            if (to > from && to <= list.size()) {
                result = list.subList(from, to);
            } else {
                result = list.subList(from, list.size());
            }
        } else {
            result = Collections.emptyList();
        }
        return result;
    }
}
