package com.lior.jdo;

import com.lior.sq.SQException;
import com.lior.sq.client.PersistedClasses;

public class ClearAllAction extends JDOAction
{
  public ClearAllAction() {}

  @Override
  public void doIt(JDOSession session) throws SQException {
    for (Class<?> clz : PersistedClasses.list()) {
      try {
        session.clear(clz);
      }
      catch (Exception e) {
      }
    }
  }

  @Override
  public boolean isTransactional() {
    return true;
  }

}
