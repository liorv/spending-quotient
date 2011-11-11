package com.lior.sq.client;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;

public class HistoryGrid extends ContentPanel
{

  private ColumnModel cm;
  private GameStore store;
  private Grid<GameInfo> grid;

  public HistoryGrid(GameStore gameStore, ColumnModel cm2) {
    cm = cm2;
    store = gameStore;
    
    grid = new Grid<GameInfo>(store, cm);
    grid.setSize("500", "400");
    setBorders(true);
    
    add(grid);
  }

  public void update(GameHistoryDO history) {
    store = new GameStore(history);
    grid.reconfigure(store, cm);
  }
}