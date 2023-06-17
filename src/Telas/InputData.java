package Telas;

import javax.swing.*;

public class InputData {

    public static String InputData(String titulo) {
        JComboBox<Integer> dayComboBox = new JComboBox<>();
        JComboBox<Integer> monthComboBox = new JComboBox<>();
        JComboBox<Integer> yearComboBox = new JComboBox<>();

        for (int i = 1; i <= 31; i++) {
            dayComboBox.addItem(i);
        }

        for (int i = 1; i<= 12; i++) {
            monthComboBox.addItem(i);
        }

        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        for (int i = currentYear; i >= currentYear-100; i--) {
            yearComboBox.addItem(i);
        }

        JPanel panel = new JPanel();
        panel.add(dayComboBox);
        panel.add(monthComboBox);
        panel.add(yearComboBox);

        int result = JOptionPane.showOptionDialog(null, panel, titulo, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

        String selectedDate = null;
        if (result == JOptionPane.OK_OPTION) {
            int day = (int) dayComboBox.getSelectedItem();
            int month = (int) monthComboBox.getSelectedItem();
            int year = (int) yearComboBox.getSelectedItem();

            selectedDate = String.format("%02d %02d %04d", day, month, year);
        }
        return selectedDate;
    }
}
