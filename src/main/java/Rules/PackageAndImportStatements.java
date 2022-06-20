package Rules;

import actions.FileReport;

import java.util.ArrayList;

public class PackageAndImportStatements {

    private boolean commentStarted = false;
    private boolean violationDetected = false;
    String pkgString = "PACKAGE";
    String impString = "IMPORT;";
    String codeString = "CODE";
    String previousString = "";

    public void orderVerifier(ArrayList<String> occurrences, FileReport report) {
        ArrayList<String> orderOfOperation = new ArrayList<>();

        for (String line : occurrences) {
            line = line.trim();
            line.toLowerCase();

            if (line.length() != 0) {
                if (commentStarted) {
                }
                if (line.startsWith(("/*"))) {
                    commentStarted = true;

                    if (line.endsWith("*/")) {
                        commentStarted = false;
                    }
                }
                else if (line.endsWith("*/") && commentStarted) {
                    commentStarted = false;
                }
                else if (line.startsWith("*/") && commentStarted) {
                    commentStarted = false;
                }
                else if (line.startsWith("package ")) {
                    orderOfOperation.add(pkgString);
                }
                else if (line.startsWith("import ")) {
                    orderOfOperation.add(impString);
                }
                else if (!commentStarted && !line.startsWith("//")) {
                    orderOfOperation.add(codeString);
                }
            }
        }

        verifier(orderOfOperation, report);
    }

    private void verifier(ArrayList<String> orderOfOperation, FileReport report) {

        if (!orderOfOperation.get(0).equals(pkgString)) {
            report.packageImportViolations.add(new PackageAndImportViolation());
            System.out.println("nej1");
            violationDetected = true;
        }

        if (!violationDetected) {
            for (String lineType : orderOfOperation) {

                if (previousString.equals(codeString)) {
                    if (lineType.equals(impString)) {
                        System.out.println("nej1");
                        report.packageImportViolations.add(new PackageAndImportViolation());
                    }
                }
                previousString = lineType;
            }
        }
    }

    public class PackageAndImportViolation {
        public String packageImportViolation = "Package and Import Statements (first non-comment should be a Package, followed by import(s))";
    }
}
