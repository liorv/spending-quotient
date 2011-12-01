package com.lior.sq.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SQServiceAsync
{
  void addGame(String uid, int sq, AsyncCallback<GameHistoryDO> callback);

  void getHistory(String id, AsyncCallback<GameHistoryDO> callback);

  void clear(String uid, List<Integer> indices, AsyncCallback<GameHistoryDO> callback);
}
