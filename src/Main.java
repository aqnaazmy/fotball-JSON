import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final List<FootballTeam> footballTeams = new ArrayList<>();

    public static void main(String[] args) {
        readJSONFile();
        displayData();
        searchClub();
        convertNameToHex(); // Mengkonversi nilai name menjadi bilangan heksadesimal jika code memiliki nilai tengah 'n'
    }

    private static void readJSONFile() {
        JSONParser jsonParser = new JSONParser();
        try {
            FileReader fileReader = new FileReader("src/brazil.json");
            Object objContent = jsonParser.parse(fileReader);
            JSONObject content = new JSONObject(objContent.toString());
            JSONArray jsonArray = new JSONArray(content.getJSONArray("clubs"));

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                String code = jsonObject.optString("code");
                String country = jsonObject.getString("country");

                FootballTeam footballTeam = new FootballTeam(name, code, country);
                footballTeams.add(footballTeam);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void displayData() {
        System.out.println("Data in JSON File:");
        for (FootballTeam team : footballTeams) {
            System.out.println("Name: " + team.getName());
            System.out.println("Code: " + team.getCode());
            System.out.println("Country: " + team.getCountry());

            System.out.println();
        }
    }

    private static void searchClub() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Cari nama club :");
        String searchName = scanner.nextLine();

        boolean found = false;
        for (FootballTeam team : footballTeams) {
            if (team.getName().equalsIgnoreCase(searchName)) {
                System.out.println("Hasil Pencarian Club");
                System.out.println("Name: " + team.getName());
                System.out.println("Code: " + team.getCode());
                System.out.println("Country: " + team.getCountry());
                System.out.println();
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Club not found.");
        }

        scanner.close();
    }

    private static void convertNameToHex() {
        System.out.println("Nama dalam bentuk hexa '" + 'N' + "'):");
        for (FootballTeam team : footballTeams) {
            String code = team.getCode();
            if (code != null && code.length() >= 3 && code.charAt(1) == 'N') {
                String name = team.getName();
                String hex = convertToHex(name);
                System.out.println("Name: " + name);
                System.out.println("Hex: " + hex);
                System.out.println();
            }
        }
    }

    private static String convertToHex(String name) {
        StringBuilder hexBuilder = new StringBuilder();
        for (char c : name.toCharArray()) {
            hexBuilder.append(Integer.toHexString(c));
        }
        return hexBuilder.toString();
    }
}
