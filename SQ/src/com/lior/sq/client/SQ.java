package com.lior.sq.client;

import java.util.ArrayList;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class SQ implements EntryPoint {
	/**
	 * Create a remote service to talk to the server-side service.
	 */
	private final SQServiceAsync sqService = GWT.create(SQService.class);

	static protected Integer computeSQ(long income, long unspent) {
		double sq = 240 + 35 * (0.00137 * income - Math.log((double) unspent));
		return Integer.valueOf((int) sq);
	}

	protected HistoryGrid dataTable;
	
	private HistoryChart dataChart;

	protected TextBox incomeInput = new TextBox();

	protected TextBox unspentInput = new TextBox();

	protected TextBox uidInput = new TextBox();

	HTML sqLabel = new HTML();

	int dim = 74;
	Image rankPic;

	int sq = 0;

	private AsyncCallback<GameHistoryDO> getHistoryHandler = new AsyncCallback<GameHistoryDO>() {
		@Override
		public void onSuccess(GameHistoryDO h) {
			if (h == null)
				h = new GameHistoryDO();
			dataTable.update(h);
			dataChart.update(h);
			sq = (int) h.getMySQ();
			sqLabel.setHTML("<font size=\"20\">" + sq + "</font>");

			int SILVER = 1;
			int GOLD = 2;
			int PLAT = 3;
			int DIA = 4;
			int MASTER = 5;
			int GM = 6;
			/*
			 * =IF(D2<47.5,"Bronze", IF(D2<52.5,"Silver", IF(D2<59,"Gold",
			 * IF(D2<65,"Platnium" ,IF(D2<72.5,"Diamond", IF(D2<82,"Master",
			 * "GrandMaster"))))))
			 */
			int i = 0;
			if (sq >= 47.5)
				i = SILVER;
			if (sq >= 52.5)
				i = GOLD;
			if (sq >= 59)
				i = PLAT;
			if (sq >= 65)
				i = DIA;
			if (sq >= 72.5)
				i = MASTER;
			if (sq >= 82)
				i = GM;

			rankPic.setUrlAndVisibleRect("images/ranks-small.png", i * dim, 7,
					dim, dim);
			Info.display("info", "getHistory::OK");
		}

		@Override
		public void onFailure(Throwable caught) {
			Info.display("info", "getHistory::BAD");
		}

	};

	static public Widget labeledWidget(String text, Widget[] fields) {
		FlexTable table = new FlexTable();
		table.setWidget(0, 0, new HTML(text));
		for (int i = 0; i < fields.length; i++)
			table.setWidget(0, i + 1, fields[i]);
		return table;
	}

	public static Widget vGap(int gap) {
		Label l = new Label(" ");
		l.setPixelSize(10, gap);
		return l;
	}

	public static Widget hGap(int gap) {
		Label l = new Label(" ");
		l.setPixelSize(gap, 10);
		return l;
	}

	public static Widget hGap() {
		Label l = new Label("     ");
		l.setPixelSize(100, 10);
		return l;
	}

	private Widget createLoginTitlePanel() {
		Button goButton = new Button("Load");
		goButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				sqService.getHistory(uidInput.getText(), getHistoryHandler);
			}
		});

		AbsolutePanel panel = new AbsolutePanel();
		panel.setSize("600px", "140px");
		Widget logo = new HTML(
				"<font color='red' size='6'>Spending Quotient: </font>");
		Widget[] fields = { uidInput, goButton };
		Widget input = labeledWidget("id:  ", fields);

		Widget rank = createRankPanel();
		sqLabel = new HTML("<font size=\"20\">0</font>");

		panel.add(logo, 10, 10);

		panel.add(input, 10, 60);
		panel.add(rank, 320, 15);
		panel.add(sqLabel, 400, 15);

		return panel;
	}

	private Widget createRankPanel() {
		int SILVER = 1;
		int GOLD = 2;
		int PLAT = 3;
		int DIA = 4;
		int MASTER = 5;
		int GM = 6;
		/*
		 * =IF(D2<47.5,"Bronze", IF(D2<52.5,"Silver", IF(D2<59,"Gold",
		 * IF(D2<65,"Platnium" ,IF(D2<72.5,"Diamond", IF(D2<82,"Master",
		 * "GrandMaster"))))))
		 */
		int i = 0;
		if (sq >= 47.5)
			i = SILVER;
		if (sq >= 52.5)
			i = GOLD;
		if (sq >= 59)
			i = PLAT;
		if (sq >= 65)
			i = DIA;
		if (sq >= 72.5)
			i = MASTER;
		if (sq >= 82)
			i = GM;

		rankPic = new Image("images/ranks-small.png", i * dim, 7, dim, dim);
		return rankPic;
	}

	private LayoutContainer createDataDisplayPanel() {
		HorizontalPanel panel = new HorizontalPanel();

		GameHistoryDO initialHistory = new GameHistoryDO();
		dataTable = new HistoryGrid(initialHistory);
		dataChart = new HistoryChart(initialHistory);
		panel.add(hGap(20));
		panel.add(dataTable);
		panel.add(hGap(20));
		panel.add(dataChart);
		return panel;
	}

	private LayoutContainer createInputPanel() {
		Button addButton = new Button("Add Score");
		addButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
			int sq;

			@Override
			public void componentSelected(ButtonEvent ce) {
				try {
					long unspent = Long.valueOf(unspentInput.getText());
					long income = Long.valueOf(incomeInput.getText());
					sq = computeSQ(income, unspent);
				} catch (Exception e) {
					return;
				}
				sqService.addGame(uidInput.getText(), sq, getHistoryHandler);
			}
		});

		Button clearButton = new Button("CLEAR");
		clearButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
			  int[] indices = dataTable.getSelectedIndices();
			  ArrayList<Integer> idxList = new ArrayList<Integer>();
			  for(int i=0; i<indices.length; i++)
			  idxList.add(Integer.valueOf(indices[i]));
				sqService.clear(uidInput.getText(), idxList, getHistoryHandler);
			}
		});

		HorizontalPanel panel = new HorizontalPanel();
		
		panel.add(hGap(20));
		panel.add(new HTML("Average Unspent: "));
		panel.add(unspentInput);
		
		panel.add(hGap(20));
    panel.add(new HTML("Collection Rate: "));
    panel.add(incomeInput);
		
		panel.add(addButton);
		panel.add(hGap(100));
		panel.add(clearButton);
		return panel;
	}

	private LayoutContainer createCompositeViewPanel() {
		VerticalPanel container = new VerticalPanel();
		container.add(createLoginTitlePanel());
		container.add(createDataDisplayPanel());
		container.add(vGap(20));
		container.add(createInputPanel());
		container.setBorders(true);
		return container;
	}

	public void onModuleLoad() {
		if (Document.get() != null) {
			Document.get().setTitle("SQ-LV");
		}
		// MessageBox.info("Message", "Hello World!!", null);

		ContentPanel cp = new ContentPanel();
		cp.setHeading("BLAH");
		cp.setSize("100%", "100%");
		cp.setPosition(10, 10);
		cp.setCollapsible(true);
		cp.setFrame(true);
		cp.setBodyStyle("backgroundColor: white;");
		cp.setIconStyle("tree-folder-open");
		cp.setLayout(new FitLayout());
		cp.setTopComponent(createCompositeViewPanel());
		Viewport vp = new Viewport();
		vp.add(cp);
		cp.setSize("100%", "100%");

		RootPanel.get().add(vp);
		cp.layout();
	}
}
