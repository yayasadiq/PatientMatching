/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package extensions;

import jcolibri.exception.NoApplicableFilterPredicateException;
import jcolibri.method.retrieve.FilterBasedRetrieval.predicates.FilterPredicate;

/**
 *
 * @author ss6035
 */
public class ApproxEqual implements FilterPredicate{
    
     @Override
     public boolean compute(Object caseObject, Object queryObject) throws NoApplicableFilterPredicateException
    {
        if(caseObject instanceof Integer  && queryObject instanceof Integer){
            if(caseObject.equals(queryObject))
                return true;
            else if(Math.abs((Integer)caseObject-(Integer)queryObject)==1)
               return true;
            else
                return false;
        }
        return false;
    }
    
}
