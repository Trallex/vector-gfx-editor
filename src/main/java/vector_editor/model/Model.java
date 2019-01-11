package vector_editor.model;

import java.util.ArrayList;

public class Model {


    public Workspace getWorkspace() {
        return workspace;
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
        System.out.println(workspace.toString());
    }


    private Workspace workspace;
    private ArrayList<Workspace> workspaceHistory;
    private int previousWorkspaceStateId;

    public void saveCurrentWorkspaceToHistory() {
        workspaceHistory.add(workspace);
        previousWorkspaceStateId++;
    }

    public Model() {
        workspaceHistory = new ArrayList<>();
        previousWorkspaceStateId = -1;
    }

    public void setWorkspaceToPreviousState() {
        try {
            workspace = workspaceHistory.get(previousWorkspaceStateId--);
        } catch (Exception e) {
        }
        if (previousWorkspaceStateId < 0) {
            workspaceHistory.clear();
            //saveCurrentWorkspaceToHistory()

        }
    }


}
