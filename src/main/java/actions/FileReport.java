package actions;

import java.util.ArrayList;
import java.util.List;

import Rules.LineLength;
import Rules.MethodChecker;
import Rules.SimpleStatements;

public class FileReport {
    // Metadata
    public String filePath = "";
    public int totalLines = 0;
    public int linesOfCode = 0;
    public int blankLines = 0;
    public int amountOfComments = 0;
    public int linesOfComments = 0;
    public int amountOfViolations = 0;
    public double medianClassSize = 0;

    // Rules Implementations
    public List<LineLength.LineLengthViolation> lineLengthViolations;
    public List<MethodChecker.MethodDeclarationViolation> methodDeclarationViolations;
    public List<SimpleStatements.SimpleStatementViolation> simpleStatementViolations;
    public List<Integer> classSizeList;


}
