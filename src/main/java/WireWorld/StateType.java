package WireWorld;

//possible cell states
public enum StateType {

    Empty, ElectronHead, ElectronTail, Conductor;

    public static boolean isStateType(String name) {

        StateType[] states = StateType.values();

        for (StateType s : states) {
            if (s.toString().toLowerCase().equals(name.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
