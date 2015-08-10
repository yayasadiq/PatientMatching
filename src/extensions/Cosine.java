/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package extensions;

import com.dtw.FastDTW;
import com.timeseries.TimeSeries;
import com.util.DistanceFunction;
import com.util.DistanceFunctionFactory;
import jcolibri.exception.NoApplicableSimilarityFunctionException;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;

/**
 *
 * @author ss6035
 */
public class Cosine implements LocalSimilarityFunction{

    @Override
    public double compute(Object o, Object o1) throws NoApplicableSimilarityFunctionException {
        if(o == null || o1 == null) return 0;
        if (!(o instanceof double[]))
            throw new jcolibri.exception.NoApplicableSimilarityFunctionException(this.getClass(), o1.getClass());
        if(!(o1 instanceof double[]))
            throw new jcolibri.exception.NoApplicableSimilarityFunctionException(this.getClass(), o1.getClass());
        double[] vals1 = (double[])o;
        double[] vals2 = (double[])o1;
        
        double num = 0;
        double a_mag = 0;
        double b_mag = 0;
        for(int i=0; i<vals1.length; i++){
            num += vals1[i] * vals2[i];
            a_mag += Math.pow(vals1[i], 2);
            b_mag += Math.pow(vals2[i], 2);
        }
        a_mag = Math.sqrt(a_mag);
        b_mag = Math.sqrt(b_mag);
        double denom = a_mag * b_mag;
        double score = 0;
        if(denom>0)
            score = num/denom;
//        if (score>1)
//            System.out.println("Cosine: "+score);
        return score;
    }

    @Override
    public boolean isApplicable(Object o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
