package com.lior.sq.client;

import com.extjs.gxt.ui.client.store.ListStore;

class GameStore extends ListStore<GameInfo>
{
  public GameStore(GameHistoryDO history) {
    for (int i = 0; i < history.sqArr.size(); i++) {
      add(new GameInfo(i + 1, history.sqArr.get(i).doubleValue()));
    }
  }
}
