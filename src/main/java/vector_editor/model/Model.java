package vector_editor.model;

import vector_editor.model.Shapes.ShapeObject;

import java.util.ArrayList;

public class Model {


    public Workspace getWorkspace() {
        return workspace;
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }
    private Workspace workspace;
    private ArrayList<Workspace> workspaceHistory;
    public void saveCurrentWorkspaceToHistory() {

        for (ShapeObject shape : workspace.getShapes()) {
            shape.setSelected(false);
        }
        workspaceHistory.add(workspace);

    }

    public Model() {
        workspaceHistory = new ArrayList<>();
    }

    public int getPreviousWorkspaceStateId() {
        return workspaceHistory.size() - 1;
    }

    public void setWorkspaceToPreviousState() {
        try {
            workspace = workspaceHistory.get(getPreviousWorkspaceStateId());
            workspaceHistory.remove(getPreviousWorkspaceStateId());

        } catch (Exception e) {
        }
        if (getPreviousWorkspaceStateId() < 0) {
            workspaceHistory.clear();
        }
    }

    public void cleanWorkspaceHistory() {
        this.workspaceHistory.clear();
    }

}
