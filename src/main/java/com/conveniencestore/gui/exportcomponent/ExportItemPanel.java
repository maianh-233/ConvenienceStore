package com.conveniencestore.gui.exportcomponent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import com.conveniencestore.DTO.ImportItemRequestDTO;
import com.conveniencestore.DTO.ImportItemResponseDTO;
import com.conveniencestore.DTO.InventoryExportItemRequestDTO;
import com.conveniencestore.DTO.InventoryExportItemResponseDTO;
import com.conveniencestore.DTO.InventoryExportRequestDTO;
import com.conveniencestore.DTO.InventoryExportResponseDTO;
import com.conveniencestore.gui.utils.CustomButton;
import com.conveniencestore.gui.utils.ImageUtil;
import com.conveniencestore.gui.utils.TableUtil;

public class ExportItemPanel extends JPanel {

	/* ================= MODE ================= */
	public static final int MODE_VIEW = 0;
	public static final int MODE_ADD = 1;
	public static final int MODE_EDIT = 2;

	private int parentMode = MODE_VIEW;

	/* ================= DATA ================= */
	private final List<InventoryExportItemResponseDTO> responseItems = new ArrayList<>();
	private final List<InventoryExportItemRequestDTO> requestItems = new ArrayList<>();

	/* ================= COMPONENTS ================= */
	private JTextField txtProduct;
	private JSpinner spQuantity;
	private JTextArea txtDescription;
	private JTextField txtInventoryOriginal;
	private JTextField txtInventoryAfter;
	private CustomButton btnSelectProduct;
	private CustomButton btnAdd;
	private CustomButton btnSaveEdit;
	private CustomButton btnEdit;
	private CustomButton btnDelete;
	private JPanel formSection;
	private JTable table;

	/* ================= THEME ================= */
	private static final Color PRIMARY_DARK = new Color(21, 128, 61);
	private static final Color DANGER = new Color(153, 27, 27);
	private static final Color BORDER = new Color(187, 247, 208);
	private static final Color LABEL_COLOR = new Color(22, 101, 52);
	private static final Color SUCCESS = new Color(34, 197, 94);
	private static final Color WARNING = new Color(217, 119, 6); 
	private static final Color NEUTRAL = new Color(107, 114, 128); 
	private static final Font LABEL_FONT = new Font("Segoe UI", Font.BOLD, 14);
	private static final Font FIELD_FONT = new Font("Segoe UI", Font.PLAIN, 14);

	/* ================= CONSTRUCTOR ================= */
	public ExportItemPanel() {
		setLayout(new BorderLayout());
		setOpaque(false);

		add(createContent(), BorderLayout.CENTER);
		applyParentMode();
	}

	public void setViewData(List<InventoryExportItemResponseDTO> items) {
		parentMode = MODE_VIEW;
		responseItems.clear();
		responseItems.addAll(items);
		requestItems.clear();

		setBorder(BorderFactory.createLineBorder(
				new Color(187, 247, 208), 
				1));

		applyParentMode();
		loadTableFromResponse();
	}

	public void setAddMode() {
		parentMode = MODE_ADD;
		responseItems.clear();
		requestItems.clear();

		applyParentMode();
		clearTable();
	}

	public void setEditData(List<InventoryExportItemResponseDTO> response,
			List<InventoryExportItemRequestDTO> request) {
		parentMode = MODE_EDIT;

		responseItems.clear();
		responseItems.addAll(response);

		requestItems.clear();
		requestItems.addAll(request);

		applyParentMode();
		loadTableFromRequest();
	}

	public List<InventoryExportItemRequestDTO> getRequestItems() {
		return requestItems;
	}

	/* ================= MAIN CONTENT ================= */
	private JPanel createContent() {
		JPanel wrapper = new JPanel();
		wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
		wrapper.setOpaque(false);

		formSection = createFormSection();
		wrapper.add(formSection);
		wrapper.add(Box.createVerticalStrut(12));
		wrapper.add(createTableSection());

		return wrapper;
	}

	/* ================= FORM SECTION ================= */
	private JPanel createFormSection() {
		JPanel form = new JPanel();
		form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
		form.setOpaque(false);

		addSectionTitle(form, "Thêm / sửa sản phẩm");

		txtProduct = createTextField(false);
		spQuantity = new JSpinner(new SpinnerNumberModel(1, 1, 999, 1));
		txtInventoryOriginal = createTextField(false);
		txtInventoryAfter = createTextField(false);
		txtDescription = new JTextArea(4, 20);
		txtDescription.setFont(FIELD_FONT);
		txtDescription.setLineWrap(true);
		txtDescription.setWrapStyleWord(true);
		txtDescription.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

		JScrollPane noteScroll = new JScrollPane(txtDescription);
		noteScroll.setPreferredSize(new Dimension(200, 90));
		noteScroll.setBorder(BorderFactory.createLineBorder(BORDER));

		btnSelectProduct = createButton("Chọn SP", "/icon/search.png", NEUTRAL);
		btnAdd = createButton("Thêm", "/icon/plus.png", SUCCESS);
		btnSaveEdit = createButton("Lưu sửa", "/icon/save.png", SUCCESS);

		addRow(form, "Sản phẩm", createWithButton(txtProduct, btnSelectProduct));
		addRow(form, "Số lượng", spQuantity);
		addRow(form, "Note", noteScroll);
		addRow(form, "Tồn kho ban đầu", txtInventoryOriginal);
		addRow(form, "Tồn kho sau xuất", txtInventoryAfter);

		JPanel btnRow = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
		btnRow.setOpaque(false);
		btnRow.add(btnAdd);
		btnRow.add(btnSaveEdit);

		form.add(btnRow);
		return form;
	}

