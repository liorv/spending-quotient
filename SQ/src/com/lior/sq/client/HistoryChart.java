package com.lior.sq.client;

import com.extjs.gxt.charts.client.Chart;
import com.extjs.gxt.charts.client.model.ChartModel;
import com.extjs.gxt.charts.client.model.axis.YAxis;
import com.extjs.gxt.charts.client.model.charts.AreaChart;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.Window;

public class HistoryChart extends ContentPanel
{
  GameHistoryDO history;
  Chart chart;
  ChartModel cm;

  public HistoryChart(GameHistoryDO initialHistory) {
    setSize(400, 400);
    history = initialHistory;
    setHeading("SQ Graph over Games");
    setFrame(true);
    setLayout(new FitLayout());
    add(createChart(initialHistory));
  }

  public void update(GameHistoryDO h) {
    history = h;

    createChartModel(h);
    chart.setChartModel(cm);
    chart.refresh();
  }

  public static boolean isExplorer() {
    String test = Window.Location.getPath();
    if (test.indexOf("pages") != -1) { return false; }
    return true;
  }

  private ChartModel createChartModel(GameHistoryDO h) {
    cm = new ChartModel();
    cm.setBackgroundColour("#ffffff");
    YAxis ya = new YAxis();
    ya.setRange(0, 100);
    ya.setSteps(5);

    AreaChart area1 = new AreaChart();
    area1.setFillAlpha(0.3f);
    area1.setColour("#ff0000");
    area1.setFillColour("#ff0000");

    if (h.sqArr.size() > 0) {
      for (Integer D : history.getSqArr()) {
        area1.addValues(D.intValue());
      }
    }
    else {
      for (int n = 0; n < 12; n++) {
        if (n != 0 && n != 11)
          area1.addNullValue();
        else
          area1.addValues(0);
      }
    }
    cm.addChartConfig(area1);
    cm.setYAxis(ya);
    return cm;
  }

  private Chart createChart(GameHistoryDO h) {
    String url = !isExplorer() ? "../../" : "";
    url += "resources/chart/open-flash-chart.swf";

    chart = new Chart(url);
    chart.setChartModel(createChartModel(h));
    return chart;
  }
}