package com.conveniencestore.gui;

import com.conveniencestore.gui.mainlayout.HeaderPanel;
import com.conveniencestore.gui.mainlayout.MainContentPanel;
import com.conveniencestore.gui.mainlayout.SidebarListener;
import com.conveniencestore.gui.mainlayout.SidebarPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainFrame extends JFrame implements SidebarListener {
    private SidebarPanel sidebar;
    private MainContentPanel mainContent;

    private Dimension screenSize;
    public MainFrame() {
        setTitle("Freshman Convenience Store");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Lấy kích thước màn hình
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Set kích thước ban đầu = màn hình
        setSize(screenSize);

        // Giới hạn thu nhỏ
        setMinimumSize(new Dimension(1100, 650));

        
        setLayout(new BorderLayout());

        HeaderPanel header = new HeaderPanel();
        sidebar = new SidebarPanel();
        sidebar.setSidebarListener(this);

        mainContent = new MainContentPanel();

        JPanel body = new JPanel(new BorderLayout());
        body.add(sidebar, BorderLayout.WEST);
        body.add(mainContent, BorderLayout.CENTER);

        add(header, BorderLayout.NORTH);
        add(body, BorderLayout.CENTER);

        // Resize listener theo kích thước màn hình
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                handleResize();
            }
        });
    }

    // Kế thừa phương thúc của interface => Lắng nghe sự kiện chọn ở sidebar
    @Override
    public void onMenuSelected(String menuKey) {
        mainContent.showPage(menuKey);
    }

    // Thu nhỏ sidebar khi người dùng resize
    private void handleResize() {
        int frameWidth = getWidth();
        int screenWidth = screenSize.width;

        boolean shouldCollapse = frameWidth < screenWidth;

        sidebar.collapse(shouldCollapse);
    }


    

}
