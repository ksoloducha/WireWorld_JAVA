package WireWorld;

import java.util.Arrays;



public enum  StructureType {

    Diode, AND, OR, XOR, ClockGen1, ClockGen2, ClockGen3, LongConductor, NAND;

    public static boolean isStructureType(String name) {

        StructureType[] structures = StructureType.values();

        for (StructureType s : structures) {
            if (name.equalsIgnoreCase(s.toString())) {
                return true;
            }
        }
        return false;
    }

    public static int[] setDiodeInCellSet (int x, int y) {

        int[] tab = new int[] {x-1, y, 3,
                               x, y-1, 3,
                               x, y, 3,
                               x, y+1, 3,
                               x+1, y-1, 3,
                               x+1, y+1, 3,
                               x+2, y, 3
                              };
        return tab;
    }

    private static int[] setANDInCellSet (int x, int y) {
        int[] tab = new int[] {x-8, y-3, 3,
                               x-8, y, 3,
                               x-7, y-3, 3,
                               x-7, y, 3,
                               x-6, y-3, 3,
                               x-6, y+1, 3,
                               x-6, y+2, 3,
                               x-6, y+3, 3,
                               x-5, y-3, 3,
                               x-5, y+4, 3,
                               x-4, y-3, 3,
                               x-4, y+1, 3,
                               x-4, y+2, 3,
                               x-4, y+3, 3,
                               x-3, y-3, 3,
                               x-3, y, 3,
                               x-2, y-3, 3,
                               x-2, y-1, 3,
                               x-2, y, 3,
                               x-2, y+1, 3,
                               x-1, y-3, 3,
                               x-1, y, 3,
                               x, y-3, 3,
                               x, y-1, 3,
                               x, y+1, 3,
                               x+1, y-2, 3,
                               x+1, y+2, 3,
                               x+2, y-2, 3,
                               x+2, y+1, 3,
                               x+2, y+2, 3,
                               x+2, y+3, 3,
                               x+3, y-2, 3,
                               x+3, y+2, 3,
                               x+4, y-1, 3,
                               x+4, y, 3,
                               x+4, y+1, 3,
                               x+4, y+3, 3,
                               x+5, y+3, 3,
                               x+6, y+2, 3,
                               x+7, y+1, 3,
                               x+8, y+1, 3
                              };
        return tab;
    }

    private static int[] setORInCellSet (int x, int y) {
        int[] tab = new int[] {x-3, y-1, 3,
                               x-3, y+1, 3,
                               x-2, y-1, 3,
                               x-2, y+1, 3,
                               x-1, y-2, 3,
                               x-1, y+2, 3,
                               x, y-2, 3,
                               x, y, 3,
                               x, y+2, 3,
                               x+1, y-1, 3,
                               x+1, y, 3,
                               x+1, y+1, 3,
                               x+2, y, 3
                              };
        return tab;
    }

    public static int[] setXORInCellSet (int x, int y) {
        int[] tab = new int[] {x-4, y-1, 3,
                               x-4, y+1, 3,
                               x-3, y-1, 3,
                               x-3, y+1, 3,
                               x-2, y-2, 3,
                               x-2, y+2, 3,
                               x-1, y+3, 3,
                               x-1, y-3, 3,
                               x, y-3, 3,
                               x, y-1, 3,
                               x, y, 3,
                               x, y+1, 3,
                               x, y+3, 3,
                               x+1, y-2, 3,
                               x+1, y-1, 3,
                               x+1, y+1, 3,
                               x+1, y+2, 3,
                               x+2, y-1, 3,
                               x+2, y+1, 3,
                               x+3, y-1, 3,
                               x+3, y, 3,
                               x+3, y+1, 3,
                               x+4, y, 3
                              };
        return tab;
    }

    private static int[] setClockGen1InCellSet (int x, int y) {
        int[] tab = new int[] {x-1, y, 2,
                               x, y-1, 1,
                               x, y+1, 3,
                               x+1, y, 3
                              };
        return tab;
    }
    private static int[] setClockGen2InCellSet (int x, int y) {
        int[] tab = new int[] {x-2, y, 2,
                               x-1, y-1, 1,
                               x-1, y+1, 3,
                               x, y-1, 3,
                               x, y+1, 3,
                               x+1, y-1, 3,
                               x+1, y+1, 3,
                               x+2, y, 3
                              };
        return tab;
    }

    private static int[] setClockGen3InCellSet (int x, int y) {
        int[] tab = new int[] {x-3, y, 2,
                               x-2, y-1, 1,
                               x-2, y+1, 3,
                               x-1, y-1, 3,
                               x-1, y+1, 3,
                               x, y-1, 3,
                               x, y+1, 3,
                               x+1, y-1, 3,
                               x+1, y+1, 3,
                               x+2, y-1, 3,
                               x+2, y+1, 3,
                               x+3, y, 3
                              };
        return tab;
    }

