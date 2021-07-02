package year2019.roundF.codeEatSwitcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainWrapper {

    static final String YEAR = "year2019";
    static final String ROUND = "roundF";
    static final String NAME = "codeEatSwitcher";

    public static void main(String[] args) throws IOException {
        FileInputStream is = new FileInputStream(new File("src/" + YEAR + "/" + ROUND + "/" + NAME + "/inputSample"));
        System.setIn(is);
        Solution.main(args);
    }
}