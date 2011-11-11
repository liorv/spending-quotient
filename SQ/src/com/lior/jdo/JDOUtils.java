package com.lior.jdo;

import java.util.Collection;
import java.util.Set;

import com.lior.sq.SQException;
import com.lior.sq.client.DataObject;

public class JDOUtils
{
  public static <T> T find(Class<T> clz, String id) throws SQException {
    JDOSession session = JDOSession.open();   
    T retval = session.find(clz, id);
    retval = session.getPM().detachCopy(retval);
    session.close();
    
    return retval;
  }

  public static <T extends DataObject> Collection<T> findByIds(Class<T> clz,
      Set<String> oids)
  {
    JDOSession session = JDOSession.open();
    Collection<T> retval = session.findByIds(clz, oids);
    retval = session.getPM().detachCopy(retval);
    session.close();
    
    return retval;
  }

  public static <T extends DataObject> void persist(T o) throws SQException {
    (new PersistAction<T>(o)).perform();
  }
  
  public static <T extends DataObject> void persist(Collection<T> coll) throws SQException {
    (new PersistManyAction<T>(coll)).perform();
  }

}
