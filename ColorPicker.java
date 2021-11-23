import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.ArrayList;
import java.awt.FontMetrics;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Date;

public class ColorPicker extends JDialog {

    public ColorPicker() {
        super();
        this.setLocation(50, 50);
        this.setPreferredSize(new Dimension(359,99));
        this.setSize(new Dimension(359,99));
        this.setResizable(false);

    }
}