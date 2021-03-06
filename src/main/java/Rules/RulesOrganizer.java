package Rules;

import actions.FileReport;

public class RulesOrganizer {
    private final TypeOfLine ToL = new TypeOfLine();
    private final LineLength LL = new LineLength();
    // private final MethodChecker MC = new MethodChecker();
    private final SimpleStatements SS = new SimpleStatements();


    public void rulesChecker(FileReport report, String line, int lineNumber) {
        ToL.lineTypeCount(report, line);
        LL.maxLineLength(report, line, lineNumber);
        // MC.methodDeclarationCheck(report, line, lineNumber);
        SS.checkSimpleStatement(report, line, lineNumber);

    }

}

