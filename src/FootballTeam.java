public class FootballTeam {
    private String name;
    private String code;
    private String country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    // Constructor
    public FootballTeam(String name, String code, String country) {
        this.name = name;
        this.code = code;
        this.country = country;
    }
}
