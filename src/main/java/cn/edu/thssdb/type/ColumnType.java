package cn.edu.thssdb.type;

public enum ColumnType {
  INT,
  LONG,
  FLOAT,
  DOUBLE,
  STRING;

  public static Comparable getEntryByType(String value, ColumnType type) {
    try {
      switch (type) {
        case INT:
          return Integer.parseInt(value);
        case LONG:
          return Long.parseLong(value);
        case FLOAT:
          return Float.parseFloat(value);
        case DOUBLE:
          return Double.parseDouble(value);
        case STRING:
          // null
          if (value.equals("null")) {
            return null;
          }
          // delete the ' and "
          return value.substring(1, value.length() - 1);
        default:
          return null;
      }
    } catch (Exception e) {
      throw new RuntimeException("Type Error");
    }
  }
}
