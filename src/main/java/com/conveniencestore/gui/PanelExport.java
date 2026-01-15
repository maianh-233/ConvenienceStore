package com.conveniencestore.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.conveniencestore.DTO.InventoryExportItemResponseDTO;
import com.conveniencestore.DTO.InventoryExportResponseDTO;
import com.conveniencestore.constant.ExportStatus;
import com.conveniencestore.constant.ExportType;
import com.conveniencestore.gui.exportcomponent.ExportDialog;
import com.conveniencestore.gui.exportcomponent.FilterExportStatusPanel;
import com.conveniencestore.gui.utils.ButtonPanelUtil;
import com.conveniencestore.gui.utils.ComboItem;
import com.conveniencestore.gui.utils.CustomButton;
import com.conveniencestore.gui.utils.FilterDateUtil;
import com.conveniencestore.gui.utils.HeaderPanelUtil;
import com.conveniencestore.gui.utils.ImageUtil;
import com.conveniencestore.gui.utils.PaginationUtil;
import com.conveniencestore.gui.utils.SearchPanelUtil;
import com.conveniencestore.gui.utils.TableUtil;

public class PanelExport extends JPanel {
	private JFrame parentFrame;
	// ================= HEADER =================
	private String titlePanel = "Quản lý xuất hàng";
	private CustomButton btnReload;

	// ================= FILTERSTATUS =================
	private JLabel lblStatus;
	private JLabel lblType;

	private JComboBox cbStatus;
	private JComboBox cbType;

	private CustomButton btnFilterStatus;

	// ================= SEARCH =================
	private JTextField txtSearch;
	private CustomButton btnSearch;

	// ================= FILTER DATE =================
	private JLabel lblFrom;
	private JLabel lblTo;
	private JSpinner spFrom;
	private JSpinner spTo;
	private CustomButton btnFilter;

	// ================= BUTTON ACTION =================
	private CustomButton btnView;
	private CustomButton btnAdd;
	private CustomButton btnDelete;
	private CustomButton btnEdit;
	private CustomButton btnRestore;

	// ================= TABLE =================
	private JTable table;

	// ================= PAGINATION =================
	private CustomButton btnPrev;
	private CustomButton btnNext;

	public PanelExport(JFrame parentFrame) {
		this.parentFrame = parentFrame;
		initComponent();
		initLayout();
		initEvent();
	}

	private URL getIconUrl(String path) {
		return getClass().getResource(path);

	}

	// ================= KHỞI TẠO COMPONENT =================
	private void initComponent() {

		// ===== HEADER =====
		btnReload = new CustomButton(
				"Reload",
				ImageUtil.scaleIcon(
						new ImageIcon(getIconUrl("/icon/load.png")), 18, 18));

		// ===== FILTER STATUS =====

		lblStatus = new JLabel("Status:");
		cbStatus = createStatusCombo();

		lblType = new JLabel("Type:");
		cbType = createExportTypeCombo();

		btnFilterStatus = new CustomButton(
				"Lọc",
				ImageUtil.scaleIcon(
						new ImageIcon(getIconUrl("/icon/filter.png")), 18, 18));

		// ===== SEARCH =====
		txtSearch = new JTextField(20);
		btnSearch = new CustomButton(
				"Tìm Kiếm",
				ImageUtil.scaleIcon(
						new ImageIcon(getIconUrl("/icon/search.png")), 18, 18));

		// ===== FILTER DATE =====
		lblFrom = new JLabel("Từ");
		lblTo = new JLabel("Đến");

		spFrom = createDateSpinner();
		spTo = createDateSpinner();

		btnFilter = new CustomButton(
				"Lọc",
				ImageUtil.scaleIcon(
						new ImageIcon(getIconUrl("/icon/filter.png")), 18, 18));

		// ===== BUTTON ACTION =====
		btnView = new CustomButton("Xem", loadIcon("see"));
		btnAdd = new CustomButton("Thêm", loadIcon("plus"));
		btnDelete = new CustomButton("Xóa", loadIcon("delete"));
		btnEdit = new CustomButton("Sửa", loadIcon("edit"));
		btnRestore = new CustomButton("Restore", loadIcon("restore"));

		// ===== TABLE =====
		table = new JTable();
		TableUtil.style(table);
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.addColumn("Date");
		tableModel.addColumn("ID");
		tableModel.addColumn("Staff");
		tableModel.addColumn("Type");
		tableModel.addColumn("Status");
		tableModel.addColumn("Active");
		table.setModel(tableModel);

		// ===== PAGINATION =====
		btnPrev = new CustomButton(
				"Trước",
				ImageUtil.scaleIcon(
						new ImageIcon(getIconUrl("/icon/previous.png")), 16, 16));

		btnNext = new CustomButton(
				"Sau",
				ImageUtil.scaleIcon(
						new ImageIcon(getIconUrl("/icon/next.png")), 16, 16));
	}

