package year2019.roundB.energyStones;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainWrapper {
    public static void main(String[] args) throws IOException {
        FileInputStream is = new FileInputStream(new File("src/year2019/roundB/energyStones/inputSample"));
        System.setIn(is);
        Solution.main(args);
    }
}