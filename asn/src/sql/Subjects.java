package sql;

public enum Subjects {
    Cpp("c++"),
    Python("Python"),
    Java("Java"),
    MATLAB("MATLAB"),
    SPSS("SPSS"),
    R("R"),
    MachineLearning("Machine Learning"),
    DeepLearning("Deep Learning"),
    Linux("Linux"),
    Android("Android"),
    Flutter("Flutter"),
    SmartHome("Smart Home"),
    MicrocontrollersBasicApplications("Microcontrollers Basic Applications"),
    ARMAdvancedApps("ARM Advanced Apps");
    private final String name;

    Subjects(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Subjects getByName(String name) {
        for (Subjects s : Subjects.values()) {
            if (s.getName().equalsIgnoreCase(name)) {
                return s;
            }
        }
        return null;
    }
}