package com.lior.sq.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Client side interface for the greeting service.
 */
@RemoteServiceRelativePath("springGwtServices/sqService")
public interface SQService extends RemoteService
{
  GameHistoryDO clear(String uid, List<Integer> indices);
  
  GameHistoryDO getHistory(String uid);
  
  GameHistoryDO addGame(String uid, int sq);
}
