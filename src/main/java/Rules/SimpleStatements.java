package Rules;

import actions.FileReport;

public class SimpleStatements {

    RulesConfig rule = new RulesConfig();
    private boolean commentStarted;

    public void checkSimpleStatement(FileReport report, String line, int lineNumber) {

        char[] lineArray = new char[line.length()];
        for (int i = 0; i < line.length(); i++) {
            lineArray[i] = line.charAt(i);
        }

        amountOfSemicolon(report, lineArray, line, lineNumber);

    }

    private void amountOfSemicolon(FileReport report,char[] lineArray, String line, int lineNumber) {
        int amountOfSemiColon = 0;
        String previousChar = "";
        boolean stringStarted = false;

        if (isForLoop(line)) {
            amountOfSemiColon = -2;
        }

        for (int i = 0; i < lineArray.length; i++) {

            if (Character.toString(lineArray[i]).equals("'") && !commentStarted) {
                stringStarted = true;

                if (previousChar.equals("'")) {
                    stringStarted = false;
                }
            }
            if (Character.toString(lineArray[i]).equals("\"") && !commentStarted) {
                stringStarted = true;

                if (previousChar.equals("\"")) {
                    stringStarted = false;
                }
            }



            if (Character.toString(lineArray[i]).equals("/") && !stringStarted) { // '//' comment type.

                if (previousChar == "/") {
                    break;
                }
            }

            if (Character.toString(lineArray[i]).equals("*") && !stringStarted) { // '/*' comment type.

                if (previousChar == "/") {
                    commentStarted = true;
                }
            }

            if (Character.toString(lineArray[i]).equals("/") && commentStarted) {

                if (previousChar == "*") {
                    commentStarted = false;
                }
            }

            if (Character.toString(lineArray[i]).equals(";")) {

                if (!commentStarted && !stringStarted) {
                    amountOfSemiColon++;
                }

            }
            if ((amountOfSemiColon > 1) ) {
                SimpleStatementViolation ssViolation = new SimpleStatementViolation();
                ssViolation.lineNumber = lineNumber;

                if (!report.simpleStatementViolations.contains(ssViolation.lineNumber)) {
                    report.simpleStatementViolations.add(ssViolation);
                }
                break;
            }
            previousChar = Character.toString(lineArray[i]);
        }
    }

    private boolean isForLoop(String line) {
        return line.matches(rule.basicForLoopRegex);
    }

    public class SimpleStatementViolation {
        public int lineNumber = 0;
        public final String statementViolation = "Each line should contain at most one statement \";\".";
    }
}