	/* ================= TABLE SECTION ================= */
	private JPanel createTableSection() {
		JPanel panel = new JPanel(new BorderLayout(8, 8));
		panel.setOpaque(false);

		addSectionTitle(panel, "Danh sách sản phẩm");

		btnEdit = createButton("Sửa", "/icon/edit.png", WARNING);
		btnDelete = createButton("Xóa", "/icon/delete.png", DANGER);

		JPanel top = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
		top.setOpaque(false);
		top.add(btnEdit);
		top.add(btnDelete);

		table = new JTable(new DefaultTableModel(
				new Object[][] {},
				new String[] { "Tên sản phẩm", "SL", "Đơn giá", "Thành tiền" }));
		TableUtil.style(table);
		table.setRowHeight(42);

		JScrollPane scroll = new JScrollPane(table);
		scroll.setBorder(BorderFactory.createLineBorder(BORDER));

		panel.add(top, BorderLayout.NORTH);
		panel.add(scroll, BorderLayout.CENTER);
		return panel;
	}

	/* ================= APPLY MODE ================= */
	private void applyParentMode() {
		boolean editable = parentMode != MODE_VIEW;

		formSection.setVisible(editable);

		btnEdit.setVisible(editable);
		btnDelete.setVisible(editable);

		table.setEnabled(editable);

		if (!editable)
			table.clearSelection();

		revalidate();
		repaint();
	}

	/* ================= TABLE LOAD ================= */
	private void loadTableFromResponse() {
		DefaultTableModel model = getModel();
		model.setRowCount(0);

		for (InventoryExportItemResponseDTO dto : responseItems) {
			model.addRow(new Object[] {
					dto.getProductName(),
					dto.getQuantity(),
					dto.getNote()

			});
		}
	}

	private void loadTableFromRequest() {
		DefaultTableModel model = getModel();
		model.setRowCount(0);

		for (InventoryExportItemRequestDTO dto : requestItems) {
			model.addRow(new Object[] {
					dto.getProductName(),
					dto.getQuantity(),
					dto.getNote()
			});
		}
	}

	private void clearTable() {
		getModel().setRowCount(0);
	}

	private DefaultTableModel getModel() {
		return (DefaultTableModel) table.getModel();
	}

	/* ================= UI UTIL ================= */
	private void addSectionTitle(JPanel parent, String title) {
		JLabel lbl = new JLabel(title);
		lbl.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lbl.setForeground(PRIMARY_DARK);
		lbl.setBorder(BorderFactory.createEmptyBorder(8, 0, 6, 0));
		parent.add(lbl);
	}

	private void addRow(JPanel parent, String label, Component field) {
		JPanel row = new JPanel(new BorderLayout(10, 6));
		row.setOpaque(false);

		JLabel lbl = new JLabel(label);
		lbl.setFont(LABEL_FONT);
		lbl.setForeground(LABEL_COLOR);
		lbl.setPreferredSize(new Dimension(120, 36));

		row.add(lbl, BorderLayout.WEST);
		row.add(field, BorderLayout.CENTER);

		parent.add(row);
		parent.add(Box.createVerticalStrut(6));
	}

	private JTextField createTextField(boolean editable) {
		JTextField txt = new JTextField();
		txt.setEditable(editable);
		txt.setFont(FIELD_FONT);
		txt.setPreferredSize(new Dimension(200, 36));
		txt.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(BORDER),
				BorderFactory.createEmptyBorder(0, 10, 0, 10)));
		return txt;
	}

	private JPanel createWithButton(Component field, Component btn) {
		JPanel panel = new JPanel(new BorderLayout(6, 0));
		panel.setOpaque(false);
		panel.add(field, BorderLayout.CENTER);
		panel.add(btn, BorderLayout.EAST);
		return panel;
	}

	private CustomButton createButton(String text, String icon, Color bg) {
		CustomButton btn = new CustomButton(
				text,
				ImageUtil.scaleIcon(
						new ImageIcon(getClass().getResource(icon)), 18, 18));
		btn.setBackgroundColor(bg);
		return btn;
	}
}
