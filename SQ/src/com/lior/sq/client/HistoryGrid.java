package com.lior.sq.client;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.grid.CheckBoxSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class HistoryGrid extends ContentPanel
{
  static private ColumnModel configureCols() {
    List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

    CheckBoxSelectionModel<GameInfo> sm = new CheckBoxSelectionModel<GameInfo>();
    configs.add(sm.getColumn());
    ColumnConfig column = new ColumnConfig();
    
    column.setId(GameInfo.IDX);
    column.setHeader(GameInfo.IDX);
    column.setWidth(200);
    column.setRowHeader(true);
    configs.add(column);

    column = new ColumnConfig();
    column.setId(GameInfo.SQ);
    column.setHeader(GameInfo.SQ);
    column.setWidth(200);
    configs.add(column);

    return new ColumnModel(configs);
  }
  
  private ColumnModel cm = configureCols();
  private GameStore store;
  private Grid<GameInfo> grid;

  public HistoryGrid(GameHistoryDO initialHistory) {
    setSize(400, 400);
    store = new GameStore(initialHistory);
    setLayout(new FitLayout());
    setHeading("SQ per Game");
    
    grid = new Grid<GameInfo>(store, cm);
    setBorders(true);
    add(grid);
  }

  public void update(GameHistoryDO history) {
    store = new GameStore(history);
    grid.reconfigure(store, cm);
  }

  public int[] getSelectedIndices() {
    List<GameInfo> games = grid.getSelectionModel().getSelectedItems();
    int[] retval = new int[games.size()];
    int i = 0;
    for(GameInfo o : games) {
      retval[i++] = o.getIndex();  
    }
    return retval;
  }
}