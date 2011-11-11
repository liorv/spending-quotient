package com.lior.sq.client;

import java.io.Serializable;
import java.util.Vector;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class GameHistoryDO implements Serializable, DataObject 
{
  private static final long serialVersionUID = 2035118999804636230L;
  
  @PrimaryKey
  String id;
  
  @Persistent
  Vector<Double> sqArr;
  
  public GameHistoryDO() {
    sqArr = new Vector<Double>();
  }
  
  public GameHistoryDO(Vector<Double> sqArr) {
    this.sqArr = sqArr;
  }

  public Vector<Double> getSqArr() {
    return sqArr;
  }

  public void setSqArr(Vector<Double> sqArr) {
    this.sqArr = sqArr;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public double getMySQ() {
    double sum = 0;
    int size = sqArr.size();
    int n = (size > 10) ? 10 : size;
    for(int i = size-1; i>=size - n; i--) {
      double d = sqArr.get(i).doubleValue();
      sum += d;
    }
    return (sum / n);
  }
}
