package loginapp;

public enum option {
    Admin, Student, Teacher;

    private option() {
    }

    public String value() {
        return name();
    }

    public static option fromvalue(String v) {
        return valueOf(v);
    }
}

