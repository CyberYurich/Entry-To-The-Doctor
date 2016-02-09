/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presenter;

import com.exceptions.WrongDayOfWeekException;
import com.messageServise.interfaces.IMessageService;
import com.model.interfaces.IDbModel;
import com.model.interfaces.IEntry;
import com.presenter.interfaces.IPresenter;
import com.view.interfaces.ICalendarView;
import com.view.interfaces.IConnectView;
import com.view.interfaces.IEntriesView;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author ALEX
 */
public class MainPresenter implements IPresenter {

    private static final int TABLE_ROW_COUNT = 6;
    private static final int ONE_MINUTE_IN_MILLISECS = 60000;

    private final IDbModel dbModel;
    private final ICalendarView calendarView;
    private final IEntriesView entriesView;
    private final IConnectView connectView;
    private final IMessageService messageService;

    public MainPresenter(IDbModel dbModel,
            ICalendarView calendarView,
            IEntriesView entriesView,
            IConnectView connectView,
            IMessageService messageService) {
        this.dbModel = dbModel;
        this.calendarView = calendarView;
        this.entriesView = entriesView;
        this.connectView = connectView;
        this.messageService = messageService;

        calendarView.setPresenter(this);
        connectView.setPresenter(this);
        entriesView.setPresenter(this);
    }

    @Override
    public void showEntries() {
        try {
            Date date = calendarView.getCheckedDate();

            Vector<Vector<Object>> dataTable = makeDataTableByDate(date);

            entriesView.setDataTable(dataTable);
            entriesView.setCurentDate(date);

            calendarView.closeView();
            entriesView.showView();

        } catch (WrongDayOfWeekException ex) {
            messageService.showError(new StringBuilder(90)
                    .append("Выбран неправильный день недели.\n")
                    .append("Воскресенье - неприемный день.")
                    .toString(),
                    "Неправильный день недели");
        } catch (SQLException | ClassNotFoundException ex) {
            messageService.showError(new StringBuilder(70)
                    .append("Соединение с базой данных утеряно\n")
                    .append("или некорректная таблица с данными.")
                    .toString(),
                    "Ошибка подключения");

            calendarView.lockEntriesView();
        }
    }

    @Override
    public void showCalendar() {
        entriesView.closeView();
        connectView.closeView();
        calendarView.showView();
    }

    @Override
    public void showConnect() {
        calendarView.closeView();
        connectView.showView();
    }

    @Override
    public void createConnection() {

        try {
            dbModel.setConnectionParameters(connectView.getConnectParameters());
            messageService.showInformation("Соединение с базой данных установлено.",
                    "Успешное подключение");
            calendarView.unlockEntriesView();
            showCalendar();
        } catch (SQLException ex) {
            messageService.showError("Невозможно подключиться к базе данных.",
                    "Ошибка подключения");
        }
    }

    private Vector<Vector<Object>> makeDataTableByDate(Date date)
            throws SQLException, ClassNotFoundException {

        Vector<Vector<Object>> dataTable = new Vector<>();
        List<IEntry> entriesList = dbModel.readByDate(date);
        for (IEntry entry : entriesList) {
            Vector<Object> row = new Vector<>();
            row.add(entry.getTime());
            row.add(entry.getLastname());
            row.add(entry.getFirstname());
            row.add(entry.getMiddlename());
            row.add(entry.getPhone());
            row.add(entry.getEmail());
            row.add(entry.getShoeSize());
            row.add(entry.getProductModel());
            dataTable.add(row);
        }
        return dataTable;
//        ResultSetMetaData metaData = resultSet.getMetaData();
//        int columnCount = metaData.getColumnCount();
//
//        /**
//         * make map of db data for sorting by key(place_in_queue)
//         */
//        Map<Integer, Vector<Object>> dataMap = new TreeMap<>();
//        while (resultSet.next()) {
//            Vector<Object> row = new Vector<>();
//            for (int i = 1; i <= columnCount; ++i) {
//                row.add(resultSet.getObject(i));
//            }
//            Integer placeInQueue = resultSet.getInt(1);
//            dataMap.put(placeInQueue, row);
//        }
//
//        /**
//         * make 2d vector with sorted by queue data
//         */
//        Vector<Vector<Object>> dataTable = new Vector<>();
//        for (int i = 0; i < TABLE_ROW_COUNT; ++i) {
//            if (dataMap.get(i) != null) {
//                dataTable.add(dataMap.get(i));
//            } else {
//                dataTable.add(new Vector<>());
//            }
//        }
//        return dataTable;

//        Calendar calendar = new GregorianCalendar();
//        calendar.setTime(date);
//        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
//
//        try {
//            // create time and format
//            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
//            Date timeForRows;
//
//            if (dayOfWeek == Calendar.SATURDAY) {
//                // set start time on 13:00
//                timeForRows = timeFormat.parse("13:00");
//            } else {
//                // set start time on 16:00
//                timeForRows = timeFormat.parse("16:00");
//            }
//
//            // insert time in each row and increments by 30 minutes
//            for (int i = 0; i < model.getRowCount(); ++i) {
//                model.setValueAt(timeFormat.format(timeForRows), i, 0);
//
//                long t = timeForRows.getTime();
//                timeForRows = new Date(t + (30 * ONE_MINUTE_IN_MILLISECS));
//            }
//        } catch (ParseException ex) {
//                Logger.getLogger(MainPresenter.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

}
