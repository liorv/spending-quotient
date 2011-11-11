package com.lior.jdo;

import java.util.Collection;

import com.lior.sq.SQException;
import com.lior.sq.client.DataObject;

public class PersistManyAction<T extends DataObject> extends JDOAction
{
  private Collection<T> coll;

  public PersistManyAction(Collection<T> coll) {
    this.coll = coll;
  }

  @Override
  public void doIt(JDOSession session) throws SQException {
    session.persist(coll);
  }

  @Override
  public boolean isTransactional() {
    return true;
  }

}
