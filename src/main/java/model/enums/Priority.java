package model.enums;

public enum Priority {
    LOW,
    MEDIUM,
    HIGH;

  public static Priority getPriorityFromString(String string) {
        try {
            return Priority.valueOf(string.toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new RuntimeException(
                    "Invalid value for enum: " + string);
        }
    }
}
