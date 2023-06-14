package io.github.thatmightyguy.xasm.token;

public enum XasmTokens {
    JMP,
    KILL,
    FORK,

    ADD, // block done
    SUB, 
    MUL, 
    DIV,
    INC, 
    DEC, 
    CEL, 
    FLR, 
    LEN, 

    MOV,    // MOVE REGISTER // block done
    
    MVI,    // MOVE ITEM
    MVF,    // MOVE FLUID
    MVG,    // MOVE GAS,
    GI,     // GET ALL ITEMS
    GF,
    GG,
    GCI,    // ITEM COUNT
    GCF,
    GCG,
    ODMI,   // OREDICT MOVE ITEM 
    ODMF,
    ODGI,   // OREDICT ITEM COUNT
    ODGF,
    GIL,    // GET ITEM LOCATION
    GFL,
    GGL,
    ODIL,   // OREDICT ITEM LOCATION
    ODFL,
    
    CMP, // block done
    JZ,
    JL,
    JLE,
    JEQ,
    JGE,
    JG,
    JNZ,

    OUT, // Display output - 1 stores data, 2 stores severity
    NOP
}
