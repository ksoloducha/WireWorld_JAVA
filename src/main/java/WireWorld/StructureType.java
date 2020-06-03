package WireWorld;


public enum  StructureType {
    
    Diode, OR, XOR;
    
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
    
    public static int[] setXORInCellSet (int x, int y) {

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

    public static int[] setORInCellSet (int x, int y) {

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
    
    public static int[] setStructureInCellSet (int x, int y, int z, String structure) {
        
        StructureType[] structures = StructureType.values();
        int[] tab = null;
        

        if (structure.equalsIgnoreCase(structures[0].toString())){
            tab = setDiodeInCellSet(x, y);
        }
        if (structure.equalsIgnoreCase(structures[1].toString())){
            tab = setORInCellSet(x, y);
        } 
        if (structure.equalsIgnoreCase(structures[2].toString())){
            tab = setXORInCellSet(x, y);

        }
        return tab; 
    }
}
