package Rules;

import java.util.ArrayList;
import java.util.Collections;

public class ClassSizeDeterminer {

    RulesConfig rule = new RulesConfig();
    public int classLength = 0;
    public ArrayList<Integer> classList;
    private double medianClassLength = 0;
    private boolean classStarted;
    private int parentOpenBrace = 0;
    private int parentEndBrace = 0;

    public void addClassLength(String line) {
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
                classList.add(classLength);
                classLength = 0;
            }
        }
        Collections.sort(classList);
        medianDeterminer();
    }

    private void medianDeterminer() {
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
