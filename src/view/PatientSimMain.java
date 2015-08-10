/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.Collection;
import jcolibri.cbrcore.CBRCase;
import jcolibri.exception.ExecutionException;   
import model.CsvConnector;
import patientsim.PatientSim;

/**
 *
 * @author ss6035
 */
public class PatientSimMain {
    
    public static void main(String[] args){
//        String cbPath = "C:\\OneDrive\\PostDoc\\Nirmalie\\PatientData\\Controls.csv"; 
//        String qPath = "C:\\OneDrive\\PostDoc\\Nirmalie\\PatientData\\Cases.csv";
        if(args.length<3){
            System.out.println("USAGE:  java -jar PatientPairs \tcontrols_file \tcases_file \toutput_file");
            System.exit(1);
        }else{
            String cbPath = args[0];
            String qPath = args[1];
            String outPath = args[2];
            PatientSim app = new PatientSim(cbPath, outPath);
            try{
                app.configure();
                app.preCycle();

                CsvConnector conn = new CsvConnector(qPath);
                Collection<CBRCase> qCases = conn.retrieveAllCases();

                for(CBRCase c:qCases){
                    app.cycle(c);
                }

                app.postCycle();

            }catch(ExecutionException e){
                System.err.println(e.getMessage());
            }
        }
    }
    
}
