package com.lior.sq.client;

import java.util.ArrayList;
import java.util.List;

import java.util.Date;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.util.DateWrapper;

public class TestData
{
  
  
  @SuppressWarnings("serial")
  public static class Stock extends BaseModel
  {

    public Stock() {}

    public Stock(String name, double open, double change, double pctChange,
        Date date, String industry)
    {
      set("name", name);
      set("open", open);
      set("change", change);
      set("percentChange", pctChange);
      set("date", date);
      set("industry", industry);
      set("split", new Boolean(Math.random() > .5));
      set("type", getType());
    }

    public Stock(String name, String symbol, double open, double last) {
      set("name", name);
      set("symbol", symbol);
      set("open", open);
      set("last", last);
      set("date", new DateWrapper().addDays(-(int) (Math.random() * 100))
          .asDate());
      set("change", last - open);
      set("split", new Boolean(Math.random() > .5));
      set("type", getType());
    }

    public double getChange() {
      return getLast() - getOpen();
    }

    public String getIndustry() {
      return get("industry");
    }

    public double getLast() {
      Double open = (Double) get("last");
      return open.doubleValue();
    }

    public Date getLastTrans() {
      return (Date) get("date");
    }

    public String getName() {
      return (String) get("name");
    }

    public double getOpen() {
      Double open = (Double) get("open");
      return open.doubleValue();
    }

    public double getPercentChange() {
      return getChange() / getOpen();
    }

    public String getSymbol() {
      return (String) get("symbol");
    }

    public void setIndustry(String industry) {
      set("industry", industry);
    }

    public String toString() {
      return getName();
    }

    private String getType() {
      double r = Math.random();
      if (r <= .25) {
        return "Auto";
      }
      else if (r > .25 && r <= .50) {
        return "Media";
      }
      else if (r > .5 && r <= .75) {
        return "Medical";
      }
      else {
        return "Tech";
      }
    }

  }

  public static String DUMMY_TEXT_SHORT =
      "Lorem Ipsum is simply dummy text of the "
          + "printing and typesetting industry. Lorem Ipsum has been the industry's standard "
          + "dummy text ever since the 1500s.";

  public static String DUMMY_TEXT_LONG =
      "<div class=text style='padding:2 8px'><p>Lorem ipsum dolor sit amet, consectetuer "
          + "adipiscing elit. Suspendisse velit metus, ultricies nec, aliquam quis, porttitor "
          + "a, felis. Donec est. Pellentesque urna dolor, bibendum nec, commodo a, laoreet sed, "
          + "turpis. Morbi tristique orci ac felis. Suspendisse nec tellus. Donec vitae quam "
          + "sed nibh luctus auctor. Suspendisse rhoncus lacus non magna. Proin consectetuer "
          + "arcu ac metus. Integer tristique erat id leo. Sed interdum dictum quam. Integer "
          + "eros. Vivamus eget elit. Quisque eget urna. Mauris venenatis molestie enim. "
          + "Aenean justo nisi, sodales vitae, pellentesque scelerisque, gravida vitae, diam. "
          + "<p>Curabitur pellentesque nulla et tellus. Fusce suscipit. Phasellus diam dolor, "
          + "ullamcorper a, placerat ac, elementum sit amet, arcu. In commodo. Duis vitae "
          + "justo vel quam nonummy imperdiet. Nam hendrerit convallis nisl. Praesent eu arcu. "
          + "Morbi justo. Proin semper venenatis nulla. Pellentesque habitant morbi tristique "
          + "senectus et netus et malesuada fames ac turpis egestas. Vivamus feugiat odio vitae "
          + "tortor. Mauris augue enim, volutpat vitae, aliquet eu, feugiat congue, nisl. Maecenas "
          + "ac orci. Donec malesuada. Proin nunc. Sed vitae urna. Nam ut mi. Nullam tempus vulputate ipsum. "
          + "Integer lacinia nonummy mauris. Nullam rhoncus accumsan nibh.</p><p>Fusce mattis. Donec "
          + "feugiat, lectus sit amet aliquet feugiat, nisi ante aliquam lacus, id facilisis metus nisl et "
          + "eros. Vestibulum tempor. Proin augue dui, commodo ut, aliquet non, tristique a, nulla. "
          + "Fusce dolor enim, bibendum at, placerat vel, ultricies vitae, libero. Praesent eu sem suscipit "
          + "dolor cursus gravida. Quisque tortor mauris, aliquam at, placerat at, iaculis non, ante. In "
          + "hendrerit, enim sed facilisis blandit, turpis erat lobortis velit, quis dapibus mauris "
          + "sapien ut nisl. Aliquam non leo eget elit ultrices ullamcorper. Aliquam porta, purus in "
          + "euismod vulputate, tellus pede imperdiet elit, vulputate viverra ipsum purus ac dolor. "
          + "Vivamus tempor lorem quis lorem. Maecenas et felis. Integer accumsan convallis est. Etiam ut "
          + "augue quis augue congue hendrerit. Vestibulum ante ipsum primis in faucibus orci luctus et "
          + "ultrices posuere cubilia Curae; Cras sem.</p><p>In hac habitasse platea dictumst. Donec facilisis rhoncus purus. "
          + "Suspendisse vulputate, nunc et mattis scelerisque, enim nisi imperdiet lectus, sed aliquet sapien nisl "
          + "feugiat tortor. Cras sit amet nisi. Vivamus dignissim. Integer a ligula. Morbi euismod. Aenean malesuada. "
          + "Pellentesque ut nisi eu purus egestas aliquam. Phasellus dolor augue, tempor a, rhoncus ac, accumsan ut, urna. "
          + "Aenean aliquet semper elit. Sed porta eros ac orci. Proin mollis dui iaculis felis. Suspendisse tortor nisi, "
          + "scelerisque at, adipiscing sagittis, vehicula et, sem. Etiam vulputate. Nullam vestibulum eros sed sapien. "
          + "<p>Duis molestie tempor arcu. Nam eu nunc. Vivamus at neque eu mi lobortis euismod. Sed erat pede, luctus a, "
          + "gravida quis, varius quis, ipsum. Proin vel massa. Cras auctor risus non nunc semper semper. Lorem ipsum dolor "
          + "sit amet, consectetuer adipiscing elit. Sed vel arcu. Sed consectetuer. Duis libero eros, imperdiet sed, "
          + "condimentum a, pretium nec, diam. Cum sociis natoque penatibus et magnis dis parturient montes, "
          + "nascetur ridiculus mus. Nunc egestas, urna nec interdum interdum, risus justo malesuada quam, vitae "
          + "consequat urna turpis at metus. Sed id neque eget diam euismod aliquet. Nam sed tortor. Praesent hendrerit "
          + "scelerisque dolor. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Phasellus semper, odio id "
          + "rutrum volutpat, mauris ante tempus metus, bibendum porta dolor ligula pretium tortor.</div>";

