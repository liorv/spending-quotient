package com.lior.jdo;

import com.lior.sq.SQException;
import com.lior.sq.client.DataObject;

public class PersistAction<T extends DataObject> extends JDOAction
{
  private T o;

  public PersistAction(T o) {
    this.o = o;
  }

  @Override
  public void doIt(JDOSession session) throws SQException{
    session.persist(o);
  }

  @Override
  public boolean isTransactional() {
    return true;
  }

}
