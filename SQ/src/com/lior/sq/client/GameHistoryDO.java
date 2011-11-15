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
  Vector<Integer> sqArr;
  
  public GameHistoryDO() {
    sqArr = new Vector<Integer>();
  }
  
  public GameHistoryDO(Vector<Integer> sqArr) {
    this.sqArr = sqArr;
  }

  public Vector<Integer> getSqArr() {
    return sqArr;
  }

  public void setSqArr(Vector<Integer> sqArr) {
    this.sqArr = sqArr;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getMySQ() {
    double sum = 0;
    int size = sqArr.size();
    int n = (size > 10) ? 10 : size;
    for(int i = size-1; i>=size - n; i--) {
      double d = sqArr.get(i).doubleValue();
      sum += d;
    }
    return (int) (sum / n);
  }
}
