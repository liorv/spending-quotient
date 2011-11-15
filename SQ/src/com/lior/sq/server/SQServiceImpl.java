package com.lior.sq.server;

import java.util.Vector;

import org.springframework.stereotype.Service;

import com.lior.jdo.JDOAction;
import com.lior.jdo.JDOSession;
import com.lior.jdo.JDOUtils;
import com.lior.sq.SQException;
import com.lior.sq.client.GameHistoryDO;
import com.lior.sq.client.SQService;

@Service("sqService")
public class SQServiceImpl implements SQService
{
  SQServiceImpl() {}

  @Override
  public void addGame(String uid, int sq) {
    try {
      GameHistoryDO history = JDOUtils.find(GameHistoryDO.class, uid);
      if (history == null) {
        history = new GameHistoryDO();
        history.setId(uid);
      }
      history.getSqArr().add(sq);
      JDOUtils.persist(history);
    }
    catch (SQException e) {
    }
  }

  @Override
  public GameHistoryDO getHistory(String uid) {
    try {
      return registerIfUndefined(uid);
    }
    catch (SQException e) {
      return null;
    }
  }

  public GameHistoryDO registerIfUndefined(String uid) throws SQException {
    GameHistoryDO h = null;
    try {
      h = JDOUtils.find(GameHistoryDO.class, uid);
    }
    catch (SQException e) {
      h = new GameHistoryDO();
      JDOUtils.persist(h);
    }
    return h;
  }

  private class ClearByIdAction extends JDOAction
  {
    String uid;

    public ClearByIdAction(String id) {
      uid = id;
    }

    @Override
    public void doIt(JDOSession session) throws SQException {
      Vector<String> oids = new Vector<String>();
      oids.add(uid);
      session.clear(GameHistoryDO.class, oids);
    }

    @Override
    public boolean isTransactional() {
      return true;
    }
  };

  @Override
  public void clear(String uid) {
    try {
      ClearByIdAction act = new ClearByIdAction(uid);
      act.perform();
    }
    catch (SQException e) {
    }
  }
}
