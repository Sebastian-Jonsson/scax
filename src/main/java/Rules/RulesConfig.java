package Rules;

public class RulesConfig {
    // Do not support other file types.
    public final String languageSupport = ".java";

    // Length Rules.
    public final int MAX_LINE_LENGTH = 80;
    public final int MAX_FILE_LENGTH = 2000;

    // Declaration Rules.
    public final String validMethodRegex = "(public|protected|private|static|\\s) +[\\w\\<\\>\\[\\]]+\\s+(\\w+)\\([^\\)]*\\) *(\\{?|[^;]) (\\{)(\\})*";
    public final String invalidLineMethodRegex = "(public|protected|private|static|\\s) +[\\w\\<\\>\\[\\]]+\\s+(\\w+) *\\([^\\)]*\\)";
    public final String invalidSpaceParenthesisMethodRegex = "(public|protected|private|static|\\s) +[\\w\\<\\>\\[\\]]+\\s+(\\w+) \\([^\\)]*\\) *(\\{?|[^;]) *(\\{)*(\\})*";
    public final String invalidNullBraceMethodRegex = "(public|protected|private|static|\\s) +[\\w\\<\\>\\[\\]]+\\s+(\\w+) *\\([^\\)]*\\) *(\\{?|[^;]) (\\{) +(\\})";
    public final String classOrInterfaceRegex = "(public|protected|private|static|\\s) +(class|interface|\\s) [\\w\\<\\>\\[\\]]+\\ *(\\{?|[^;]) (\\{)*";
    public final String classRegex = "(public|protected|private|static|\\s) +(class|\\s) [\\w\\<\\>\\[\\]]+\\ *(\\{?|[^;]) (\\{)*";

    // Simple Statement Rules.
    public final String basicForLoopRegex = ".*(for\\s*\\([^;]*?;[^;]*?;[^)]*?\\)]?).*";
}
