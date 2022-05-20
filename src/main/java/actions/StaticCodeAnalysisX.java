package actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;

public class StaticCodeAnalysisX extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Messages.showInfoMessage("Thank you for using SCAX. Your report is being written...", "SCAX - Static Code Analysis X");
        String projectFolder = e.getProject().getBasePath();
            System.out.println(projectFolder);
        for (int i = 0; i < 10; i++) {
        }
    }
}
