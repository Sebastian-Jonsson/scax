package Rules;

import actions.FileReport;

public class PackageAndImportStatements {

    private boolean commentStarted;
    private boolean orderCorrect = false;
    public void confirmOrder(FileReport report, String line, int lineNumber) {
        line = line.trim();
        line.toLowerCase();

        if (!orderCorrect) {
            if (line.length() > 0) {
                if (!line.startsWith("//")) {
                    if (line.startsWith(("/*"))) {
                        commentStarted = true;

                        if (line.endsWith("*/")) {
                            commentStarted = false;
                        };
                    }
                    else if (line.endsWith("*/") && commentStarted) {
                        commentStarted = false;
                    }
                    else if (line.startsWith("*/") && commentStarted) {
                        commentStarted = false;
                    }
                    else if (!line.startsWith("package ") && !commentStarted) {
                        PackageAndImportViolation piViolation = new PackageAndImportViolation();
                        piViolation.lineNumber = lineNumber;
                        report.packageImportViolations.add(piViolation);
                        orderCorrect = true;
                    }
                }
                if (line.startsWith("package")) {
                    orderCorrect = true;
                }
                else if (!line.startsWith("import") && orderCorrect) {
                    PackageAndImportViolation piViolation = new PackageAndImportViolation();
                    piViolation.lineNumber = lineNumber;
                    report.packageImportViolations.add(piViolation);
                }
            }
        }

    }

    private void isComment(String line) {
    }

    public class PackageAndImportViolation {
        public int lineNumber = 0;
        public String packageImportViolation = "Package and Import Statements (first non-comment should be a Package, followed by import(s))";
    }
}
