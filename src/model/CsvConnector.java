package model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.csvreader.CsvReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.cbrcore.CaseBaseFilter;
import jcolibri.cbrcore.Connector;
import jcolibri.exception.InitializingException;
import model.PatientDescription;

/**
 *
 * @author ss6035
 */
public class CsvConnector implements Connector{
    
    private String filepath;
    private File file;

    public CsvConnector(String filepath) {
        this.filepath = filepath;
        this.file = new File(filepath);
    }
    
    public List<String[]> parse() throws FileNotFoundException {
        
        List<String[]> data = new ArrayList();
        CsvReader reader =  new CsvReader(new FileReader(file));
        try{
            reader.readHeaders();
            String[] headers = reader.getHeaders();
            data.add(headers);
            while(reader.readRecord()){
                String[] values = reader.getValues();
                data.add(values);
            }
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
        return data;      
    }
    
    public static void main(String[] args){        
        String filepath = "C:\\OneDrive\\PostDoc\\Nirmalie\\PatientData\\Cases.csv";       
        try{
            CsvConnector c = new CsvConnector(filepath);
            List<String[]> data = c.parse();
            String[] header = data.get(0);
            for(int i=0; i<header.length;i++){
                System.out.print(header[i]);
            }
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void initFromXMLfile(URL url) throws InitializingException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void storeCases(Collection<CBRCase> clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteCases(Collection<CBRCase> clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<CBRCase> retrieveAllCases() {
        Collection<CBRCase> cases = new ArrayList();        
        try{            
            List<String[]> data = this.parse();
            for(int i=1; i<data.size();i++){
//                System.out.println("i: "+i);
                String[] pRecord = data.get(i);
                CBRCase _case = new CBRCase();                
                PatientDescription patient =  new PatientDescription();
                _case.setDescription(patient);
                int j=0;
                patient.setId(pRecord[j]);                
                patient.setAge(Integer.parseInt(pRecord[++j]));
                
                //Get all BTS values
                double[] bts = new double[12];                
                for(int k=0; k<bts.length;k++){  
                    String val =  pRecord[++j];
                    if(val.isEmpty())
                        val = "0";
                    bts[k] = Integer.parseInt(val);
                }
                patient.setBts(bts);
                
                //Get all control values
                double[] control = new double[12];                
                for(int k=0; k<control.length;k++){   
                    String val =  pRecord[++j];
                    if(val.isEmpty())
                        val = "0";
                    control[k] = Integer.parseInt(val);
                }
                patient.setControl(control);
                
                //Get all Drug01 values
                int[][] drugs = new int[11][12];                
                for(int k=0; k<drugs.length;k++){
//                    System.out.println("k: "+k);                    
                    for(int l=0; l<drugs[0].length; l++){
//                        System.out.println("l: "+l);
                        String val =  pRecord[++j];
                        if(val.isEmpty())
                            val = "0";
//                        System.out.println(j);
                        drugs[k][l] = Integer.parseInt(val);
                    }                    
                }
                patient.setDrugs(drugs);
                
                cases.add(_case);
            }
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
        return cases;
    }
    
   
    public Collection<CBRQuery> retrieveAllQueries() {
        Collection<CBRQuery> queries = new ArrayList();        
        try{            
            List<String[]> data = this.parse();
            for(int i=1; i<data.size();i++){
//                System.out.println("i: "+i);
                String[] pRecord = data.get(i);
                CBRCase _case = new CBRCase();                
                PatientDescription patient =  new PatientDescription();
                _case.setDescription(patient);
                int j=0;
                patient.setId(pRecord[j]);                
                patient.setAge(Integer.parseInt(pRecord[++j]));
                
                //Get all BTS values
                double[] bts = new double[12];                
                for(int k=0; k<bts.length;k++){  
                    String val =  pRecord[++j];
                    if(val.isEmpty())
                        val = "0";
                    bts[k] = Integer.parseInt(val);
                }
                patient.setBts(bts);
                
                //Get all control values
                double[] control = new double[12];                
                for(int k=0; k<control.length;k++){   
                    String val =  pRecord[++j];
                    if(val.isEmpty())
                        val = "0";
                    control[k] = Integer.parseInt(val);
                }
                patient.setControl(control);
                
                //Get all Drug01 values
                int[][] drugs = new int[11][12];                
                for(int k=0; k<drugs.length;k++){
//                    System.out.println("k: "+k);                    
                    for(int l=0; l<drugs[0].length; l++){
//                        System.out.println("l: "+l);
                        String val =  pRecord[++j];
                        if(val.isEmpty())
                            val = "0";
//                        System.out.println(j);
                        drugs[k][l] = Integer.parseInt(val);
                    }                    
                }
                patient.setDrugs(drugs);
                
                queries.add(_case);
            }
            System.out.println();
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
        return queries;
    }

    @Override
    public Collection<CBRCase> retrieveSomeCases(CaseBaseFilter cbf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
