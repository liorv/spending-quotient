package com.lior.sq.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Client side interface for the greeting service.
 */
@RemoteServiceRelativePath("springGwtServices/sqService")
public interface SQService extends RemoteService
{
  void clear(String uid);
  
  GameHistoryDO getHistory(String uid);
  
  void addGame(String uid, double sq);
}
