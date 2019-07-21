package vn.edu.tdc.mymanager.model;

public class Task {

    private String contentTask;
    private Boolean actionCheck;

    public Task(String contentTask) {

        this.contentTask = contentTask;
        actionCheck = false;
    }


    public String getContentTask() {
        return contentTask;
    }

    public void setContentTask(String contentTask) {
        this.contentTask = contentTask;
    }

    public Boolean getActionCheck() {
        return actionCheck;
    }

    public void setActionCheck(Boolean actionCheck) {
        this.actionCheck = actionCheck;
    }
}
