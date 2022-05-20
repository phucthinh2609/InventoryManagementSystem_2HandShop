package vn.mvpthinh.model;

public enum Type {
    IN ("IN"),
    OUT ("OUT");

    private String value;

    private Type(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static Type parseRole(String value) {
        Type[] values = values();
        for (Type role : values) {
            if (role.value.equals(value))
                return role;
        }
        throw new IllegalArgumentException("invalid");
    }
}
