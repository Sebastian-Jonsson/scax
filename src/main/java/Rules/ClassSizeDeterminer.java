package Rules;

import actions.FileReport;

import java.util.ArrayList;
import java.util.Collections;

public class ClassSizeDeterminer {

    RulesConfig rule = new RulesConfig();
    public double medianClassLength = 0;
    private int classLength = 0;
    private boolean classStarted;
    private int parentOpenBrace = 0;
    private int parentEndBrace = 0;

    public int addClassLength(FileReport report, String line) {
        if (line.matches(rule.classRegex) || classStarted) {
            classStarted = true;
            classLength++;

            if (line.contains("{")) {
                parentOpenBrace++;
            }
            if (line.contains("}")) {
                parentEndBrace++;
            }
            if (parentOpenBrace == parentEndBrace) {
                classStarted = false;
                classLength++;
                int returnLength = classLength;
                classLength = 0;
                return returnLength;
            }
        }

        return 0;
    }

    public void medianDeterminer(ArrayList<Integer> classList) {
        Collections.sort(classList);
        int arraySize = classList.size();
        int middleNumber = arraySize / 2;

        if ((arraySize % 2) == 0) {
            medianClassLength = ((double)classList.get(middleNumber - 1) + (double)classList.get(middleNumber)) / 2;
        }
        else {
            medianClassLength = (double)classList.get(middleNumber);
        }
    }

}
