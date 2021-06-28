package year2019.roundD.latestGuests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainWrapper {

    static final String YEAR = "year2019";
    static final String ROUND = "roundD";
    static final String NAME = "latestGuests";
    static final boolean normalSample = true;

    public static void main(String[] args) throws IOException {
        FileInputStream is = new FileInputStream(new File("src/" + YEAR + "/" + ROUND + "/" + NAME + "/" + (normalSample ? "inputSample" : "additionalInputSample")));
        System.setIn(is);
        Solution.main(args);
    }
}