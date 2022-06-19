package Rules;

import actions.FileReport;

import java.util.ArrayList;

public class PackageAndImportStatements {

    private boolean commentStarted = false;
    private boolean packageFirst = false;
    private boolean codeStarted = false;
    private boolean importFound = false;
    private boolean violationDetected = false;

    public void orderVerifier(ArrayList<String> occurrences, FileReport report) {
        ArrayList<String> orderOfOperation = new ArrayList<>();
        String cmtString = "COMMENT";
        String pkgString = "PACKAGE";
        String impString = "IMPORT;";
        String codeString = "CODE";

        for (String line : occurrences) {
            line = line.trim();
            line.toLowerCase();

            if (line.length() != 0) {
                if (commentStarted) {
                    orderOfOperation.add(cmtString);
                }
                else if (line.startsWith("//")) {
                    orderOfOperation.add(cmtString);
                }
                if (line.startsWith(("/*"))) {
                    orderOfOperation.add(cmtString);
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
                else if (line.startsWith("package ")) {
                    orderOfOperation.add(pkgString);
                }
                else if (line.startsWith("import ")) {
                    orderOfOperation.add(impString);
                }
                else {
                    orderOfOperation.add(codeString);
                }
            }
        }

        verifier(orderOfOperation, report);
    }

    private void verifier(ArrayList<String> orderOfOperation, FileReport report) {
        // TODO: go through arraylist and check order of the types of lines. Package, Import, Comment, Code. If package is after import = violation.
        // TODO: Bubble sort principle to find the correct order.
    }

    public void confirmOrder(FileReport report, String line, int lineNumber) {
        line = line.trim();
        line.toLowerCase();
        PackageAndImportViolation piViolation = new PackageAndImportViolation();

        System.out.println(line);

        if (!violationDetected) {
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
                    else if (!commentStarted) {


                        if (packageFirst) {
                            if (!line.startsWith("import ")) {
                                if (!codeStarted) {
                                    codeStarted = true;
                                    System.out.println("Code started");
                                    piViolation.lineNumber = lineNumber;
                                    violationDetected = true;
                                    report.packageImportViolations.add(piViolation);
                                }
                            }
                        }
                        if (line.startsWith("package ")) {
                            System.out.println("package e först");
                            packageFirst = true;
                        }
                    }
                }
            }
        }


    }

    public class PackageAndImportViolation {
        public int lineNumber = 0;
        public String packageImportViolation = "Package and Import Statements (first non-comment should be a Package, followed by import(s))";
    }
}