    private static int[] setLongConductorInCellSet (int x, int y){
        int[] tab = new int[] {x-2, y, 3,
                               x-1, y, 3,
                               x, y, 3,
                               x+1, y, 3,
                               x+2, y, 3
                              };
        return tab;
    }
    
    private static int[] setNANDInCellSet (int x, int y){
        int[] tab = new int[] {x-6, y-1, 3,
                               x-6, y+1, 3,
                               x-5, y-1, 3,
                               x-5, y+1, 3,
                               x-4, y-2, 3,
                               x-4, y+2, 3,
                               x-3, y-3, 3,
                               x-3, y, 3,
                               x-3, y+3, 3,
                               x-2, y-4, 3,
                               x-2, y-1, 3,
                               x-2, y+1, 3,
                               x-2, y+4, 3,
                               x-1, y-5, 3,
                               x-1, y-1, 3,
                               x-1, y+1, 2,
                               x-1, y+5, 3,
                               x, y-6, 3,
                               x, y, 1,
                               x, y+6, 3,
                               x+1, y-6, 3,
                               x+1, y-1, 3,
                               x+1, y+1, 3,
                               x+1, y+6, 3,
                               x+2, y-6, 3,
                               x+2, y-4, 3,
                               x+2, y-2, 3,
                               x+2, y+2, 3,
                               x+2, y+4, 3,
                               x+2, y+6, 3,
                               x+3, y-5, 3,
                               x+3, y-4, 3,
                               x+3, y-3, 3,
                               x+3, y, 3,
                               x+3, y+3, 3,
                               x+3, y+4, 3,
                               x+3, y+5, 3,
                               x+4, y-4, 3,
                               x+4, y-2, 3,
                               x+4, y-1, 3,
                               x+4, y, 3,
                               x+4, y+1, 3,
                               x+4, y+2, 3,
                               x+4, y+4, 3,
                               x+5, y, 3,
                               x+6, y, 3
                              };
        return tab;
    }

    public static int[] getSetupStructureInCellSet (int x, int y, int z, String structure) {

        StructureType[] structures = StructureType.values();
        int[] tab = null;


        if (structure.equalsIgnoreCase(structures[0].toString())){
            tab = setDiodeInCellSet(x, y);
        }
        if (structure.equalsIgnoreCase(structures[1].toString())){
            tab = setANDInCellSet(x, y);
        }
        if (structure.equalsIgnoreCase(structures[2].toString())){
            tab = setORInCellSet(x, y);
        }
        if (structure.equalsIgnoreCase(structures[3].toString())){
            tab = setXORInCellSet(x, y);
        }
        if (structure.equalsIgnoreCase(structures[4].toString())){
            tab = setClockGen1InCellSet(x, y);
        }
        if (structure.equalsIgnoreCase(structures[5].toString())){
            tab = setClockGen2InCellSet(x, y);
        }
        if (structure.equalsIgnoreCase(structures[6].toString())){
            tab = setClockGen3InCellSet(x, y);
        }
        if (structure.equalsIgnoreCase(structures[7].toString())){
            tab = setLongConductorInCellSet(x, y);
        }
        if (structure.equalsIgnoreCase(structures[8].toString())){
            tab = setNANDInCellSet(x, y);
        }
        tab = setDirection(tab, x, y, z);
        return tab;
    }

    private static int[] setDirection(int[] tab, int x, int y, int z){
        if (z == 0){
            return tab;
        }
        if (z == 1){
            return setDirectionUp(tab, x, y);
        }
        if (z == 2){
            return setDirectionHorizontal(tab, x);
        }
        if (z == 3){
            return setDirectionVertical(setDirectionUp(tab, x, y), y);
        }
        return null;
    }

    private static int[] setDirectionHorizontal(int[] tab, int x){
        int tmp[] = Arrays.copyOf(tab, tab.length);
        for(int i = 0; i < tab.length; i=i+3){
            int k = tab[i] - x;
            tmp[i] = x - k;
        }
        return tmp;
    }

    private static int[] setDirectionVertical(int[] tab, int y){
        int tmp[] = Arrays.copyOf(tab, tab.length);
        for(int i = 0; i < tab.length; i=i+3){
            int k = tab[i+1] - y;
            tmp[i+1] = y - k;
        }
        return tmp;
    }

    private static int[] setDirectionUp(int[] tab, int x, int y){
        int tmp[] = Arrays.copyOf(tab, tab.length);
        for(int i = 0; i < tab.length; i=i+3){
            int xd = tab[i] - x;
            tmp[i+1] = y - xd;
            int yd = tab[i+1] - y;
            tmp[i] = x - yd;
        }
        return tmp;
    }
}
