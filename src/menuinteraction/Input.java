package menuinteraction;

public class Input {
    Command theCommand;

    public Input(Command newCommand) { theCommand = newCommand; }
    public void entered() {
        theCommand.execute();
    }
    public void undoEnter() {
        theCommand.unexecute();
    }
}
