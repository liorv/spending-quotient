package com.lior.sq.client;

import com.extjs.gxt.charts.client.model.charts.dots.BaseDot;

class GameInfo extends BaseDot
{
  private static final long serialVersionUID = -7562400130163889653L;

  public static final String IDX = "index";
  public static final String SQ = "sq";

  public GameInfo(int idx, double sq) {
    super.setXY(idx, 0);
    super.setValue(sq);
    set(IDX, idx);
    set(SQ, sq);
    
  }


  public int getIndex() {
    Integer idx = (Integer) get(IDX);
    return idx.intValue();
  }

  public Double getSq() {
    Double sq = (Double) get(SQ);
    return sq.doubleValue();
  }
}
