package actions;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import Rules.RulesConfig;
import Rules.RulesOrganizer;
import Rules.LineLength.LineLengthViolation;
import Rules.MethodChecker.MethodDeclarationViolation;

public class PathReader {
    RulesConfig rule = new RulesConfig();
    List<FileReport> reportList = new ArrayList<FileReport>();

    public void sourcePathReader(String projectFolder) throws IOException {
        File sourceFolder = new File(projectFolder);

        processFiles(sourceFolder, file -> System.out.println(file.getAbsolutePath()));
        tempFileReport(projectFolder);
    }

    private void processFiles(File dir, Consumer<File> fileConsumer) throws IOException {
        if (dir.isDirectory()) {
            for (File file1 : dir.listFiles()) {
                processFiles(file1, fileConsumer);
            }
        }
        else {
            if (dir.toString().endsWith(rule.languageSupport)) {
                readFile(dir);
            }
        }
    }

    private void readFile(File inputFile) throws IOException {
        RulesOrganizer Rules = new RulesOrganizer();
        FileInputStream fileInputStream = new FileInputStream(inputFile);
        InputStreamReader inStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader buffReader = new BufferedReader(inStreamReader);

        FileReport report = new FileReport();
        String line = "";
        int lineNumber = 0;

        report.filePath = inputFile.getAbsolutePath();
        line = buffReader.readLine();

        while (line != null) {
            lineNumber++;
            Rules.rulesChecker(report, line, lineNumber);
            report.totalLines++;
            line = buffReader.readLine();
        }
        buffReader.close();
        reportList.add(report);
    }

    /**
     * TODO: Move and refactor.
     */
    private void tempFileReport(String projectFolder) {
        StringBuilder fileReport = new StringBuilder();
        for (FileReport report : reportList) {
            fileReport.append(
                "\n\nMetaData:\nFile: " + report.filePath
                + "\nTotal Lines: " + report.totalLines + " | Max File Length Violation: "
                + (report.totalLines > rule.MAX_FILE_LENGTH)
                + "\nLines of Code: " + report.linesOfCode
                + " Blank Lines: " + report.blankLines
                + " Lines of Comments: " + report.linesOfComments
                + " Amount of Comments: " + report.amountOfComments
                + "\n\nRules: \nTotal Line Length Violations: " + report.lineLengthViolations.size());

            for (LineLengthViolation lineLength : report.lineLengthViolations) {
                fileReport.append(
                    "\nLine Length Violation at line: " + lineLength.lineNumber
                    + " - Actual Length: " + lineLength.actualLength);
            }

            fileReport.append("\n\n\nMethod Declaration Violations below: " + report.methodDeclarationViolations.size());
            for (MethodDeclarationViolation declarationViolation : report.methodDeclarationViolations) {
                fileReport.append(
                    "\n\nParent Class or Interface Length: " + declarationViolation.parentLength
                    + "\nLine: " + declarationViolation.lineNumber + " | Method: "
                    + declarationViolation.methodName
                    + " | Method Length: " + declarationViolation.methodLength
                    + "\nDescription: " + declarationViolation.declarationViolation);
            }
        }
        // TODO: Add print to Markdown format function and refactor.
        printReport(fileReport.toString(), projectFolder);
        System.out.println(fileReport.toString());
    }

    private void printReport(String fileReport, String projectFolder) {
        File myObj = new File(projectFolder + "/SCAX_Report.txt");
        try {
            if (myObj.createNewFile()) {
                FileWriter writeToFile = new FileWriter(projectFolder + "/SCAX_Report.txt");
                writeToFile.write(fileReport);
                writeToFile.close();
            }
            else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
