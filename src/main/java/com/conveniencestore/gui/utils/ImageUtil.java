package com.conveniencestore.gui.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * ImageUtil - class tiện ích xử lý ảnh trong Swing
 * Hỗ trợ:
 * - Scale ảnh (ImageIcon, BufferedImage)
 * - Giữ tỉ lệ ảnh
 * - Dễ dùng cho JLabel, JButton
 */
public class ImageUtil {

    /**
     * Scale ImageIcon về kích thước mới
     * 
     * @param icon   ImageIcon gốc
     * @param width  chiều rộng mới
     * @param height chiều cao mới
     * @return ImageIcon đã scale
     */
    public static ImageIcon scaleIcon(ImageIcon icon, int width, int height) {
        if (icon == null)
            return null;
        Image img = icon.getImage();
        Image scaled = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    /**
     * Scale BufferedImage về kích thước mới
     * 
     * @param image  BufferedImage gốc
     * @param width  chiều rộng mới
     * @param height chiều cao mới
     * @return BufferedImage đã scale
     */
    public static BufferedImage scaleBufferedImage(BufferedImage image, int width, int height) {
        if (image == null)
            return null;
        Image tmp = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage scaled = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = scaled.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return scaled;
    }

    /**
     * Scale và giữ tỉ lệ ảnh theo chiều rộng
     * 
     * @param icon  ImageIcon gốc
     * @param width chiều rộng mới
     * @return ImageIcon đã scale giữ tỉ lệ
     */
    public static ImageIcon scaleIconKeepRatioWidth(ImageIcon icon, int width) {
        if (icon == null)
            return null;
        int originalWidth = icon.getIconWidth();
        int originalHeight = icon.getIconHeight();
        int height = (width * originalHeight) / originalWidth;
        return scaleIcon(icon, width, height);
    }

    /**
     * Scale và giữ tỉ lệ ảnh theo chiều cao
     * 
     * @param icon   ImageIcon gốc
     * @param height chiều cao mới
     * @return ImageIcon đã scale giữ tỉ lệ
     */
    public static ImageIcon scaleIconKeepRatioHeight(ImageIcon icon, int height) {
        if (icon == null)
            return null;
        int originalWidth = icon.getIconWidth();
        int originalHeight = icon.getIconHeight();
        int width = (height * originalWidth) / originalHeight;
        return scaleIcon(icon, width, height);
    }
}