  public static List<Stock> getStocks() {
    List<Stock> stocks = new ArrayList<Stock>();

    stocks.add(new Stock("Apple Inc.", "AAPL", 125.64, 123.43));
    stocks.add(new Stock("Cisco Systems, Inc.", "CSCO", 25.84, 26.3));
    stocks.add(new Stock("Google Inc.", "GOOG", 516.2, 512.6));
    stocks.add(new Stock("Intel Corporation", "INTC", 21.36, 21.53));
    stocks.add(new Stock("Level 3 Communications, Inc.", "LVLT", 5.55, 5.54));
    stocks.add(new Stock("Microsoft Corporation", "MSFT", 29.56, 29.72));
    stocks.add(new Stock("Nokia Corporation (ADR)", "NOK", 27.83, 27.93));
    stocks.add(new Stock("Oracle Corporation", "ORCL", 18.73, 18.98));
    stocks.add(new Stock("Starbucks Corporation", "SBUX", 27.33, 27.36));
    stocks.add(new Stock("Yahoo! Inc.", "YHOO", 26.97, 27.29));
    stocks.add(new Stock("Applied Materials, Inc.", "AMAT", 18.4, 18.66));
    stocks.add(new Stock("Comcast Corporation", "CMCSA", 25.9, 26.4));
    stocks.add(new Stock("Sirius Satellite", "SIRI", 2.77, 2.74));

    stocks.add(new Stock("Tellabs, Inc.", "TLAB", 10.64, 10.75));
    stocks.add(new Stock("eBay Inc.", "EBAY", 30.43, 31.21));
    stocks.add(new Stock("Broadcom Corporation", "BRCM", 30.88, 30.48));
    stocks.add(new Stock("CMGI Inc.", "CMGI", 2.14, 2.13));
    stocks.add(new Stock("Amgen, Inc.", "AMGN", 56.22, 57.02));
    stocks.add(new Stock("Limelight Networks", "LLNW", 23, 22.11));
    stocks.add(new Stock("Amazon.com, Inc.", "AMZN", 72.47, 72.23));

    stocks
        .add(new Stock("E TRADE Financial Corporation", "ETFC", 24.32, 24.58));
    stocks.add(new Stock("AVANIR Pharmaceuticals", "AVNR", 3.7, 3.52));
    stocks.add(new Stock("Gemstar-TV Guide, Inc.", "GMST", 4.41, 4.55));
    stocks.add(new Stock("Akamai Technologies, Inc.", "AKAM", 43.08, 45.32));
    stocks.add(new Stock("Motorola, Inc.", "MOT", 17.74, 17.69));
    stocks.add(new Stock("Advanced Micro Devices, Inc.", "AMD", 13.77, 13.98));
    stocks.add(new Stock("General Electric Company", "GE", 36.8, 36.91));
    stocks.add(new Stock("Texas Instruments Incorporated", "TXN", 35.02, 35.7));
    stocks.add(new Stock("Qwest Communications", "Q", 9.9, 10.03));
    stocks.add(new Stock("Tyco International Ltd.", "TYC", 33.48, 33.26));
    stocks.add(new Stock("Pfizer Inc.", "PFE", 26.21, 26.19));
    stocks.add(new Stock("Time Warner Inc.", "TWX", 20.3, 20.45));
    stocks.add(new Stock("Sprint Nextel Corporation", "S", 21.85, 21.76));
    stocks.add(new Stock("Bank of America Corporation", "BAC", 49.92, 49.73));
    stocks.add(new Stock("Taiwan Semiconductor", "TSM", 10.4, 10.52));
    stocks.add(new Stock("AT&T Inc.", "T", 39.7, 39.66));
    stocks
        .add(new Stock("United States Steel Corporation", "X", 115.81, 114.62));
    stocks.add(new Stock("Exxon Mobil Corporation", "XOM", 81.77, 81.86));
    stocks.add(new Stock("Valero Energy Corporation", "VLO", 72.46, 72.6));
    stocks.add(new Stock("Micron Technology, Inc.", "MU", 12.02, 12.27));
    stocks.add(new Stock("Verizon Communications Inc.", "VZ", 42.5, 42.61));
    stocks.add(new Stock("Avaya Inc.", "AV", 16.96, 16.96));
    stocks.add(new Stock("The Home Depot, Inc.", "HD", 37.66, 37.79));

    stocks.add(new Stock("First Data Corporation", "FDC", 32.7, 32.65));
    return stocks;

  }
}
