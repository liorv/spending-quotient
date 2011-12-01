package com.lior.sq.server;

import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;

import com.lior.jdo.JDOUtils;
import com.lior.sq.SQException;
import com.lior.sq.client.GameHistoryDO;
import com.lior.sq.client.SQService;

@Service("sqService")
public class SQServiceImpl implements SQService
{
  SQServiceImpl() {}

  @Override
  public GameHistoryDO addGame(String uid, int sq) {
    try {
      GameHistoryDO history = JDOUtils.find(GameHistoryDO.class, uid);
      if (history == null) {
        history = new GameHistoryDO();
        history.setId(uid);
      }
      history.getSqArr().add(sq);
      JDOUtils.persist(history);
      return history;
    }
    catch (SQException e) {
      return new GameHistoryDO();
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

  @Override
  public GameHistoryDO clear(String uid, List<Integer> indices) {
    Collections.sort(indices);

    try {
      GameHistoryDO h = registerIfUndefined(uid);
      if (indices.size() < 1) return h;

      if (indices.size() >= h.getSqArr().size()) {
        GameHistoryDO retval = new GameHistoryDO();
        retval.setId(h.getId());
        JDOUtils.persist(retval);
        return retval;
      }

      for (int i = indices.size() - 1; i >= 0; i--) {
        h.getSqArr().remove(indices.get(i) - 1);
      }
      JDOUtils.persist(h);
      return h;
    }
    catch (SQException e) {
      return new GameHistoryDO();
    }
  }
}
