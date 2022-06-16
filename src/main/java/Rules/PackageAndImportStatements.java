package Rules;

import actions.FileReport;

public class PackageAndImportStatements {

    private boolean commentStarted;
    private boolean orderCorrect = false;
    public void confirmOrder(FileReport report, String line, int lineNumber) {
        line = line.trim();

        if (!orderCorrect) {
            if (line.length() > 0) {
                if (line.startsWith("//")) {

                }
                else if (line.startsWith(("/*"))) {
                    commentStarted = true;

                    if (line.endsWith("*/")) {
                        commentStarted = false;
                    };
                }
                else if (line.startsWith("/*")) {
                    commentStarted = true;
                }
                if (line.endsWith("*/") && commentStarted) {
                    commentStarted = false;
                }

                if (line.startsWith("*/") && commentStarted) {
                    commentStarted = false;
                }
            }
            else if (!line.startsWith("package ")) { // TODO: Order of operation is wrong.
                PackageAndImportViolation piViolation = new PackageAndImportViolation();
                piViolation.lineNumber = lineNumber;
                report.packageImportViolations.add(piViolation);
                orderCorrect = true;
            }
        }

    }

    private void isComment(String line) {
    }

    public class PackageAndImportViolation {
        public int lineNumber = 0;
        public String packageImportViolation = "";
    }
}
