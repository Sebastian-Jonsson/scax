package actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;
import actions.PathReader;

import java.io.IOException;


public class StaticCodeAnalysisX extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        String projectFolder = e.getProject().getBasePath();

        try {
            PathReader PR = new PathReader();
            PR.sourcePathReader(projectFolder);
            System.out.println(projectFolder);
            Messages.showInfoMessage("Thank you for using SCAX. Your report is being written to the projects source folder.", "SCAX - Static Code Analysis X");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
