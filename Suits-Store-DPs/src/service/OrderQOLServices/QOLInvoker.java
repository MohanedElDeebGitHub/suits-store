package service.OrderQOLServices;

public class QOLInvoker {
    private Command command;

    public void setCommand(Command command){
        this.command = command;
    }

    public void execute(){
        command.execute();
    }
}
