package com.lior.sq.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SQServiceAsync
{
  void addGame(String uid, double sq, AsyncCallback<Void> callback);

  void getHistory(String id, AsyncCallback<GameHistoryDO> callback);

  void clear(String uid, AsyncCallback<Void> callback);
}
