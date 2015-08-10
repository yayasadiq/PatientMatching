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
public class DTW implements LocalSimilarityFunction{

    @Override
    public double compute(Object o, Object o1) throws NoApplicableSimilarityFunctionException {
        if(o == null || o1 == null) return 0;
        if (!(o instanceof double[]))
            throw new jcolibri.exception.NoApplicableSimilarityFunctionException(this.getClass(), o1.getClass());
        if(!(o1 instanceof double[]))
            throw new jcolibri.exception.NoApplicableSimilarityFunctionException(this.getClass(), o1.getClass());
        double[] vals1 = (double[])o;
        double[] vals2 = (double[])o1;
        TimeSeries ts1 = new TimeSeries(vals1,false,false);
        TimeSeries ts2 = new TimeSeries(vals2,false,false);
        DistanceFunction distFn = DistanceFunctionFactory.getDistFnByName("EuclideanDistance");
        Double dist = FastDTW.getWarpDistBetween(ts1, ts2, distFn);
//        System.out.println("DTW Score: "+dist);
        double score = 1;
        if(dist>0)
            score = 1.0/dist;
        return score;
    }

    @Override
    public boolean isApplicable(Object o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
