package WireWorld;

//possible cell states
public enum StateType {

    Empty, ElectronHead, ElectronTail, Conductor;

    public static boolean isStateType(String name) {

        StateType[] states = StateType.values();

        for (StateType s : states) {
            
            if (name.equalsIgnoreCase(s.toString())) {
                return true;
            }
        }
        return false;
    }

    public static StateType enumValueOf (String name) {
        StateType[] states = StateType.values();

        for (StateType s : states) {
            
            if (name.equalsIgnoreCase(s.toString())) {
                return s;
            }
        }
        return null;
    }
    
    public static String getStringState(int n) {
      
        StateType[] states = StateType.values();
        return states[n].toString();
    }
}