	// ================= LAYOUT =================
	private void initLayout() {

		setLayout(new BorderLayout(10, 10));
		setOpaque(false);
		setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

		// ================= TOP =================
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		topPanel.setOpaque(false);

		// Header
		topPanel.add(
				HeaderPanelUtil.createHeaderPanel(titlePanel, btnReload));
		topPanel.add(Box.createVerticalStrut(10));

		topPanel.add(
				FilterExportStatusPanel.create(
						lblStatus, cbStatus,
						lblType, cbType,
						btnFilterStatus)

		);
		topPanel.add(Box.createVerticalStrut(10));
		// Search + Filter

		topPanel.add(
				SearchPanelUtil.createSearchPanel(txtSearch, btnSearch));
		topPanel.add(Box.createVerticalStrut(10));

		// ===== FILTER DATE =====
		topPanel.add(
				FilterDateUtil.createFilterDatePanel(
						lblFrom, spFrom,
						lblTo, spTo,
						btnFilter));
		topPanel.add(Box.createVerticalStrut(10));

		// Button action
		topPanel.add(
				ButtonPanelUtil.createButtonPanel(
						btnView,
						btnAdd,
						btnDelete,
						btnEdit,
						btnRestore));

		add(topPanel, BorderLayout.NORTH);

		// ================= CENTER (TABLE) =================
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.getViewport().setBackground(Color.WHITE);
		scrollPane.setBorder(BorderFactory.createLineBorder(new Color(229, 231, 235)));

		add(scrollPane, BorderLayout.CENTER);

		// ================= BOTTOM (PAGINATION) =================
		add(PaginationUtil.createPaginationPanel(btnPrev, btnNext), BorderLayout.SOUTH);
	}

	// ================= HELPER =================
	private JSpinner createDateSpinner() {
		JSpinner spinner = new JSpinner(new SpinnerDateModel());
		spinner.setEditor(new JSpinner.DateEditor(spinner, "dd/MM/yyyy"));
		spinner.setPreferredSize(new Dimension(120, 30));
		return spinner;
	}

	private Icon loadIcon(String name) {
		return ImageUtil.scaleIcon(
				new ImageIcon(getIconUrl("/icon/" + name + ".png")),
				18, 18);
	}

	private JComboBox<ComboItem<ExportStatus>> createStatusCombo() {
		JComboBox<ComboItem<ExportStatus>> combo = new JComboBox<>();

		combo.addItem(new ComboItem<>(null, "Tất cả"));

		for (ExportStatus status : ExportStatus.values()) {
			combo.addItem(new ComboItem<>(status, status.getDisplayName()));
		}

		return combo;
	}

	private JComboBox<ComboItem<ExportType>> createExportTypeCombo() {
		JComboBox<ComboItem<ExportType>> combo = new JComboBox<>();

		combo.addItem(new ComboItem<>(null, "Tất cả"));

		for (ExportType status : ExportType.values()) {
			combo.addItem(
					new ComboItem<>(status, status.getDisplayName()));
		}

		return combo;
	}

	private List<InventoryExportItemResponseDTO> mockExportItems() {
		List<InventoryExportItemResponseDTO> list = new ArrayList<>();

		InventoryExportItemResponseDTO i1 = new InventoryExportItemResponseDTO();
		i1.setId(1L);
		i1.setExportId(1L);
		i1.setExportCode("EXP-2026-0001");

		i1.setProductId(101L);
		i1.setProductCode("PRD-COCA");
		i1.setProductName("Coca Cola");

		i1.setQuantity(2);
		i1.setNote("Xuất bán lẻ");

		InventoryExportItemResponseDTO i2 = new InventoryExportItemResponseDTO();
		i2.setId(2L);
		i2.setExportId(1L);
		i2.setExportCode("EXP-2026-0001");

		i2.setProductId(102L);
		i2.setProductCode("PRD-MIHAOHAO");
		i2.setProductName("Mì Hảo Hảo");

		i2.setQuantity(5);
		i2.setNote("Xuất bán lẻ");

		list.add(i1);
		list.add(i2);

		return list;
	}

	private InventoryExportResponseDTO mockExportResponse() {
		InventoryExportResponseDTO dto = new InventoryExportResponseDTO();

		dto.setId(1L);
		dto.setCode("EXP-2026-0001");

		dto.setCreatedById(3L);
		dto.setCreatedByName("Trần Thị B");

		dto.setType(ExportType.SALE); 
		dto.setStatus(ExportStatus.SUCCESS);

		dto.setNote("Xuất hàng buổi sáng");
		dto.setIsDeleted(0);

		dto.setCreatedAt(LocalDateTime.now().minusDays(1));
		dto.setUpdatedAt(LocalDateTime.now());

		return dto;
	}

	// ACTION EVENT
	private void initEvent() {
		btnAdd.addActionListener(e -> {
			new ExportDialog(
					parentFrame,
					ExportDialog.MODE_ADD,
					null,
					new ArrayList<>());
		});

		btnEdit.addActionListener(e -> {
			new ExportDialog(
					parentFrame,
					ExportDialog.MODE_EDIT,
					mockExportResponse(),
					mockExportItems());
		});

		btnView.addActionListener(e -> {
			new ExportDialog(
					parentFrame,
					ExportDialog.MODE_VIEW,
					mockExportResponse(),
					mockExportItems());
		});
	}

}
