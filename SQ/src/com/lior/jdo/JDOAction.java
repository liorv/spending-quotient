package com.lior.jdo;

import org.apache.log4j.Logger;

import com.lior.sq.SQException;

abstract public class JDOAction
{
  private static Logger log = Logger.getLogger(JDOAction.class);
  
  protected JDOAction() {}

  public abstract void doIt(JDOSession session) throws SQException;

  public abstract boolean isTransactional();

  public void perform() throws SQException {
    JDOSession session = null;

    if(log.isDebugEnabled())
      log.debug("perform(" + this.getClass().getSimpleName() + ")");
    try {
      session = JDOSession.open(isTransactional());
      doIt(session);
      session.close();
    }
    catch (SQException lv) {
      lv.printStackTrace();
      throw lv;
    }
  }
}
