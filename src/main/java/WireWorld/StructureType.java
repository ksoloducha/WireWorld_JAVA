package WireWorld;


public enum  StructureType {
    
    Diode, OR, XOR;
    
    public static boolean isStructureType(String name) {

        StructureType[] structures = StructureType.values();

        for (StructureType s : structures) {
            if (s.toString().toLowerCase().equals(name.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    
    public static int[] SetDiodeInCellSet (int x, int y) {
        int[] tab = new int[] {x-2, y, 3,
                               x-1, y-1, 1,
                               x-1, y+1, 3,
                               x, y-1, 2,
                               x, y+1, 3,
                               x+1, y-1, 3,
                               x+1, y+1, 3,
                               x+2, y, 3,     
                              };
        return tab;  
    }
    
    public static int[] SetXORInCellSet (int x, int y) {
        int[] tab = new int[] {x-1, y-1, 3,
                               x-1, y, 3,
                               x-1, y+1, 3,
                               x, y-2, 3,
                               x, y-1, 3,
                               x, y+1, 3,
                               x, y+2, 3,
                               x+1, y-1, 3,
                               x+1, y+1, 3,
                               x+2, y-1, 3,
                               x+2, y, 3,
                               x+2, y+1, 3,                               
                               x+3, y, 3,
                              };
        return tab;  
    }
    public static int[] SetORInCellSet (int x, int y) {
        int[] tab = new int[] {x-1, y-1, 3,
                               x-1, y, 3,
                               x-1, y+1, 3,
                               x, y-2, 3,
                               x, y-1, 3,
                               x, y+1, 3,
                               x, y+2, 3,
                               x+1, y-1, 3,
                               x+1, y+1, 3,
                               x+2, y-1, 3,
                               x+2, y+1, 3,                               
                               x+3, y, 3,
                              };
        return tab;  
    }
    
    public static int[] SetStructureInCellSet (int x, int y, int z, String structure) {
        
        StructureType[] structures = StructureType.values();
        int[] tab = null;
        
        if (structure.toLowerCase().equals(structures[0].toString().toLowerCase())){
            tab = SetDiodeInCellSet(x, y);
        }
        if (structure.toLowerCase().equals(structures[1].toString().toLowerCase())){
            tab = SetORInCellSet(x, y);
        } 
        if (structure.toLowerCase().equals(structures[2].toString().toLowerCase())){
            tab = SetXORInCellSet(x, y);
        }
        return tab; 
    }
}
