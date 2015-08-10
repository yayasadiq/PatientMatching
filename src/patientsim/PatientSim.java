/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package patientsim;

import extensions.DTW;
import extensions.Cosine;
import extensions.Jaccard;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jcolibri.casebase.LinealCaseBase;
import jcolibri.cbraplications.StandardCBRApplication;
import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRCaseBase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.cbrcore.Connector;
import jcolibri.exception.ExecutionException;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import jcolibri.method.retrieve.RetrievalResult;
import jcolibri.method.retrieve.selection.SelectCases;
import model.CsvConnector;
import model.PatientDescription;

/**
 *
 * @author ss6035
 */
public class PatientSim implements StandardCBRApplication{
    
    private Connector connector;
    private CBRCaseBase casebase;
    private NNConfig simConfig;
    private String cbPath;   
    private BufferedWriter out;
    private String outPath;

    public PatientSim(String cbPath, String outPath) {
        this.cbPath = cbPath; 
        this.outPath = outPath;
    }    

    @Override
    public void configure() throws ExecutionException{
        connector  = new CsvConnector(cbPath);
        casebase = new LinealCaseBase();
        simConfig = new NNConfig();
        simConfig.setDescriptionSimFunction(new Average());
        simConfig.addMapping(new Attribute("age", PatientDescription.class), new Equal());
        simConfig.addMapping(new Attribute("bts", PatientDescription.class), new DTW());
        simConfig.addMapping(new Attribute("control", PatientDescription.class), new DTW());
        simConfig.addMapping(new Attribute("drugVec", PatientDescription.class), new Jaccard());
        
        try{
            out = new BufferedWriter(new FileWriter(new File(outPath)));
        }catch(IOException e){
            throw new ExecutionException(e);
        }
    }

    @Override
    public CBRCaseBase preCycle() throws ExecutionException {
        casebase.init(connector); 
        Collection<CBRCase> cases = casebase.getCases();
        for (CBRCase _c:cases){
            PatientDescription patient = (PatientDescription)_c.getDescription();
//            System.out.println(patient.getId());
        }
        return casebase;
    }

    @Override
    public void cycle(CBRQuery cbrq) throws ExecutionException {
        Collection<RetrievalResult> result = NNScoringMethod.evaluateSimilarity(casebase.getCases(), cbrq, simConfig);
        Collection<RetrievalResult> retievedCases = SelectCases.selectTopKRR(result, 1);
        DecimalFormat df = new DecimalFormat("0.00");
        for(RetrievalResult rr:retievedCases){
            String output = cbrq.getID()+", "+rr.get_case().getID()+", "+df.format(rr.getEval());
            try {
                out.write(output);
                out.newLine();
            } catch (IOException e) {
                throw new ExecutionException(e);
            }
            System.out.println(output);
        }
    }

    @Override
    public void postCycle() throws ExecutionException {
        try{
            if(out != null){                
                out.close();
            }
        }catch(IOException e){
            throw new ExecutionException(e);
        }
    }
    
    
    
}
