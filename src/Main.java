import fileManager.FileManager;
import lexicalAnalyzer.LexicalAnalyzer;
import lexicalAnalyzer.LexicalException;
import lexicalAnalyzer.Token;
import symbolTable.SemanticException;
import symbolTable.SymbolTable;
import syntacticAnalyzer.SyntacticAnalyzer;
import syntacticAnalyzer.SyntacticException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            String file_path = new File(args[0]).getAbsolutePath();

            try {
                FileManager fileManager = new FileManager(file_path);
                LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(fileManager);
                SymbolTable.getSymbolTableInstance();
                SyntacticAnalyzer syntacticAnalyzer = new SyntacticAnalyzer(lexicalAnalyzer);

                SymbolTable.getSymbolTableInstance().checkStatements();
                SymbolTable.getSymbolTableInstance().consolidate();

                SymbolTable.getSymbolTableInstance().checkSentences();

                System.out.println("Compilacion Exitosa\n \n[SinErrores]");

            } catch (FileNotFoundException e) {
                System.out.println("No se encontró el archivo en la ruta indicada.");
            } catch (IOException | SemanticException | SyntacticException | LexicalException e){
                System.out.println(e.getMessage());
            }finally {
                SymbolTable.resetSymbolTable();
            }
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("No se indicó la ruta del archivo que contiene el código fuente.");
        }

    }
}

