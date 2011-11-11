package com.lior.jdo;

import com.lior.sq.SQException;

public class ClearByClassAction<T> extends JDOAction
{
  private Class<T> clz;

  public ClearByClassAction(Class<T> clz) {
    this.clz = clz;
  }

  @Override
  public void doIt(JDOSession session) throws SQException{
    session.clear(clz);
  }

  @Override
  public boolean isTransactional() {
    return true;
  }

}
