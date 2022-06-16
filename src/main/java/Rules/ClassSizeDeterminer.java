package Rules;

import java.util.Collections;
import java.util.List;

public class ClassSizeDeterminer {

    RulesConfig rule = new RulesConfig();
    public List<Integer> classList;
    public double medianClassLength = 0;
    private int classLength = 0;
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
        medianDeterminer();

    }

    private void medianDeterminer() {
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
