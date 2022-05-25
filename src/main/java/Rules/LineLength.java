package Rules;

import actions.*;

public class LineLength {
    RulesConfig rule = new RulesConfig();

    public void maxLineLength(FileReport report, String line, int lineNumber) {
        if (line.length() > rule.MAX_LINE_LENGTH) {
            LineLengthViolation LLV = new LineLengthViolation();
            LLV.lineNumber = lineNumber;
            LLV.actualLength = line.length();
            report.lineLengthViolations.add(LLV);
        }
    }

    public class LineLengthViolation {
        public int lineNumber = 0;
        public int actualLength = 0;
    }
}
