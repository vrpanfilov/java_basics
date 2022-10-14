public enum Hbm2DllAuto {
    CREATE, CREATE_DROP, UPDATE, VALIDATE, NONE;
    public String value() {
        return  names[this.ordinal()];
    }
    private static String[] names = {"create", "create-drop", "update", "validate", "none"};
}

