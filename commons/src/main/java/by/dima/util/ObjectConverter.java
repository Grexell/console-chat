package by.dima.util;

public interface ObjectConverter {
    String write(Object t);
    <T> T read(String value, Class<T> type);
}
